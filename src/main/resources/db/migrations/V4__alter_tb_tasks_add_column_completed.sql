ALTER TABLE tb_tasks ADD completed BOOLEAN;

UPDATE tb_tasks SET completed = false;