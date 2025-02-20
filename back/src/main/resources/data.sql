-- Vérifie si les données existent déjà avant de les insérer
INSERT INTO theme (name, description)
SELECT 'Spring', 'Spring Framework'
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Spring');

INSERT INTO theme (name, description)
SELECT 'MySQL', 'Database MySQL'
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'MySQL');

INSERT INTO theme (name, description)
SELECT 'Java', 'Java Programming Language'
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Java');

INSERT INTO theme (name, description)
SELECT 'Next.js', 'Frontend Framework Next.js'
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Next.js');

INSERT INTO theme (name, description)
SELECT 'Python', 'Python Programming Language'
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Python');

INSERT INTO theme (name, description)
SELECT 'React', 'Frontend Framework React'
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'React');