package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.league.LeagueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BossService {
    private final BossRepository bossRepository;
    private final LeagueRepository leagueRepository;

    public Boss getAllDropsByBossName(String bossName) {
        return bossRepository.getAllDropsByBossName(bossName);
    }

    public Boss createNewBoss(Boss boss) {
        UUID leagueId = leagueRepository.getCurrentLeague().getLeagueId();
        bossRepository.insertNewBoss(boss, leagueId);
        return bossRepository.getBossByName(boss.getBossName());
    }
}
