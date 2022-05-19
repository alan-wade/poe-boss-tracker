package com.poebossdrops.drops;

import com.poebossdrops.dto.LoggedKill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DropRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LoggedKill getMostRecentKillByUserBossDate(UUID bossId, UUID appUserID, Timestamp killDate) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossId", bossId.toString());
        sqlParams.put("appUserId", appUserID.toString());

        try{
            File sqlFile = new ClassPathResource("sql/drops/GetMostRecentKillByUserBoss.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(LoggedKill.class)).get(0);
        } catch (Exception exception) {
            log.error("Error while trying to retrieve logged kill");
            throw new RuntimeException(exception.getMessage());
        }
    }

    public void insertNewKill(LoggedKill loggedKill) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossId", loggedKill.getBossId().toString());
        sqlParams.put("appUserId", loggedKill.getAppUserId().toString());

        try{
            File sqlFile = new ClassPathResource("sql/drops/InsertKill.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            jdbcTemplate.update(sql, sqlParams);
        } catch (Exception exception) {
            log.error("Error while trying to log new kill");
            throw new RuntimeException(exception.getMessage());
        }
    }

    //public List<Item> getDropsByBossUUID(UUID bossId) {
    //}
}
