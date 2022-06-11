package com.poebossdrops.drops;

import com.poebossdrops.dto.KillDrop;
import com.poebossdrops.dto.KillLog;
import com.poebossdrops.dto.LoggedDrop;
import com.poebossdrops.dto.LoggedKill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DropService {

    private final DropRepository dropRepository;

    public List<LoggedKill> getAllKillsByBossUser(String bossId, String appUserId) {
        return dropRepository.getAllKillsByBossUser(UUID.fromString(bossId), UUID.fromString(appUserId));
    }

    @Transactional
    public LoggedKill logKill(LoggedKill loggedKill) {
        dropRepository.insertNewKill(loggedKill);
        return dropRepository.getMostRecentKillByBossUserDate(loggedKill.getBossId(), loggedKill.getAppUserId());
    }

    public List<LoggedDrop> logDrop(LoggedDrop loggedDrop) {
        dropRepository.insertNewDrop(loggedDrop);
        return dropRepository.getAllDropsForKill(loggedDrop.getLoggedKillId());
    }

    @Transactional
    public KillLog logKill(UUID appUserId, KillLog killLog) {
        UUID loggedKillId = dropRepository.insertNewKill(appUserId, killLog);
        for (KillDrop drop: killLog.getDrops()) {
            dropRepository.insertNewDrop(drop, loggedKillId);
        }

        return killLog;
    }
}
