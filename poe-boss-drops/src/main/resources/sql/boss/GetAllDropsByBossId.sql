SELECT item.item_id itemId,
       item.boss_id bossId,
       item.item_name itemName
FROM bossdrops.item
     LEFT JOIN bossdrops.boss
     ON item.boss_id = boss.boss_id
WHERE boss.boss_id = :bossId