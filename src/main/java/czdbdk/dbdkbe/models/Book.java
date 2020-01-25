package czdbdk.dbdkbe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import czdbdk.dbdkbe.jview.DataView;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Tereza Holm
 */
@Entity(name = "book")
@Data
public class Book extends GeneralBook implements Serializable{

    @JsonView(DataView.SummaryView.class)
    private String slug;
    /*
    @Column(name = "image")
    @JsonView(DataView.SummaryView.class)
    private String imageURL;
    */

}
