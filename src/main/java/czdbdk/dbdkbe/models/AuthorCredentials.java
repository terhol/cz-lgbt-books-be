package czdbdk.dbdkbe.models;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tereza Holm
 */
@Data
public class AuthorCredentials implements Serializable {
    private String firstName;
    private String lastName;

    public AuthorCredentials() {
    }

    public AuthorCredentials(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
