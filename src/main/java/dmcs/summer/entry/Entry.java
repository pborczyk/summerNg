package dmcs.summer.entry;

import dmcs.summer.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "ENTRY_T")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"comments"})
@ToString(exclude = {"comments"})
public class Entry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne
    private User author;

    @ElementCollection
    private Set<String> hashTags;

    private int upvotes;

    private Date timeStamp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entry")
    private List<Comment> comments;

    private String embedContent;

    @Enumerated(EnumType.STRING)
    private EMBED_CONTENT_TYPE embedContentType;

    public EntryDto asDto() {
        EntryDto dto = new EntryDto();
        dto.setAuthor(getAuthor().getUsername());
        dto.setComments(getComments().stream().map(Comment::asDto).collect(Collectors.toList()));
        dto.setHashTags(getHashTags());
        dto.setId(getId());
        dto.setContent(getContent());
        dto.setTimeStamp(getTimeStamp());
        dto.setUpvotes(getUpvotes());
        dto.setEmbedContent(getEmbedContent());
        dto.setEmbedContentType(getEmbedContentType());
        return dto;
    }
}
