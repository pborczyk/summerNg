package dmcs.summer.entry;

import dmcs.summer.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final UserRepository userRepository;

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
        return entryRepository.findAll().stream().map(Entry::asDto).collect(Collectors.toList());
    }

    public List<EntryDto> getHot() {
        return null;
    }

    public List<EntryDto> getTop() {
        return null;
    }
}
