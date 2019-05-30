package dmcs.summer.entry;

import lombok.Data;

@Data
public class CreateEntryRequest {
    private String content;
    private String author;
    private EMBED_CONTENT_TYPE embedContentType;
    private String embedContent;
}
