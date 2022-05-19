package com.poebossdrops.drops;

import com.poebossdrops.dto.LoggedKill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DropService {

    private final DropRepository dropRepository;

    @Transactional
    public LoggedKill logKill(LoggedKill loggedKill) {
        dropRepository.insertNewKill(loggedKill);
        return dropRepository.getMostRecentKillByUserBossDate(loggedKill.getBossId(), loggedKill.getAppUserId(), loggedKill.getKillDate());
    }
}
