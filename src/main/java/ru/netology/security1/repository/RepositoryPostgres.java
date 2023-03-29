package ru.netology.security1.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositoryPostgres {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String query;

    public RepositoryPostgres(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<String> getProductName(String name) {
        query = "SELECT product_name from ddl.ORDERS where customer_id in (\n" +
                "SELECT id from ddl.CUSTOMERS where name=:name);";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("name", name);
        return namedParameterJdbcTemplate.queryForList(query, paramMap, String.class);
    }

    public List<String> getProducts() {
        query = "SELECT product_name from ddl.ORDERS;";
        Map<String, String> paramMap = new HashMap<String, String>();
        return namedParameterJdbcTemplate.queryForList(query, paramMap, String.class);
    }
}