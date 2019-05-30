package dmcs.summer.entry;

import lombok.Data;

@Data
public class CreateCommentDto {
    private Long entryId;
    private String author;
    private String content;
}
