package dmcs.summer.entry;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
class EntryController {

    private final EntryService entryService;

    @PostMapping("/entry")
    long createEntry(@RequestBody CreateEntryRequest request) {
        return entryService.createEntry(request);
    }

    @GetMapping("/entries/newest")
    List<EntryDto> getNewest(){
        return entryService.getNewest();
    }

    @GetMapping("/entries/top")
    List<EntryDto> getTop(){
        return entryService.getTop();
    }

    @GetMapping("/entries/hot")
    List<EntryDto> getHot(){
        return entryService.getHot();
    }

    @PutMapping
    void incrementUpvotes(@RequestBody Long entryId) {
        entryService.incrementEntry(entryId);
    }

    @PostMapping("/entry/comment/")
    void postComment(@RequestBody CreateCommentDto createCommentDto) {
        entryService.postComment(createCommentDto.getEntryId(), createCommentDto);
    }

    @GetMapping("/entry/comment")
    List<CommentDto> getEntriesComments(Long entryId) {
        return entryService.getEntriesComments(entryId);
    }
}
