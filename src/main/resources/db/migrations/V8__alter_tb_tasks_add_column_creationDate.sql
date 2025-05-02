ALTER TABLE tb_tasks ADD COLUMN creation_date DATE;

UPDATE tb_tasks SET creation_date = CURRENT_DATE;