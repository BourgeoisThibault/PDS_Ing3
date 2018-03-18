package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.repositories.AccountRepository;
import pds.esibank.dataaccess.repositories.CardRepository;
import pds.esibank.models.dab.AccountDto;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final CardRepository cardRepository;


    @Autowired
    public AccountService(AccountRepository accountRepository,CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository=accountRepository;
    }

    public List<AccountDto> getListAccountByCardId(long card_id){
        return accountRepository.getAccountByAccountId(card_id)
                .stream()
                .map(
                        u -> AccountDto.builder()
                                .account_id(String.valueOf(u.getAccount_id()))
                                .amount(String.valueOf(u.getAmount()))
                                .build()
                )
                .collect(Collectors.toList());
    }




}
