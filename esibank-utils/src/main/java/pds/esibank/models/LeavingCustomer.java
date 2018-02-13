package pds.esibank.models;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
public class LeavingCustomer {

    private int lpId;

    private BigDecimal lpY;

    private BigDecimal balance;

    private BigDecimal csp;

    private int transactions;

    private int connections;

    private int transfers;

    private BigDecimal charge;

    private int c_appreciation;

    public LeavingCustomer(){
        super();
    }

    public String [] toStringArray()
    {
        String[] ObjString = {String.valueOf(this.lpY), String.valueOf(this.balance),  String.valueOf(this.csp),
                String.valueOf(this.transactions), String.valueOf(this.connections), String.valueOf(this.transfers),
                String.valueOf(this.charge), String.valueOf(this.c_appreciation)};
        return ObjString;
    }
}
