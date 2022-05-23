package com.poebossdrops.drops;

import com.poebossdrops.dto.LoggedDrop;
import com.poebossdrops.dto.LoggedKill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DropService {

    private final DropRepository dropRepository;

    @Transactional
    public LoggedKill logKill(LoggedKill loggedKill) {
        dropRepository.insertNewKill(loggedKill);
        return dropRepository.getMostRecentKillByUserBossDate(loggedKill.getBossId(), loggedKill.getAppUserId());
    }

    public List<LoggedDrop> logDrop(LoggedDrop loggedDrop) {
        dropRepository.insertNewDrop(loggedDrop);
        return dropRepository.getAllDropsForKill(loggedDrop.getLoggedKillId());
    }
}
