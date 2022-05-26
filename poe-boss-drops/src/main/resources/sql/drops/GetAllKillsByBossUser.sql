SELECT  logged_kill_id
    ,   boss_id
    ,   app_user_id
    ,   kill_date
FROM bossdrops.logged_kill
WHERE   boss_id = :bossId::UUID
AND     app_user_id = :appUserId::UUID
ORDER BY kill_date DESC