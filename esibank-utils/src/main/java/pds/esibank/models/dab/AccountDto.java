package pds.esibank.models.dab;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AccountDto {

    private String account_id;
    private String amount;
}
