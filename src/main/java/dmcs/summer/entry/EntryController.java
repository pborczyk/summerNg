package dmcs.summer.entry;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
@RequiredArgsConstructor
class EntryController {

    private final EntryService entryService;

    @PostMapping
    long createEntry(CreateEntryRequest request) {
        return entryService.createEntry(request);
    }

    @GetMapping("/newest")
    List<EntryDto> getNewest(){
        return entryService.getNewest();
    }

    @GetMapping("/top")
    List<EntryDto> getTop(){
        return entryService.getTop();
    }

    @GetMapping("/hot")
    List<EntryDto> getHot(){
        return entryService.getHot();
    }

    @PutMapping
    void incrementUpvotes(Long entryId) {
        entryService.incrementEntry(entryId);
    }
}
