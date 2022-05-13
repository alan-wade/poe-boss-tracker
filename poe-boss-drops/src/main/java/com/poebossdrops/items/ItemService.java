package com.poebossdrops.items;

import com.poebossdrops.boss.BossRepository;
import com.poebossdrops.dto.Boss;
import com.poebossdrops.dto.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item addNewItem(String bossName, String itemName) {
        itemRepository.insertNewItem(bossName, itemName);
        return itemRepository.getItemByNameByBoss(itemName, bossName);
    }

}
