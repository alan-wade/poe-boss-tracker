SELECT boss_id bossId,
       league_id leagueId,
       boss_name bossName
FROM bossdrops.boss
WHERE boss_id = :bossId