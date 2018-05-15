package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.crypto.MySHA;
import pds.esibank.dataaccess.entities.Account;
import pds.esibank.dataaccess.entities.Card;
import pds.esibank.dataaccess.entities.Customer;
import pds.esibank.dataaccess.entities.Status;
import pds.esibank.dataaccess.entities.payfree.LinkCardToAccount;
import pds.esibank.dataaccess.repositories.AccountRepository;
import pds.esibank.dataaccess.repositories.CardRepository;
import pds.esibank.dataaccess.repositories.CustomerRepository;
import pds.esibank.dataaccess.repositories.payfree.CardRepo;
import pds.esibank.dataaccess.repositories.payfree.LinkCardRepo;
import pds.esibank.models.dab.AccountDto;
import pds.esibank.models.dab.CardDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final LinkCardRepo linkCardRepo;

    @Autowired
    private final CardRepo cardRepo;


    @Autowired
    public AccountService(AccountRepository accountRepository, CardRepository cardRepository, CustomerRepository customerRepository, LinkCardRepo linkCardRepo, CardRepo cardRepo) {
        this.cardRepository = cardRepository;
        this.accountRepository=accountRepository;
        this.customerRepository=customerRepository;
        this.linkCardRepo = linkCardRepo;
        this.cardRepo = cardRepo;
    }

    public Account getAccoutById(long id_account){
        return accountRepository.getAnAccountByAccountId(id_account);
    }

   public List<AccountDto> getListAccountByCardId(long card_id){
        return accountRepository.getAccountByAccountId(card_id)
                .stream()
                .map(
                        u -> AccountDto.builder()
                                .account_id(String.valueOf(u.getId_account()))
                                .amount(String.valueOf(u.getSold()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    public AccountDto createAccount(AccountDto accountDto){
        Account a = new Account();
        a.setSold(Float.valueOf(accountDto.getAmount()));
        a.setCard_id_fk(1);
        a.setCustomer(createCustomer());
        System.out.println(a.toString());
        a =  accountRepository.save(a);
        return AccountDto.builder()
                .account_id(String.valueOf(a.getId_account()))
                .amount(String.valueOf(a.getSold()))
                .build();
    }

    public Customer createCustomer(){
    Customer c = new Customer();
    c.setAddress("");
    c.setFirstName("");
    Status s = new Status();
    long z = 1;
    s.setId_status(z);
    s.setLabel("");
    c.setStatus(s);
        return customerRepository.save(c);
    }

    public CardDto createCard(CardDto cardDto){
        Card c = new Card();
        c.setCardNum(cardDto.getCardNum());
        System.out.println("card pass : "+cardDto.getCardPass());

        String val = MySHA.passToSHA(cardDto.getCardPass(), "SHA-512");
        c.setCardPass(val);

        c = cardRepo.save(c);
        LinkCardToAccount l = new LinkCardToAccount();
        l.setCard(c);
        l.setAccount(getAccoutById(c.getCard_id()));
        linkCardRepo.save(l);
        CardDto cardDto1 = new CardDto();
        cardDto1.setCardNum(c.getCardNum());
        cardDto1.setCard_id(c.getCard_id());
        return cardDto1;
//        return CardDto.builder()
//                .cardNum(c.getCardNum())
//                .card_id(c.getCard_id())
//                .build();
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }



    public String removeLinkAccountToCard(String card_num) throws Exception{
        LinkCardToAccount l = linkCardRepo.getByCard_CardNum(card_num);
        linkCardRepo.delete(l);
        return "ok";
    }

    public String removeAccount(long id_account) throws Exception{
        accountRepository.delete(id_account);
        customerRepository.delete(id_account);

        return "ok";
    }

    public String removeCard(long card_id) throws Exception{
        cardRepository.delete(card_id);
        return "ok";
    }
}
