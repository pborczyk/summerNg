package dmcs.summer.entry;

import org.springframework.data.jpa.repository.JpaRepository;

interface EntryRepository extends JpaRepository<Entry, Long> {
}
