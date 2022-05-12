package com.poebossdrops.boss;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/boss")
public class BossController {

    @GetMapping
    public ResponseEntity<String> addNewBoss(){
        return ResponseEntity.ok("Hi David");
    }
}
