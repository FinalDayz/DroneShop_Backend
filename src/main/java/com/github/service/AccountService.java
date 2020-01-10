package com.github.service;

import com.github.modal.Account;
import com.github.persistence.AccountDAO;
import com.github.persistence.AccountMapper;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.apache.commons.validator.routines.EmailValidator;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public class AccountService extends Service<AccountDAO> {

    public AccountService(AccountDAO DAO) {
        super(DAO);
    }

    public List<Account> gettAllAccounts() {
        return DAO.selectAllAccounts();
    }

    public void createAccount(Account account) throws InstanceAlreadyExistsException, InvalidAccountException {

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

    private void isValidNewAccount(Account account) throws InvalidAccountException {

        EmailValidator validator = EmailValidator.getInstance();
        if(!validator.isValid(account.getAccountEmail())) {
            throw new InvalidAccountException("Invalid email");
        }

        if(account.getAccountPassword().length() <= 1) {
            throw new InvalidAccountException("Password must be at least 2 characters long");
        }

    }
}
