package dmcs.summer.entry;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
class EntryController {

    private final EntryService entryService;

    @PostMapping("/entries")
    long createEntry(@RequestBody CreateEntryRequest request) {
        return entryService.createEntry(request);
    }

    @GetMapping("/entries")
    List<EntryDto> getNewest(@RequestParam(name = "mode") String mode) {
        switch (mode) {
            case "top":
                return entryService.getTop();
            case "newest":
                return entryService.getNewest();
        }
        return entryService.getNewest();
    }

    @PutMapping("/entries/{id}/upvote")
    void incrementUpvotes(@RequestBody Long entryId) {
        entryService.incrementEntry(entryId);
    }

    @PostMapping("/entries/{id}/comments")
    void postComment(@PathVariable(name = "id") Long entryId,
                     @RequestBody CreateCommentDto createCommentDto) {
        entryService.postComment(entryId, createCommentDto);
    }

    @GetMapping("/entries/{id}/comments")
    List<CommentDto> getEntriesComments(@PathVariable(name = "id") Long entryId) {
        return entryService.getEntriesComments(entryId);
    }

    @GetMapping("/entries/{id}")
    EntryDto getById(@PathVariable(name = "id") Long entryId) {
        return entryService.getById(entryId);
    }

    @GetMapping("/users/{username}/entries")
    List<EntryDto> getEntriesByUser(@PathVariable(name = "username") String username) {
        return entryService.getEntriesByUser(username);
    }
}
