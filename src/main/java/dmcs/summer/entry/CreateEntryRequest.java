package dmcs.summer.entry;

import lombok.Data;

@Data
public class CreateEntryRequest {
    private String content;
    private String author;
}
