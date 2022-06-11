package com.poebossdrops.drops;

import com.poebossdrops.dto.KillLog;
import com.poebossdrops.dto.LoggedDrop;
import com.poebossdrops.dto.LoggedKill;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/drops")
@RequiredArgsConstructor
public class DropController {

    private final DropService dropService;

    @PostMapping("/kill/log")
    public ResponseEntity<LoggedKill> logKill(@RequestBody LoggedKill loggedKill) {
        log.info("Logging record of new kill for bossId " + loggedKill.getBossId().toString());
        return ResponseEntity.ok(dropService.logKill(loggedKill));
    }

    @GetMapping("/kill/list/{bossId}")
    public ResponseEntity<List<LoggedKill>> getAllKillsByUserBoss(@PathVariable String bossId, @RequestHeader String appUserId) {
        return ResponseEntity.ok(dropService.getAllKillsByBossUser(bossId, appUserId));
    }

    @PostMapping("/kill/log/drop")
    public ResponseEntity<List<LoggedDrop>> logDrop(@RequestBody LoggedDrop loggedDrop) {
        log.info("Logging record of new kill for loggedKillId " + loggedDrop.getLoggedKillId().toString());
        return ResponseEntity.ok((dropService.logDrop(loggedDrop)));
    }

    @PutMapping("/kills")
    @Tag(name = "log", description = "Logs a kill for a user including all the drops for the boss was slain")
    public ResponseEntity<KillLog> logKillDetails(@RequestHeader UUID appUserId, @RequestBody KillLog killLog) {
        return ResponseEntity.ok(dropService.logKill(appUserId, killLog));
    }
}
