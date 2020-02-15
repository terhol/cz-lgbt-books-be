package czdbdk.dbdkbe.models.parameters;

import lombok.Data;

import java.text.Collator;
import java.util.Locale;

/**
 * @author Tereza Holm
 */
@Data
class TagsInfo implements Comparable<TagsInfo> {
    private String name;
    private String slug;
    private Long booksMatchesValue;

    TagsInfo(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagsInfo)) return false;
        TagsInfo tagsInfo = (TagsInfo) o;
        return name.equals(tagsInfo.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31;
    }

    @Override
    public int compareTo(TagsInfo tagsInfo) {
        Collator usCollator = Collator.getInstance(new Locale("cs"));
        return usCollator.compare(name, tagsInfo.getName());
    }
}
