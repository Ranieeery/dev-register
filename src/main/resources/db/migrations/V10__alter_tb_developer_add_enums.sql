CREATE TYPE developer_seniority AS ENUM ('INTERN', 'JUNIOR', 'MID_LEVEL', 'SENIOR', 'TECH_LEAD', 'ARCHITECT', 'PRINCIPAL', 'OTHER');

ALTER TABLE tb_developer ADD COLUMN temp_seniority VARCHAR(50);

UPDATE tb_developer 
SET temp_seniority = CASE 
    WHEN seniority = 0 THEN 'INTERN'
    WHEN seniority = 1 THEN 'JUNIOR'
    WHEN seniority = 2 THEN 'MID_LEVEL'
    WHEN seniority = 3 THEN 'SENIOR'
    WHEN seniority = 4 THEN 'TECH_LEAD'
    WHEN seniority = 5 THEN 'ARCHITECT'
    WHEN seniority = 6 THEN 'PRINCIPAL'
    ELSE 'OTHER'
END;

ALTER TABLE tb_developer DROP COLUMN seniority;

ALTER TABLE tb_developer ADD COLUMN seniority developer_seniority;

UPDATE tb_developer SET seniority = temp_seniority::developer_seniority;

ALTER TABLE tb_developer DROP COLUMN temp_seniority;