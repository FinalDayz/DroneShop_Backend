package com.github.persistence;

import com.github.modal.Order;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface OrderDAO {

    @SqlUpdate("INSERT INTO order (orderId, accountId) VALUES (orderId, accountId)")
    public void addOrder(@BindBean Order order);
}
