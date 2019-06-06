package dmcs.summer.entry;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetEntiresResponse {
    private int pageNumber;
    private int maxPages;
    private int sizeOfPage;

    private List<EntryDto> content;}
