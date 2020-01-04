package czdbdk.dbdkbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author Tereza Holm
 */

@Entity(name = "author")
@Data
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long authorId;
    @Column(name = "author_id")
    private int id;
    @Column(name = "name")
    private String firstName;
    @Column(name = "surname")
    private String lastName;


    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;
}
