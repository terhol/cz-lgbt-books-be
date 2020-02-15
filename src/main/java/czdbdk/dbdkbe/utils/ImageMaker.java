package czdbdk.dbdkbe.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * @author Tereza Holm
 */
public class ImageMaker {
    public static String prepareImageURL(String goodreads) {
        Cloudinary cloudinary = new Cloudinary();
        String finalImageUrl = "placeholderString";
        try {
            Document document = Jsoup.connect(goodreads).get();
            String imageUrl = document.getElementById("coverImage").attr("src");
            Map params = ObjectUtils.asMap("transformation", new Transformation().crop("pad").width(300).height(400));
            Map uploadResult = cloudinary.uploader().upload(imageUrl, params);
            finalImageUrl = uploadResult.get("secure_url").toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return finalImageUrl;
    }
}
