ALTER TABLE tb_tasks ADD active BOOLEAN;

UPDATE tb_tasks SET active = true;
