package czdbdk.dbdkbe.models.parameters;

import czdbdk.dbdkbe.models.databaseModels.Language;
import lombok.Data;

import java.text.Collator;
import java.util.Locale;

/**
 * @author Tereza Holm
 */
@Data
class OriginalLanguageInfo implements Comparable<OriginalLanguageInfo> {
    private Long booksMatchesValue;
    private String name;
    private String slug;

    OriginalLanguageInfo(Language language) {
        name = language.getName();
        slug = language.getSlug();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OriginalLanguageInfo)) return false;
        OriginalLanguageInfo originalLanguageInfo = (OriginalLanguageInfo) o;
        return name.equals(originalLanguageInfo.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31;
    }

    @Override
    public int compareTo(OriginalLanguageInfo originalLanguageInfo) {
        Collator usCollator = Collator.getInstance(new Locale("cs"));
        return usCollator.compare(name, originalLanguageInfo.getName());
    }
}
