-- V2: Add startDate to Database

ALTER TABLE IF EXISTS tb_tasks ADD COLUMN start_date DATE;