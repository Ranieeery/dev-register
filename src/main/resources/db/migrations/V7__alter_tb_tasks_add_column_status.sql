CREATE TYPE task_status AS ENUM ('TO_DO', 'IN_PROGRESS', 'COMPLETED', 'ON_HOLD');

ALTER TABLE tb_tasks ADD COLUMN status task_status;