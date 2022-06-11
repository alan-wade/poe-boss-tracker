package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/boss")
@RequiredArgsConstructor
@Tag(name = "boss", description = "Used for all interactions with boss entities. Adding drops to pool, ")
public class BossController {
    private final BossService bossService;

    @GetMapping("/{bossName}")
    @Tag(name = "boss", description = "Get bosses for a league, providing no league name will use the current date to find the active league bosses")
    public ResponseEntity<List<Item>> getAllDropsByBossName(@PathVariable String bossName){
        return ResponseEntity.ok(bossService.getAllDropsByBossName(bossName));
    }

    @GetMapping("/league")
    @Tag(name = "boss", description = "Get bosses for a league, providing no league name will use the current date to find the active league bosses")
    public ResponseEntity<List<Boss>> getAllBossesForLeague(@RequestParam(required = false, defaultValue = "") String leagueName) {
        if(StringUtils.isNoneBlank(leagueName)){
            return ResponseEntity.ok(bossService.getAllBossesLeague(leagueName));
        }

        return ResponseEntity.ok(bossService.getAllBossesForCurrentLeague());
    }

    @PutMapping
    @Tag(name = "boss", description = "Get bosses for a league, providing no league name will use the current date to find the active league bosses")
    public ResponseEntity<Boss> addNewBoss(@RequestBody Boss boss) {
        log.info("Adding new boss " + boss.getBossName());
        return ResponseEntity.ok(bossService.createNewBoss(boss));
    }

    @PutMapping("/{bossName}/item")
    @Tag(name = "boss", description = "Adds a new item to the drop pool for a boss given")
    public ResponseEntity<String> addNewDropForBoss(@PathVariable UUID bossId, @RequestBody Item newItemDrop) {
        log.info("Adding a new drop for boss");
        return ResponseEntity.ok("temp response");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleDatabaseFailures(RuntimeException runtimeException) {
        return ResponseEntity.internalServerError().body(runtimeException.getMessage());
    }
}
