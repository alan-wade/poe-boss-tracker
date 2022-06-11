package com.poebossdrops.league;

import com.poebossdrops.dto.League;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Files;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LeagueRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public League getCurrentLeague() {
        try {
            File sqlFile = new ClassPathResource("sql/league/GetCurrentLeague.sql").getFile();
            String sql = new String(Files.readAllBytes(sqlFile.toPath()));
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(League.class)).get(0);
        } catch (Exception exception) {
            log.error("Things broke getting the current league");
            throw new RuntimeException(exception.getMessage());
        }
    }
}
