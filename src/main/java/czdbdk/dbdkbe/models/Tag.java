package czdbdk.dbdkbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author Tereza Holm
 */
@Entity(name = "tag")
@Data
public class Tag implements Serializable {
    private String name;
    @Id
    private String slug;
    private String color;
    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private List<Book> books;
}
