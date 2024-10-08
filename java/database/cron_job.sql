CREATE EXTENSION IF NOT EXISTS pg_cron;

SELECT cron.schedule('0 0 * * *', $$UPDATE your_table
SET status = CASE 
    WHEN due_date <= CURRENT_DATE THEN TRUE
    ELSE FALSE
END;$$);