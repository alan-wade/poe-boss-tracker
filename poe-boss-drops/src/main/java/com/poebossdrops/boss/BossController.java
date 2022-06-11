package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import com.poebossdrops.exception.BadBossRequestException;
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

    @GetMapping("/{bossId}")
    @Tag(name = "boss", description ="Get all drops for a boss with a given id")
    public ResponseEntity<List<Item>> getAllDropsByBossId(@PathVariable UUID bossId){
        return ResponseEntity.ok(bossService.getAllDropsByBossId(bossId));
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

    @PutMapping("/{bossId}/item")
    @Tag(name = "boss", description = "Adds a new item to the drop pool for a boss given")
    public ResponseEntity<List<Item>> addNewDropForBoss(@PathVariable UUID bossId, @RequestBody Item newItemDrop) {
        return ResponseEntity.ok(bossService.addNewDropForBoss(bossId, newItemDrop));
    }

    @ExceptionHandler
    public ResponseEntity<String> handleDatabaseFailures(RuntimeException runtimeException) {
        return ResponseEntity.internalServerError().body(runtimeException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleBadRequest(BadBossRequestException badBossRequestException){
        return ResponseEntity.badRequest().body(badBossRequestException.getMessage());
    }
}
