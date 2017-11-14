package pds.esibank.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     14:49
 */
@Data
@ToString
public class LdapUser {
    private String uid;
    private String firstname;
    private String lastname;
    private String mail;
    private String description;
    private String position;
    private String phone;
    private String company;

    public LdapUser() {

    }

    public LdapUser(String uid, String firstname, String lastname, String position) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
    }
}
