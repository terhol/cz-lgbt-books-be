package czdbdk.dbdkbe.models.databaseModels;

import com.fasterxml.jackson.annotation.JsonBackReference;
import czdbdk.dbdkbe.models.AuthorCredentials;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author Tereza Holm
 */
@Entity(name = "author")
@Data
@IdClass(AuthorCredentials.class)
public class Author implements Serializable {
    @Id
    @Column(name = "name")
    private String firstName;
    @Id
    @Column(name = "surname")
    private String lastName;
    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;
}
