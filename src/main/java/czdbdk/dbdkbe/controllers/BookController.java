package czdbdk.dbdkbe.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.slugify.Slugify;
import czdbdk.dbdkbe.exceptions.BookNotFoundException;
import czdbdk.dbdkbe.jview.DataView;
import czdbdk.dbdkbe.models.databaseModels.Author;
import czdbdk.dbdkbe.models.databaseModels.Book;
import czdbdk.dbdkbe.models.Info;
import czdbdk.dbdkbe.models.parameters.ParametersInfo;
import czdbdk.dbdkbe.repositories.AuthorRepository;
import czdbdk.dbdkbe.repositories.BookRepository;
import czdbdk.dbdkbe.utils.SlugMaker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.time.LocalDate;
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
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ParametersInfo parametersInfo;
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

        Pageable pageable = PageRequest.of(page, size, Sort.by(order, orderBy).and(Sort.by(order, "id")));

        return bookRepository.findAll(pageable).getContent();
    }

    @GetMapping(value = "/info", produces = "application/json")
    public Info showInfo(){
        Long numberOfBooks = bookRepository.count();
        LocalDate lastChange = bookRepository.findMaxDate();
        return new Info(numberOfBooks, lastChange);
    }

    @GetMapping(value = "/random", produces = "application/json")
    @JsonView(DataView.DetailView.class)
    public Book getRandomBook(){
        return bookRepository.findRandomBook();
    }

    @GetMapping(value = "/{slug}", produces = "application/json")
    @JsonView(DataView.DetailView.class)
    public Book showConcreteBook(
            @PathVariable(name = "slug") String slug
    ) {
        return bookRepository.findBySlug(slug)
                .orElseThrow(() -> new BookNotFoundException(slug));
    }

    @GetMapping(value = "/filterParams", produces = "application/json")
    public ParametersInfo getParametersInformation(){
        parametersInfo.prepareTagsList();
        return parametersInfo;
    }

    private Map<String, String> prepareMap() {
        Map<String, String> orderByMap = new HashMap<>();
        orderByMap.put("TITLE", "title");
        orderByMap.put("YEAR_OF_ISSUE", "yearOfIssue");
        orderByMap.put("DATE_OF_ADDITION", "dateOfAddition");
        return orderByMap;
    }

    // Method for adding books
    @PreAuthorize(value = "ROLES_ADMIN")
    @PostMapping(value = "/admin/add", consumes = "application/json")
    public String addNewBook(@RequestBody Book book) {
        book.setSlug(SlugMaker.prepareSlug(book.getTitle(), book.getYearOfIssue()));
        book.setDateOfAddition(LocalDate.now());
        book.setImageURL(prepareImageURL(book.getLinks().getGoodreads()));
        for (Author author : book.getAuthors()) {
            if (!authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
                authorRepository.save(author);
            }
        }
        bookRepository.save(book);

        return book.getSlug();
    }

    private String prepareImageURL(String goodreads) {
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
