package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.League;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BossRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public Boss getBossByName(String bossName) {
        return jdbcTemplate.queryForObject("Select * from bossdrops.boss", new HashMap<>() ,Boss.class);
    }

    public Boss getAllDropsByBossName(String bossName) {

        List<Boss> boss =  jdbcTemplate.queryForList("Select * from bossdrops.boss", new HashMap<>(), Boss.class);
        return Boss.builder()
                .bossId(UUID.randomUUID())
                .bossName(bossName)
                .league(League.builder()
                        .leagueId(UUID.randomUUID())
                        .name("Sentinel")
                        .patch("3.19")
                        .build())
                .drops(new ArrayList<>())
                .build();
    }

    public void insertNewBoss(Boss boss, UUID leagueId) {


    }
}
