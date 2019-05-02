package dmcs.summer.entry;

import dmcs.summer.user.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class EntryDto {
    private long id;
    private String content;
    private String author;
    private Set<String> hashTags;
    private int upvotes;

    private Date timeStamp;
    private List<CommentDto> comments;
}
