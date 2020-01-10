package com.github.persistence;

import com.github.modal.Account;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AccountMapper implements ResultSetMapper<Account> {

    @Override
    public Account map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        final ResultSetMetaData meta = resultSet.getMetaData();
        Account account = new Account();

        for(int i = 0; i < meta.getColumnCount(); i++) {
            String columnName = meta.getColumnClassName(i);
            setProperty(account, columnName, resultSet.getObject(i));
        }
        return account;
    }

    private void setProperty(Account account, String propertyName, Object propertyValue) {
        switch (propertyName) {
            case "accountId":
                account.setAccountId((int) propertyValue);
                break;
            case "accountEmail":
                account.setAccountEmail((String) propertyValue);
                break;
            case "accountPassword":
                account.setAccountPassword((String) propertyValue);
                break;
            case "accountRole":
                account.setAccountRole((String) propertyValue);
                break;
        }
    }
}