package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boss")
@RequiredArgsConstructor
public class BossController {
    private final BossService bossService;

    @GetMapping("/{bossName}")
    public ResponseEntity<List<Item>> getAllDropsByBossName(@PathVariable String bossName){
        return ResponseEntity.ok(bossService.getAllDropsByBossName(bossName));
    }

    @GetMapping("/league")
    @Tag(name = "boss", description = "Get bosses for a league, providing no league name will use the current date to find the active league bosses")
    public ResponseEntity<List<Boss>> getAllBossesForLeague(@RequestParam String name) {
        return ResponseEntity.ok(bossService.getAllBossesForCurrentLeague());
    }

    @PutMapping
    public ResponseEntity<Boss> addNewBoss(@RequestBody Boss boss) {
        log.info("Adding new boss " + boss.getBossName());
        return ResponseEntity.ok(bossService.createNewBoss(boss));
    }


    @PutMapping("/{bossName}/item")
    public ResponseEntity<String> addNewDropForBoss(@PathVariable String bossName, @RequestBody Item newItemDrop) {
        log.info("Adding a new item for " + bossName + " with information " + newItemDrop);

        return ResponseEntity.ok("temp response");
    }


    @ExceptionHandler
    public ResponseEntity<String> handleDatabaseFailures(RuntimeException runtimeException) {
        return ResponseEntity.internalServerError().body(runtimeException.getMessage());
    }
}
