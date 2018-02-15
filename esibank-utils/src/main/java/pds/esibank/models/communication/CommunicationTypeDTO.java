package pds.esibank.models.communication;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Abbdoulaye on 09/02/2018.
 */
@Data
@Builder
public class CommunicationTypeDTO {
    private Long idCommunicationType;
    private String libelle;

    public CommunicationTypeDTO() {

    }
    public CommunicationTypeDTO(Long idCommunicationType, String libelle) {
        this.idCommunicationType = idCommunicationType;
        this.libelle = libelle;
    }


}
