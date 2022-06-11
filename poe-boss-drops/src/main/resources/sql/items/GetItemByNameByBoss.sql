SELECT item.item_id itemId,
       item.boss_id bossId,
       item.item_name itemName
FROM bossdrops.item
     LEFT JOIN bossdrops.boss
     ON item.boss_id = boss.boss_id
WHERE item.item_name = :itemName
AND   boss.league_id = f_get_current_league()
AND   boss.boss_name = :bossName
