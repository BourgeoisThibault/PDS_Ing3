package pds.esibank.dataaccess.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.Account;
import pds.esibank.dataaccess.entities.Card;

import java.util.List;

public interface CardRepository extends CrudRepository<Card,Long> {
    Card findOne(Long aLong);

    List<Card> findAll();



}
