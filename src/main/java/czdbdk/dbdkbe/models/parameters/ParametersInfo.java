package czdbdk.dbdkbe.models.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import czdbdk.dbdkbe.models.databaseModels.Book;
import czdbdk.dbdkbe.models.databaseModels.Tag;
import czdbdk.dbdkbe.repositories.BookRepository;
import czdbdk.dbdkbe.repositories.BookTagRepository;
import czdbdk.dbdkbe.repositories.TagRepository;
import czdbdk.dbdkbe.utils.SlugMaker;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tereza Holm
 */
@Data
@Component
public class ParametersInfo {
    //private List<BookSizeInfo> bookSize = prepareBookSizeList();
    @Autowired
    @JsonIgnore
    private BookRepository bookRepository;
    @Autowired
    @JsonIgnore
    private TagRepository tagRepository;
    @Autowired
    @JsonIgnore
    private BookTagRepository bookTagRepository;
    //private List<TagsInfo> tags;
    //private List<OriginalLanguageInfo> originalLanguage;

    public void prepareOriginalLanguageList() {
        List<OriginalLanguageInfo> finalListOfLanguages = new ArrayList<>();
        List<String> allOriginalLanguages = bookRepository.findDistinctByOriginalLanguage();
        for (String language : allOriginalLanguages) {
            OriginalLanguageInfo currentLanguage = new OriginalLanguageInfo(language);
            currentLanguage.setSlug(SlugMaker.prepareSlug(language));
            currentLanguage.setBooksMatchesValue(bookRepository.countByOriginalLanguage(language));
            finalListOfLanguages.add(currentLanguage);
        }
        //this.originalLanguage = finalListOfLanguages;
    }

    private List<BookSizeInfo> prepareBookSizeList() {
        List<BookSizeInfo> finalBookSize = new ArrayList<>();
        //TODO non-hardcoded values
        finalBookSize.add(new BookSizeInfo("krátká", 0, 200));
        finalBookSize.add(new BookSizeInfo("střední", 201, 400));
        //TODO find out maximal value?
        finalBookSize.add(new BookSizeInfo("dlouhá", 401, 10000));
        return finalBookSize;
    }

    public void prepareTagsList() {
        List<TagsInfo> finalTags = new ArrayList<>();
        try {
            List<Tag> allSlugs = tagRepository.findAll();
            for (Tag tag : allSlugs) {
                TagsInfo currentTag = new TagsInfo(tag.getSlug());
                currentTag.setName(tagRepository.getNameFromSlug(tag.getSlug()));
                currentTag.setBooksMatchesValue(bookTagRepository.countByTagSlug(tag.getSlug()));
                finalTags.add(currentTag);
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        //this.tags = finalTags;
    }
}
