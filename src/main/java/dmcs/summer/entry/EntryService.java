package dmcs.summer.entry;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import dmcs.summer.user.QUser;
import dmcs.summer.user.User;
import dmcs.summer.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.QSort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    long createEntry(CreateEntryRequest request) {
        Entry entry = Entry.builder()
                .author(
                        userRepository.findByUsername(request.getAuthor()).orElseThrow(() -> new UsernameNotFoundException(request.getAuthor()))
                )
                .content(request.getContent())
                .upvotes(0)
                .embedContent(request.getEmbedContent())
                .embedContentType(request.getEmbedContentType())
                .build();
        Entry savedEntry = entryRepository.save(entry);
        return savedEntry.getId();
    }

    public List<EntryDto> getNewest() {
        return entryRepository.findByOrderByTimeStampDesc().stream().map(this::asDto).collect(Collectors.toList());
    }

    public List<EntryDto> getTop() {
        return entryRepository.findByOrderByUpvotesDesc().stream().map(this::asDto).collect(Collectors.toList());
    }

    public long incrementEntryAndReturnUpvotes(Long entryId) {
        Entry entry = entryRepository.findById(entryId).orElseThrow(EntryDoesNotExistException::new);
        entry.setUpvotes(entry.getUpvotes() + 1);
        entryRepository.save(entry);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow();
        user.getUpvotedEntries().add(entryId);
        userRepository.save(user);
        return entry.getUpvotes();
    }

    public void postComment(Long entryId, CreateCommentDto createCommentDto) {
        Entry entry = entryRepository.findById(entryId).get();
        Comment comment = Comment.builder()
                .author(userRepository.findByUsername(createCommentDto.getAuthor()).orElseThrow())
                .content(createCommentDto.getContent())
                .entry(entryRepository.findById(entryId).orElseThrow())
                .timeStamp(new Date())
                .build();
        entry.getComments().add(comment);
        commentRepository.save(comment);
        entryRepository.save(entry);
    }

    public List<CommentDto> getEntriesComments(Long entryId) {
        return commentRepository.getAllByEntry_IdOrderByTimeStampAsc(entryId)
                .stream()
                .map(Comment::asDto)
                .collect(Collectors.toList());
    }

    public EntryDto getById(Long entryId) {
        return entryRepository.findById(entryId).map(this::asDto).orElseThrow();
    }

    public List<EntryDto> getEntriesByUser(String username) {
        return entryRepository.findByAuthor_UsernameOrderByTimeStampAsc(username)
                .stream()
                .map(this::asDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long entryId) {
        entryRepository.deleteById(entryId);
    }

    public boolean isAuthorsEntry(Long entryId, String name) {
        return entryRepository.existsByAuthor_UsernameAndId(name, entryId);
    }

    public EntryDto asDto(Entry entry) {
        EntryDto dto = new EntryDto();
        dto.setAuthor(entry.getAuthor().getUsername());
        dto.setComments(entry.getComments().stream().map(Comment::asDto).collect(Collectors.toList()));
        dto.setHashTags(entry.getHashTags());
        dto.setId(entry.getId());
        dto.setContent(entry.getContent());
        dto.setTimeStamp(entry.getTimeStamp());
        dto.setUpvotes(entry.getUpvotes());
        dto.setEmbedContent(entry.getEmbedContent());
        dto.setEmbedContentType(entry.getEmbedContentType());
        dto.setCanUpvote(canUpvote(entry));
        return dto;
    }

    private boolean canUpvote(Entry entry) {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            BooleanExpression booleanExpression = QUser.user.username.eq(username)
                    .and(QUser.user.upvotedEntries.contains(entry.getId()));
            boolean b = !userRepository.exists(booleanExpression);
            return b;
        }
        return false;
    }

    public void editEntry(Long entryId, EditEntryRequestDto requestDto) {
        Entry entry = entryRepository.findById(entryId).orElseThrow();
        entry.setContent(requestDto.getContent());
        entry.setEmbedContent(requestDto.getEmbedContent());
        entryRepository.save(entry);
    }
}
