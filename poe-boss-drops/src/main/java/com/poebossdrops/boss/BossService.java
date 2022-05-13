package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BossService {
    private final BossRepository bossRepository;

    public Boss getAllDropsByBossName(String bossName) {
        return bossRepository.getAllDropsByBossName(bossName);
    }
}
