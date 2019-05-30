package dmcs.summer.entry;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import dmcs.summer.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.QSort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
                .build();
        Entry savedEntry = entryRepository.save(entry);
        return savedEntry.getId();
    }

    public List<EntryDto> getNewest() {
        return entryRepository.findByOrderByTimeStampDesc().stream().map(Entry::asDto).collect(Collectors.toList());
    }

    public List<EntryDto> getHot() {
        PathBuilder<Entry> entryPathBuilder = new PathBuilder<>(Entry.class, "entry");
        OrderSpecifier<Long> divide = entryPathBuilder.getNumber("upvotes", Long.class)
                .divide(entryPathBuilder.getTime("timestamp", Date.class)
                        .castToNum(Long.class))
                .desc();
        QSort orders = new QSort(divide);
        return entryRepository.findAll(orders).stream().map(Entry::asDto).collect(Collectors.toList());
    }

    public List<EntryDto> getTop() {
        return entryRepository.findByOrderByUpvotesDesc().stream().map(Entry::asDto).collect(Collectors.toList());
    }

    public void incrementEntry(Long entryId) {
        Entry entry = entryRepository.findById(entryId).orElseThrow(EntryDoesNotExistException::new);
        entry.setUpvotes(entry.getUpvotes() + 1);
        entryRepository.save(entry);
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
        return entryRepository.findById(entryId).map(Entry::asDto).orElseThrow();
    }

    public List<EntryDto> getEntriesByUser(String username) {
        return entryRepository.findByAuthor_UsernameOrderByTimeStampAsc(username)
                .stream()
                .map(Entry::asDto)
                .collect(Collectors.toList());
    }
}
