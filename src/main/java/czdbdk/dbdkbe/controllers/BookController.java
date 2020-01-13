package czdbdk.dbdkbe.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import czdbdk.dbdkbe.jview.DataView;
import czdbdk.dbdkbe.models.Book;
import czdbdk.dbdkbe.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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



    @GetMapping
    @JsonView(DataView.SummaryView.class)
    public Iterable<Book> getAllBooks(
            @RequestParam(defaultValue = "title") String orderBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction order,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "-1") Integer size
    ) {
        if(size == -1){
            size = Math.toIntExact(bookRepository.count());
        }
        orderBy = orderByMap.getOrDefault(orderBy, "title");

        Pageable pageable = PageRequest.of(page,size, Sort.by(order, orderBy));

        return bookRepository.findAll(pageable);
    }

    @GetMapping("/total")
    public long getNumberOfBooks() {
        return bookRepository.count();
    }

    private Map<String, String> prepareMap(){
        Map<String, String> orderByMap = new HashMap<>();
        orderByMap.put("TITLE", "title");
        orderByMap.put("YEAR_OF_ISSUE", "yearOfIssue");
        orderByMap.put("DATE_OF_ADDITION", "dateOfAdition");
        return orderByMap;
    }
}
