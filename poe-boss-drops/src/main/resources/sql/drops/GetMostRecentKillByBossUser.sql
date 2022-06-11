SELECT  logged_kill_id
    ,   boss_id
    ,   app_user_id
    ,   kill_date
FROM bossdrops.logged_kill
WHERE app_user_id = :appUserId::UUID
AND   boss_id = :bossId::UUID
AND   kill_date = (SELECT MAX(kill_date) FROM bossdrops.logged_kill b WHERE b.app_user_id = :appUserId::UUID AND b.boss_id = :bossId::UUID)
