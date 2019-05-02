package dmcs.summer.entry;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
