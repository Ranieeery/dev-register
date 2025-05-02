CREATE TYPE developer_specialization AS ENUM ('FRONTEND', 'BACKEND', 'FULLSTACK', 'MOBILE', 'DEVOPS', 'CLOUD_ENGINEERING', 'DATA_SCIENCE', 'MACHINE_LEARNING', 'EMBEDDED_SYSTEMS', 'SECURITY', 'GAME_DEVELOPMENT', 'DATABASE_ADMINISTRATOR', 'QA_TESTING', 'UI_UX_DESIGN', 'BLOCKCHAIN', 'OTHER');

ALTER TABLE tb_developer ADD COLUMN temp_specialization VARCHAR(50);

UPDATE tb_developer 
SET temp_specialization = CASE
    WHEN specialization = 0 THEN 'FRONTEND'
    WHEN specialization = 1 THEN 'BACKEND'
    WHEN specialization = 2 THEN 'FULLSTACK'
    WHEN specialization = 3 THEN 'MOBILE'
    WHEN specialization = 4 THEN 'DEVOPS'
    WHEN specialization = 5 THEN 'CLOUD_ENGINEERING'
    WHEN specialization = 6 THEN 'DATA_SCIENCE'
    WHEN specialization = 7 THEN 'MACHINE_LEARNING'
    WHEN specialization = 8 THEN 'EMBEDDED_SYSTEMS'
    WHEN specialization = 9 THEN 'SECURITY'
    WHEN specialization = 10 THEN 'GAME_DEVELOPMENT'
    WHEN specialization = 11 THEN 'DATABASE_ADMINISTRATOR'
    WHEN specialization = 12 THEN 'QA_TESTING'
    WHEN specialization = 13 THEN 'UI_UX_DESIGN'
    WHEN specialization = 14 THEN 'BLOCKCHAIN'
    ELSE 'OTHER'
END;

ALTER TABLE tb_developer DROP COLUMN specialization;

ALTER TABLE tb_developer ADD COLUMN specialization developer_specialization;

UPDATE tb_developer SET specialization = temp_specialization::developer_specialization;

ALTER TABLE tb_developer DROP COLUMN temp_specialization;