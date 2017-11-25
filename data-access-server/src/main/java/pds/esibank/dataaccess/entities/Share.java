package pds.esibank.dataaccess.entities;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="shares")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "share_id" )
    private int shareId;

    @Column( name = "name" )
    private String name;

    @Column( name = "symbol" )
    private String symbol;

    @Column( name = "currency" )
    private String currency;

    @OneToMany(mappedBy="share")
    private Collection<SharePrice> sharePriceCollection ;

    public Share()
    {
        super();
    }

}
