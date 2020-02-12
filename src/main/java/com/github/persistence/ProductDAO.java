package com.github.persistence;

import com.github.modal.Product;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import javax.ws.rs.Produces;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    @Mapper(ProductMapper.class)
    @SqlQuery("SELECT * FROM product")
    List<Product> selectAllProducts();

    @SqlUpdate("INSERT INTO product " +
            "VALUES " +
            "(null, :productName, :productDesc, :productPrice, :productImagePath)")
    void insertProduct(@BindBean Product product);

    @SqlUpdate("UPDATE product " +
            "SET productName = :productName, " +
            "productDesc = :productDesc, " +
            "productPrice = :productPrice, " +
            "productImagePath = :productImagePath " +
            "WHERE productId = :productId")
    void updateProduct(@BindBean Product product);

    @SqlUpdate("DELETE FROM product " +
            "WHERE productId = :productId")
    void deleteProduct(@Bind int productId);

}
