package czdbdk.dbdkbe.controllers;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.slugify.Slugify;
import czdbdk.dbdkbe.exceptions.BookNotFoundException;
import czdbdk.dbdkbe.jview.DataView;
import czdbdk.dbdkbe.models.Author;
import czdbdk.dbdkbe.models.Book;
import czdbdk.dbdkbe.models.BookCount;
import czdbdk.dbdkbe.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Tereza Holm
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    private Map<String, String> orderByMap = prepareMap();

    @GetMapping(produces = "application/json")
    @JsonView(DataView.SummaryView.class)
    public List<Book> getAllBooks(
            @RequestParam(defaultValue = "dateOfAddition") String orderBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction order,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        orderBy = orderByMap.getOrDefault(orderBy, "dateOfAddition");

        Pageable pageable = PageRequest.of(page, size, Sort.by(order, orderBy));

        return bookRepository.findAll(pageable).getContent();
    }

    @GetMapping(value = "/total", produces = "application/json")
    public BookCount getNumberOfBooks() {
        BookCount bookcount = new BookCount(bookRepository.count());
        return bookcount;
    }

    @GetMapping(value = "/{slug}", produces = "application/json")
    @JsonView(DataView.DetailView.class)
    public Book showConcreteBook(
            @PathVariable(name = "slug") String slug
    ) {
        return bookRepository.findBySlug(slug)
                .orElseThrow(() -> new BookNotFoundException(slug));


    }

    private Map<String, String> prepareMap() {
        Map<String, String> orderByMap = new HashMap<>();
        orderByMap.put("TITLE", "title");
        orderByMap.put("YEAR_OF_ISSUE", "yearOfIssue");
        orderByMap.put("DATE_OF_ADDITION", "dateOfAddition");
        return orderByMap;
    }

    // Method for adding books

    @PostMapping(value = "/admin/add", consumes = "application/json")
    public String addNewBook(@RequestBody Book book){
        book.setSlug(prepareSlug(book.getTitle(), book.getYearOfIssue()));
        bookRepository.save(book);

        return book.getSlug();
    }

    private String prepareSlug(String title, String yearOfIssue) {
        Slugify slg = new Slugify();
        String result = slg.slugify(String.format("%s %s", title, yearOfIssue));
        return result;
    }
}
