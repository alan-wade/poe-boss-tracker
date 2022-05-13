package com.poebossdrops.boss;

import com.poebossdrops.dto.Boss;
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

    @PutMapping("/{bossName}/item")
    public ResponseEntity<String> addNewDropForBoss(@PathVariable String bossName, @RequestBody String newItemDrop){
      log.info("Adding a new item for " + bossName + " with information " + newItemDrop);
      return ResponseEntity.ok("temp response");
    }

}
