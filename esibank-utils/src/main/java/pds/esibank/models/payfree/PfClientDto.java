package pds.esibank.models.payfree;

import lombok.Data;
import lombok.ToString;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     18:06
 */
@Data
@ToString
public class PfClientDto {
    private String pseudo;
    private String lastName;
    private String firstName;
    private String encryptedPass;
}