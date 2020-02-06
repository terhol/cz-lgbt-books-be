package czdbdk.dbdkbe.models.parameters;

import czdbdk.dbdkbe.repositories.TagRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tereza Holm
 */
@Data
public class TagsInfo {
    private String name;
    private String slug;
    private Long booksMatchesValue;
    @Autowired
    TagRepository tagRepository;

    public TagsInfo(String slug){
        this.slug = slug;
        this.name = tagRepository.getNameFromSlug(slug);
        booksMatchesValue = tagRepository.countBySlug(slug);
    }
}
