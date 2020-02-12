package com.github.persistence;

import com.github.modal.Product;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ProductMapper implements ResultSetMapper<Product> {

    @Override
    public Product map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        final ResultSetMetaData meta = resultSet.getMetaData();
        Product product = new Product();
        for(int i = 1; i <= meta.getColumnCount(); i++) {
            String columnName = meta.getColumnName(i);
            setProperty(product, columnName, resultSet.getObject(i));
        }
        return product;
    }

    private void setProperty(Product product, String propertyName, Object propertyValue) {
        switch (propertyName) {
            case "productId":
                product.setProductId((int) propertyValue);
                break;
            case "productName":
                product.setProductName((String) propertyValue);
                break;
            case "productDesc":
                product.setProductDesc((String) propertyValue);
                break;
            case "productPrice":
                product.setProductPrice(((BigDecimal) propertyValue).doubleValue());
                break;
            case "productImagePath":
                product.setProductImagePath((String) propertyValue);
                break;
        }
    }
}
