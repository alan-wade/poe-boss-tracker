package com.poebossdrops.items;

import com.poebossdrops.dto.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Item> getAllItemsByBossName(String bossName) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossName", bossName);

        try{
            File sqlFile = new ClassPathResource("sql/items/GetAllItemsByBossName.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(Item.class));
        } catch (Exception exception){
            log.error("Error while trying to get " + bossName);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
