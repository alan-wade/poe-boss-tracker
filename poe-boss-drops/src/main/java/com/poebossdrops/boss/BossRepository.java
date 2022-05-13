package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
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
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BossRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Boss getBossByName(String bossName, UUID leagueId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("leagueId", leagueId.toString());
        sqlParams.put("bossName", bossName);

        try{
            File sqlFile = new ClassPathResource("sql/boss/GetAllBossesByNameAndLeague.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(Boss.class)).get(0);
        } catch (Exception exception){
            log.error("Error while trying to get " + bossName);
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<Item> getAllDropsByBossName(String bossName) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossName", bossName);

        try{
            File sqlFile = new ClassPathResource("sql/boss/GetAllDropsByBossName.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(Item.class));
        } catch (Exception exception){
            log.error("Error while trying to get " + bossName);
            throw new RuntimeException(exception.getMessage());
        }

    }

    public void insertNewBoss(Boss boss, UUID leagueId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("leagueId", leagueId.toString());
        sqlParams.put("bossName", boss.getBossName());

        try{
            File sqlFile = new ClassPathResource("sql/boss/InsertBoss.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            jdbcTemplate.update(sql, sqlParams);
        } catch (Exception exception){
            log.error("Error while trying to create a new boss " + boss);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
