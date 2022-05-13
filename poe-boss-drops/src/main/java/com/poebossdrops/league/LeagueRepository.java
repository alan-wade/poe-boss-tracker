package com.poebossdrops.league;

import com.poebossdrops.dto.League;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LeagueRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    public League getCurrentLeague(){
        return jdbcTemplate.query("", new BeanPropertyRowMapper<>(League.class)).get(0);
    }
}
