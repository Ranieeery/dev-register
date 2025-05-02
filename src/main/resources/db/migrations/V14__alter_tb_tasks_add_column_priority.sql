CREATE TYPE task_priority AS ENUM ('CRITICAL','URGENT','HIGH','MEDIUM','LOW','TRIVIAL');

ALTER TABLE tb_tasks ADD COLUMN priority task_status;

ALTER TABLE tb_tasks RENAME COLUMN creation_date TO created_at