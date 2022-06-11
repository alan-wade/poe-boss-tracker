package com.poebossdrops.drops;

import com.poebossdrops.dto.KillDrop;
import com.poebossdrops.dto.KillLog;
import com.poebossdrops.dto.LoggedDrop;
import com.poebossdrops.dto.LoggedKill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DropRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LoggedKill getMostRecentKillByBossUserDate(UUID bossId, UUID appUserId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossId", bossId.toString());
        sqlParams.put("appUserId", appUserId.toString());

        try{
            InputStream sqlInputStream = new ClassPathResource("sql/drops/GetMostRecentKillByBossUser.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(LoggedKill.class)).get(0);
        } catch (Exception exception) {
            log.error("Error while trying to retrieve logged kill");
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<LoggedKill> getAllKillsByBossUser(UUID bossId, UUID appUserId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossId", bossId.toString());
        sqlParams.put("appUserId", appUserId.toString());

        try{
            InputStream sqlInputStream = new ClassPathResource("sql/drops/GetAllKillsByBossUser.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(LoggedKill.class));
        } catch (Exception exception) {
            log.error("Error while trying to retrieve logged kills of boss " + bossId + " for user " + appUserId);
            throw new RuntimeException(exception.getMessage());
        }
    }

    public void insertNewKill(LoggedKill loggedKill) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossId", loggedKill.getBossId().toString());
        sqlParams.put("appUserId", loggedKill.getAppUserId().toString());

        try{
            InputStream sqlInputStream = new ClassPathResource("sql/drops/InsertKill.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            jdbcTemplate.update(sql, sqlParams);
        } catch (Exception exception) {
            log.error("Error while trying to log new kill");
            throw new RuntimeException(exception.getMessage());
        }
    }

    public void insertNewDrop(LoggedDrop loggedDrop) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("itemId", loggedDrop.getItemId().toString());
        sqlParams.put("loggedKillId", loggedDrop.getLoggedKillId().toString());
        sqlParams.put("itemValue", loggedDrop.getItemValue().toString());

        try{
            InputStream sqlInputStream = new ClassPathResource("sql/drops/InsertDrop.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            jdbcTemplate.update(sql, sqlParams);
        } catch (Exception exception) {
            log.error("Error while trying to log new drop");
            throw new RuntimeException(exception.getMessage());
        }
    }

    public UUID insertNewKill(UUID appUserId, KillLog killLog) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("bossId", killLog.getBossId().toString());
        sqlParams.put("appUserId", appUserId.toString());

        try{
            InputStream sqlInputStream = new ClassPathResource("sql/drops/InsertKill.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(sql, new MapSqlParameterSource(sqlParams), generatedKeyHolder);
            return UUID.fromString(generatedKeyHolder.getKeys().get("logged_kill_id").toString());
        } catch (Exception exception) {
            log.error("Error while trying to log new kill");
            throw new RuntimeException(exception.getMessage());
        }
    }
    public void insertNewDrop(KillDrop killDrop, UUID killLogId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("itemId", killDrop.getItemId().toString());
        sqlParams.put("loggedKillId", killLogId.toString());
        sqlParams.put("itemValue", killDrop.getItemValue().toString());

        for( int i = 0; i < killDrop.getCount(); i++) {
            try {
                InputStream sqlInputStream = new ClassPathResource("sql/drops/InsertDrop.sql").getInputStream();
                String sql = new String(sqlInputStream.readAllBytes());
                jdbcTemplate.update(sql, sqlParams);
            } catch (Exception exception) {
                log.error("Error while trying to log new drop");
                throw new RuntimeException(exception.getMessage());
            }
        }
    }

    public List<LoggedDrop> getAllDropsForKill(UUID loggedKillId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("loggedKillId", loggedKillId.toString());

        try{
            InputStream sqlInputStream = new ClassPathResource("sql/drops/GetAllDropsByKill.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(LoggedDrop.class));
        } catch (Exception exception){
            log.error("Error while trying to get drops for killId" + loggedKillId);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
