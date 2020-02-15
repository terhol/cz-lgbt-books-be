package czdbdk.dbdkbe.specifications;

import czdbdk.dbdkbe.models.databaseModels.Book;
import czdbdk.dbdkbe.models.databaseModels.BookLength;
import czdbdk.dbdkbe.models.databaseModels.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tereza Holm
 */
public class BookSpecifications {
    @Autowired
    private static EntityManager em;

    public static Specification<Book> hasLanguageSlug(String languageSlug) {
        return new Specification<Book>() {
            private static final long serialVersionUID = 263002298111156757L;

            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (languageSlug == null) return null;
                return criteriaBuilder.equal(root.get("language").get("slug"), languageSlug);
            }
        };
    }

    public static Specification<Book> hasBookLength(BookLength bookLength) {
        return new Specification<Book>() {
            private static final long serialVersionUID = -1145575019624999026L;

            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (bookLength == null) return null;
                return criteriaBuilder.between(root.get("numberOfPages"), bookLength.getMinPages(), bookLength.getMaxPages());
            }
        };
    }

    public static Specification<Book> hasTags(List<Tag> tags) {
        return new Specification<Book>() {
            private static final long serialVersionUID = 7349064684335223773L;

            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (tags.isEmpty()) return null;
                List<Predicate> predicates = new ArrayList<>();
                for (Tag tag : tags) predicates.add(criteriaBuilder.isMember(tag, root.get("tags")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
