DELETE FROM bossdrops.boss
WHERE bossdrops.name = :bossName
AND   bossdrops.league_id = :leagueId::UUID