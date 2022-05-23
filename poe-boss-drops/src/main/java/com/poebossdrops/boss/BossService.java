package com.poebossdrops.boss;

import com.poebossdrops.drops.DropRepository;
import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import com.poebossdrops.league.LeagueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BossService {
    private final BossRepository bossRepository;
    private final DropRepository dropRepository;
    private final LeagueRepository leagueRepository;

    public List<Item> getAllDropsByBossName (String bossName) {
        UUID leagueId = leagueRepository.getCurrentLeague().getLeagueId();
        return bossRepository.getAllDropsByBossName(bossName, leagueId);
    }

    @Transactional
    public Boss createNewBoss(Boss boss) {
        UUID leagueId = leagueRepository.getCurrentLeague().getLeagueId();
        bossRepository.insertNewBoss(boss, leagueId);
        return bossRepository.getBossByName(boss.getBossName(), leagueId);
    }

    public List<Boss> getAllBossesForCurrentLeague() {
        UUID leagueId = leagueRepository.getCurrentLeague().getLeagueId();
        return bossRepository.getAllBossesForCurrentLeague(leagueId);
    }
}
