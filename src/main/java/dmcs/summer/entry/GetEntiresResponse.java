package dmcs.summer.entry;

import lombok.Data;

import java.util.List;

@Data
public class GetEntiresResponse {
    private List<EntryDto> entries;
}
