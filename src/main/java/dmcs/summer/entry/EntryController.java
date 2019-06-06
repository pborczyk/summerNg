package dmcs.summer.entry;

import dmcs.summer.user.Privilege;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
class EntryController {

    private final EntryService entryService;

    @PostMapping("/entries")
    long createEntry(@RequestBody CreateEntryRequest request) {
        return entryService.createEntry(request);
    }

    @PutMapping("/entries/{id}")
    void editEntry(@PathVariable(name = "id") Long entryId, @RequestBody EditEntryRequestDto requestDto) {
        entryService.editEntry(entryId, requestDto);
    }

    @GetMapping("/entries")
    GetEntiresResponse getNewest(@RequestParam(name = "mode") String mode,
                             @RequestParam(name = "page") Integer page,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        switch (mode) {
            case "top":
                return entryService.getTop(page, pageSize);
            case "newest":
                return entryService.getNewest(page, pageSize);
        }
        return entryService.getNewest(page, pageSize);
    }

    @PutMapping("/entries/{id}/upvote")
    Long incrementUpvotes(@PathVariable(name = "id") Long entryId) {
        return entryService.incrementEntryAndReturnUpvotes(entryId);
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

    @DeleteMapping("/entries/{id}")
    void deleteEntry(@PathVariable(name = "id") Long entryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorties = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if (authorties.contains(Privilege.DELETE_ALL_POSTS.name())) {
            entryService.deleteById(entryId);
        } else if (authorties.contains(Privilege.DELETE_OWN_POSTS.name()) &&
                isAuthorsEntry(entryId, authentication.getName())) {
            entryService.deleteById(entryId);
        }
    }

    private boolean isAuthorsEntry(Long entryId, String name) {
       return entryService.isAuthorsEntry(entryId, name);
    }
}
