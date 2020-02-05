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
        System.out.println("MAPPP");
        Account account = new Account();

        for(int i = 1; i <= meta.getColumnCount(); i++) {
            String columnName = meta.getColumnName(i);
            System.out.println(columnName);
            setProperty(account, columnName, resultSet.getObject(i));
        }
        return account;
    }

    private void setProperty(Account account, String propertyName, Object propertyValue) {
        System.out.println(propertyName+" " + propertyValue);
        switch (propertyName) {
            case "accountId":
                account.setAccountId((int) propertyValue);
                break;
            case "accountEmail":
                account.setAccountEmail((String) propertyValue);
                break;
            case "accountRole":
                account.setAccountRole((String) propertyValue);
                break;
        }
    }
}
