package czdbdk.dbdkbe.models.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import czdbdk.dbdkbe.models.databaseModels.BookLength;
import czdbdk.dbdkbe.models.databaseModels.Language;
import czdbdk.dbdkbe.models.databaseModels.Tag;
import czdbdk.dbdkbe.repositories.BookLengthRepository;
import czdbdk.dbdkbe.repositories.BookRepository;
import czdbdk.dbdkbe.repositories.BookTagRepository;
import czdbdk.dbdkbe.repositories.LanguageRepository;
import czdbdk.dbdkbe.repositories.TagRepository;
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
    @Autowired
    @JsonIgnore
    private BookRepository bookRepository;
    @Autowired
    @JsonIgnore
    private TagRepository tagRepository;
    @Autowired
    @JsonIgnore
    private BookTagRepository bookTagRepository;
    @Autowired
    @JsonIgnore
    private LanguageRepository languageRepository;
    @Autowired
    @JsonIgnore
    private BookLengthRepository bookLengthRepository;
    private List<TagsInfo> tags;
    private List<OriginalLanguageInfo>
            originalLanguage;
    private List<BookLength> bookSize;

    public void prepareOriginalLanguageList() {
        List<OriginalLanguageInfo> finalListOfLanguages = new ArrayList<>();
        List<Language> allOriginalLanguages = languageRepository.findAll();
        for (Language language : allOriginalLanguages) {
            OriginalLanguageInfo currentLanguage = new OriginalLanguageInfo(language);
            currentLanguage.setBooksMatchesValue(bookRepository.countByLanguageSlug(language.getSlug()));
            finalListOfLanguages.add(currentLanguage);
        }
        this.originalLanguage = finalListOfLanguages;
    }

    public void prepareBookSizeList() {
        List<BookLength> finalBookSize = bookLengthRepository.findAllLengths();
        for (BookLength bookLength : finalBookSize) {
            bookLength.setBooksMatchesValue(bookRepository.countByNumberOfPages(bookLength.getMinPages(), bookLength.getMaxPages()));
        }
        this.bookSize = finalBookSize;
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
        this.tags = finalTags;
    }
}
