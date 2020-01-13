package com.github.persistence;

import com.github.modal.ProductOrder;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ProductOrderDAO {

    @SqlUpdate("INSERT INTO product_order (orderId, productId) VALUES (:orderId, :productId)")
    public void addProductOrder(@BindBean ProductOrder productOrder);
}
