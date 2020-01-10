package com.github.persistence;

import com.github.modal.Account;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface AccountDAO {

    @Mapper(AccountMapper.class)
    @SqlQuery("SELECT accountId, accountEmail, accountRole FROM account")
    List<Account> selectAllAccounts();

    @SqlUpdate("INSERT INTO account VALUES (null, :accountEmail, :accountPassword, :accountRole)")
    void insertAccount(@BindBean Account account);

    @SqlUpdate("UPDATE account SET accountEmail = :accountEmail, accountRole = :accountRole")
    void updateAccount(@BindBean Account account);

    @SqlUpdate("DELETE FROM account WHERE accountId = :accountId")
    void deleteAccount(@Bind int accountId);

    @SqlQuery("SELECT count(*) FROM account WHERE accountEmail = :email")
    int selectAccountWithEmail(@Bind("email") String accountEmail);
}