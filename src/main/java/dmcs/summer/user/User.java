package dmcs.summer.user;

import dmcs.summer.entry.Entry;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Table(name = "USER_T")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "author")
    private List<Entry> entries;

    @Singular
    @ElementCollection
    private Set<Long> upvotedEntries;

}
