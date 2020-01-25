package czdbdk.dbdkbe.models;

import com.fasterxml.jackson.annotation.JsonView;
import czdbdk.dbdkbe.jview.DataView;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tereza Holm
 */
@Embeddable
@Data
public class Links {
    @Column(name = "link_goodreads")
    @JsonView(DataView.DetailView.class)
    private String goodreads;
    @Column(name = "link_databaze")
    @JsonView(DataView.DetailView.class)
    private String databazeKnih;
    @Column(name = "link_cbdb")
    @JsonView(DataView.DetailView.class)
    private String cbdb;
}
