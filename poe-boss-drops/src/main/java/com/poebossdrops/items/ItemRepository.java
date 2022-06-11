package com.poebossdrops.items;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void insertNewItem(UUID bossId, String itemName) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossId", bossId.toString());
        sqlParams.put("itemName", itemName);

        try{
            InputStream sqlInputStream = new ClassPathResource("sql/items/InsertItem.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            jdbcTemplate.update(sql, sqlParams);
        } catch (Exception exception){
            log.error("Error while trying to create a new item " + itemName);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
