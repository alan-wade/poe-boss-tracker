package com.poebossdrops.league;

import com.poebossdrops.dto.League;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LeagueRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public League getCurrentLeague() {
        try {
            InputStream sqlInputStream = new ClassPathResource("sql/league/GetCurrentLeague.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(League.class)).get(0);
        } catch (Exception exception) {
            log.error("Things broke getting the current league");
            throw new RuntimeException(exception.getMessage());
        }
    }

    public League getLeagueByName(String leagueName) {
        Map<String, String> sqlParams = new HashMap<>();
        sqlParams.put("leagueName", leagueName);

        try {
            InputStream sqlInputStream = new ClassPathResource("sql/league/GetLeagueByLeagueName.sql").getInputStream();
            String sql = new String(sqlInputStream.readAllBytes());
            return jdbcTemplate.queryForObject(sql, sqlParams, new BeanPropertyRowMapper<>(League.class));
        } catch (EmptyResultDataAccessException emptyResultException) {
            log.warn("Unable to find requested league " + leagueName);
            return new League();
        } catch (Exception exception) {
            log.error("Things broke getting league " + leagueName);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
