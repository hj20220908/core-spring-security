package security.coreSpringSecurity.service;

import security.coreSpringSecurity.domain.dto.AccountDto;
import security.coreSpringSecurity.domain.entity.Account;

import java.util.List;

public interface UserService {

    void createUser(Account account);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long idx);
}
