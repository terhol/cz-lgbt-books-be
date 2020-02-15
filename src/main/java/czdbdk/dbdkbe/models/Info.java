package czdbdk.dbdkbe.models;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Tereza Holm
 */
@Data
public class Info {
    private Long totalBooks;
    private LocalDate dateOfLastBookAddition;

    public Info(Long totalBooks, LocalDate dateOfLastBookAddition) {
        this.dateOfLastBookAddition = dateOfLastBookAddition;
        this.totalBooks = totalBooks;
    }
}
