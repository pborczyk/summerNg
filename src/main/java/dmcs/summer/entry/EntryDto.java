package dmcs.summer.entry;

import dmcs.summer.user.User;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private String embedContent;

    private EMBED_CONTENT_TYPE embedContentType;
    private List<CommentDto> comments;
    private boolean canUpvote;
}
