package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/boss")
@RequiredArgsConstructor
public class BossController {

    private final BossService bossService;

    @GetMapping("/{bossName}")
    public ResponseEntity<Boss> getAllDropsByBossName(@PathVariable String bossName){
        return ResponseEntity.ok(bossService.getAllDropsByBossName(bossName));
    }

    @PutMapping
    public ResponseEntity<Boss> addNewBoss(@RequestBody Boss boss){
        log.info("Adding new boss " + boss);
        return ResponseEntity.ok(bossService.createNewBoss(boss));

    }


    @PutMapping("/{bossName}/item")
    public ResponseEntity<String> addNewDropForBoss(@PathVariable String bossName, @RequestBody Item newItemDrop){
        log.info("Adding a new item for " + bossName + " with information " + newItemDrop);
        return ResponseEntity.ok("temp response");
    }


    @ExceptionHandler
    public ResponseEntity<String> handleDatabaseFailures(RuntimeException runtimeException){
        return ResponseEntity.internalServerError().body(runtimeException.getMessage());
    }
}
