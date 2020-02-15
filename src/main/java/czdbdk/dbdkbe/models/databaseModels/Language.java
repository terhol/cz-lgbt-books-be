package czdbdk.dbdkbe.models.databaseModels;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Tereza Holm
 */
@Data
@Entity(name = "language")
public class Language {
    private String name;
    @Id
    private String slug;
}
