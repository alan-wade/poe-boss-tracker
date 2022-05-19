package com.poebossdrops.drops;

import com.poebossdrops.dto.LoggedKill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{bossName}")
    public String helloWorld(@PathVariable String bossName){
        return "Hello World " + bossName;
    }
}
