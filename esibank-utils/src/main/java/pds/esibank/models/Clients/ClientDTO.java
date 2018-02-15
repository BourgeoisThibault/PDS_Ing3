package pds.esibank.models.Clients;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Abbdoulaye on 08/02/2018.
 */
@Data
@Builder
public class ClientDTO {

    private Long id_client;

    private String lastName;

    private String firstName;

    private String profession;

    public ClientDTO() {
    }

    public ClientDTO(Long id_client, String lastName, String firstName, String profession) {
        this.id_client = id_client;
        this.lastName = lastName;
        this.firstName = firstName;
        this.profession = profession;
    }

}

