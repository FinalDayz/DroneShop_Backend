package com.github.service;

import com.github.modal.Account;
import com.github.persistence.AccountDAO;
import com.github.security.JWTUtils;
import org.apache.commons.validator.EmailValidator;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public class AccountService extends Service<AccountDAO> {

    public AccountService(AccountDAO DAO) {
        super(DAO);
    }

    public List<Account> gettAllAccounts() {
        return DAO.selectAllAccounts();
    }

    public void createAccount(Account account) throws InstanceAlreadyExistsException, InvalidInputException {

        isValidNewAccount(account);

        if (DAO.selectAccountWithEmail(account.getAccountEmail()) != 0) {
            throw new InstanceAlreadyExistsException();
        }

        account.setAccountRole("GEBRUIKER");

        DAO.insertAccount(account);
    }

    public void changeAccount(Account account) {
        DAO.updateAccount(account);
    }

    public void removeAccount(int accountId) {
        DAO.deleteAccount(accountId);
    }

    private void isValidNewAccount(Account account) throws InvalidInputException {

        EmailValidator validator = EmailValidator.getInstance();
        if(!validator.isValid(account.getAccountEmail())) {
            throw new InvalidInputException("Invalid email");
        }

        if(account.getAccountPassword().length() <= 1) {
            throw new InvalidInputException("Password must be at least 2 characters long");
        }
    }

    public String validate(Account account) {
        Account accountReturned = this.DAO.validateAccount(account);
        if(accountReturned == null) {
            return null;
        }

        return JWTUtils.creatJWT(accountReturned);
    }
}
