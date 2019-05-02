package dmcs.summer.entry;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.core.types.dsl.NumberExpression;
import dmcs.summer.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.QSort;
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
        return entryRepository.findByOrderByTimeStampDesc().stream().map(Entry::asDto).collect(Collectors.toList());
    }

    public List<EntryDto> getHot() {
        OrderSpecifier<Integer> divide = QEntry.entry.upvotes.divide(QEntry.entry.timeStamp.castToNum(Long.class)).desc();
        QSort orders = new QSort(divide);
        return entryRepository.findAll(orders).stream().map(Entry::asDto).collect(Collectors.toList());
    }

    public List<EntryDto> getTop() {
        return entryRepository.findByOrderByUpvotesDesc().stream().map(Entry::asDto).collect(Collectors.toList());
    }
}
