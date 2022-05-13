INSERT INTO item (boss_id, item_name)
VALUES ((SELECT boss_id FROM boss WHERE league_id = f_get_current_league() AND boss_name = :bossName), :itemName)