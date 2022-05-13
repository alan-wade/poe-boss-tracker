package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.League;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BossRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public Boss getAllDropsByBossName(String bossName) {

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
}
