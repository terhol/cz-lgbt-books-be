package czdbdk.dbdkbe.utils;

import com.github.slugify.Slugify;

/**
 * @author Tereza Holm
 */
public class SlugMaker {
    public static String prepareSlug(String title, String yearOfIssue) {
        Slugify slg = new Slugify();
        String result = slg.slugify(String.format("%s %s", title, yearOfIssue));
        return result;
    }

    public static String prepareSlug(String title) {
        Slugify slg = new Slugify();
        String result = slg.slugify(title);
        return result;
    }
}
