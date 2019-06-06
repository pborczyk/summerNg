package dmcs.summer.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

interface EntryRepository extends PagingAndSortingRepository<Entry, Long>, QuerydslPredicateExecutor<Entry> {
    Page<Entry> findByOrderByTimeStampDesc(Pageable pageable);
    Page<Entry> findByOrderByUpvotesDesc(Pageable pageable);
    Optional<Entry> findById(long id);
    List<Entry> findByAuthor_UsernameOrderByTimeStampAsc(String username);
    boolean existsByAuthor_UsernameAndId(String author, Long entryId);
}
