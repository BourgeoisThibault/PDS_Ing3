package pds.esibank.dataaccess.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pds.esibank.dataaccess.entities.Account;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {


    @Query("select a from account a where id_account = ?1")
    List<Account> getAccountByAccountId(long account_id);

    @Query("select a from account a where id_account = ?1")
    Account getAnAccountByAccountId(long account_id);


    @Query("select a from account a where card_id_fk = ?1")
    List<Account> getListAccountByCardId(long card_id);

    List<Account> findAll();

    @Query(value = "insert into account (id_account,id_customer,id_cardtype,sold,card_id_fk) VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
    Account createAccount(long id_account, long id_customer, long cardtpe, float sold, long cardfk);

    @Override
    Account save(Account a);

    @Override
    void delete(Long aLong);
}
