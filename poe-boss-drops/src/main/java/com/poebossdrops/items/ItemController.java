package com.poebossdrops.items;

import com.poebossdrops.dto.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{bossName}")
    public ResponseEntity<Item> getAllItemsByBossName(@PathVariable String bossName){
        return ResponseEntity.ok(itemService.getAllItemsByBossName(bossName));
    }

}
