package dmcs.summer.entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

interface EntryRepository extends JpaRepository<Entry, Long>, QuerydslPredicateExecutor<Entry> {
    List<Entry> findByOrderByTimeStampDesc();
    List<Entry> findByOrderByUpvotesDesc();
    Optional<Entry> findById(long id);
}
