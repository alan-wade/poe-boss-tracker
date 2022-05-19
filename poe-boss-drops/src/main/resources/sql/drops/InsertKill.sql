INSERT INTO bossdrops.logged_kill (boss_id, app_user_id, kill_date)
VALUES ( :bossId::UUID, :appUserId::UUID, CURRENT_TIMESTAMP)