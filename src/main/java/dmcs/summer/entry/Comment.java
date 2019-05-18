package dmcs.summer.entry;

import dmcs.summer.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "COMMENT_T")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"entry"})
@ToString(exclude = {"entry"})
class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne
    private Entry entry;

    @ManyToOne
    private User author;

    private int upvotes;

    private Date timeStamp;

    public CommentDto asDto() {
        CommentDto dto = new CommentDto();
        dto.setAuthor(getAuthor().getUsername());
        dto.setContent(getContent());
        dto.setId(getId());
        dto.setTimeStamp(getTimeStamp());
        dto.setUpvotes(getUpvotes());
        return dto;
    }


}
