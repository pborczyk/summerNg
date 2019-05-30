package dmcs.summer.entry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> getAllByEntry_IdOrderByTimeStampAsc(Long entryId);
}
