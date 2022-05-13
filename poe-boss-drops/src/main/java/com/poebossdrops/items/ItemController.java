package com.poebossdrops.items;

import com.poebossdrops.dto.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PutMapping("/{bossName}")
    public ResponseEntity<Item> addNewItemForBoss(@PathVariable String bossName, @RequestBody Item item) {
        log.info("Adding new item to available drop pool for " + bossName + " with information" + item.getItemName());
        return ResponseEntity.ok(itemService.addNewItem(bossName, item.getItemName()));
    }

    @ExceptionHandler
    public ResponseEntity<String> handleDatabaseFailures(RuntimeException runtimeException){
        return ResponseEntity.internalServerError().body(runtimeException.getMessage());
    }

}
