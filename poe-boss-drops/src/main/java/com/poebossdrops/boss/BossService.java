package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import com.poebossdrops.exception.BadBossRequestException;
import com.poebossdrops.items.ItemRepository;
import com.poebossdrops.league.LeagueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BossService {
    private final BossRepository bossRepository;
    private final ItemRepository itemRepository;
    private final LeagueRepository leagueRepository;


    public List<Item> getAllDropsByBossId(UUID bossId) {
        return bossRepository.getAllDropsByBossId(bossId);
    }

    @Transactional
    public Boss createNewBoss(Boss boss) {
        UUID leagueId = leagueRepository.getCurrentLeague().getLeagueId();
        UUID bossId = bossRepository.insertNewBoss(boss, leagueId);
        return bossRepository.getBossByBossId(bossId);
    }

    public List<Boss> getAllBossesForCurrentLeague() {
        UUID leagueId = leagueRepository.getCurrentLeague().getLeagueId();
        return bossRepository.getAllBossesByLeagueId(leagueId);
    }

    public List<Boss> getAllBossesLeague(String leagueName) {
        UUID leagueId = leagueRepository.getLeagueByName(leagueName).getLeagueId();
        return leagueId != null ? bossRepository.getAllBossesByLeagueId(leagueId) : Collections.emptyList();
    }

    @Transactional
    public List<Item> addNewDropForBoss(UUID bossId, Item newItemDrop) {
        Boss boss = bossRepository.getBossByBossId(bossId);
        if (boss == null) {
            return Collections.emptyList();
        }

        List<Item> bossItemPool = bossRepository.getAllDropsByBossId(bossId);
        if (bossAlreadyHasDropWithSameName(bossItemPool, newItemDrop)) {
            throw new BadBossRequestException("A drop with that name already exists for the requested boss.");
        }

        itemRepository.insertNewItem(bossId, newItemDrop.getItemName());
        return bossRepository.getAllDropsByBossId(bossId);
    }

    private boolean bossAlreadyHasDropWithSameName(List<Item> bossItemPool, Item newItemDrop) {
        for (Item bossItem : bossItemPool) {
            if (bossItem.getItemName().equalsIgnoreCase(newItemDrop.getItemName())) {
                return true;
            }
        }

        return false;
    }
}
