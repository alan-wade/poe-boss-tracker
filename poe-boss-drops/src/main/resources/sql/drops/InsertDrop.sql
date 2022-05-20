INSERT INTO logged_drop (item_id, logged_kill_id, item_value)
VALUES (:itemId::UUID, :loggedKillId::UUID, TO_NUMBER(:itemValue,'999G999G999.99'))