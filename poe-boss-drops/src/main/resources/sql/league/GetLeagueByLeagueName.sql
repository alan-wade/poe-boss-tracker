SELECT league_id leagueId,
       league_name leagueName,
       patch patch,
       league_begin_date leagueBeginDate,
       league_end_date leagueEndDate
FROM bossdrops.league
WHERE league_name = :leagueName