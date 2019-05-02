package dmcs.summer.entry;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private long id;
    private String content;
    private String author;
    private int upvotes;
    private Date timeStamp;
}
