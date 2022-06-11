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
@Tag(name = "log", description = "Used for interacting with kill logs for users")
public class DropController {

    private final DropService dropService;

    @GetMapping("/kill/list/{bossId}")
    @Tag(name = "log", description = "Gets all the kills and associated drops for a given user and boss")
    public ResponseEntity<List<LoggedKill>> getAllKillsByUserBoss(@PathVariable String bossId, @RequestHeader String appUserId) {
        return ResponseEntity.ok(dropService.getAllKillsByBossUser(bossId, appUserId));
    }

    @PutMapping("/kills")
    @Tag(name = "log", description = "Logs a kill for a user including all the drops for the boss was slain")
    public ResponseEntity<KillLog> logKillDetails(@RequestHeader UUID appUserId, @RequestBody KillLog killLog) {
        return ResponseEntity.ok(dropService.logKill(appUserId, killLog));
    }
}
