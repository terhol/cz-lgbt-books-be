package czdbdk.dbdkbe.models.parameters;

import czdbdk.dbdkbe.repositories.BookRepository;
import czdbdk.dbdkbe.utils.SlugMaker;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tereza Holm
 */
@Data
public class OriginalLanguageInfo {
    //private Long booksMatchesValue;
    private String name;
    //private String slug;

    @Autowired
    BookRepository bookRepository;

    public OriginalLanguageInfo(String name){
        this.name = name;
        //this.slug = SlugMaker.prepareSlug(name);
        //this.booksMatchesValue = bookRepository.countByOriginalLanguage(name);
    }



}
