package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import com.poebossdrops.exception.BadBossRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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

    public Boss getBossByBossId(UUID bossId) {
        Map<String, UUID> sqlParams = new HashMap<>();
        sqlParams.put("bossId", bossId);

        try {
            File sqlFile = new ClassPathResource("sql/boss/GetBossById.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.queryForObject(sql, sqlParams, new BeanPropertyRowMapper<>(Boss.class));
        } catch (EmptyResultDataAccessException emptyEx) {
            log.warn("Unable to find boss with id " + bossId);
            throw new BadBossRequestException("Boss does not exist for id " + bossId);
        } catch (Exception exception) {
            log.error("Error while trying to get " + bossId);
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<Item> getAllDropsByBossId(UUID bossId) {
        Map<String, UUID> sqlParams = new HashMap<>();
        sqlParams.put("bossId", bossId);

        try {
            File sqlFile = new ClassPathResource("sql/boss/GetAllDropsByBossId.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(Item.class));
        } catch (Exception exception) {
            log.error("Error while trying to get " + bossId);
            throw new RuntimeException(exception.getMessage());
        }
    }

    public UUID insertNewBoss(Boss boss, UUID leagueId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("leagueId", leagueId.toString());
        sqlParams.put("bossName", boss.getBossName());

        try {
            File sqlFile = new ClassPathResource("sql/boss/InsertBoss.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(sql, new MapSqlParameterSource(sqlParams), generatedKeyHolder);
            return UUID.fromString(generatedKeyHolder.getKeys().get("boss_id").toString());
        } catch (Exception exception) {
            log.error("Error while trying to create a new boss " + boss);
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<Boss> getAllBossesByLeagueId(UUID leagueId) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("leagueId", leagueId.toString());

        try {
            File sqlFile = new ClassPathResource("sql/boss/GetAllBossesByLeague.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.query(sql, sqlParams, new BeanPropertyRowMapper<>(Boss.class));
        } catch (Exception exception) {
            log.error("Error while trying to list all current league bosses");
            throw new RuntimeException(exception.getMessage());
        }
    }
}
