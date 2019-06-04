package dmcs.summer.user;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Table(name = "ROLE_T")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roleName;

    @Singular
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Privilege> privileges;
}
