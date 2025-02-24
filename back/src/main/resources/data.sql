-- Vérifie si les données existent déjà avant de les insérer
INSERT INTO theme (name, description)
SELECT 'Spring', "Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard..."
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Spring');

INSERT INTO theme (name, description)
SELECT 'MySQL', "Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard..."
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'MySQL');

INSERT INTO theme (name, description)
SELECT 'Java', "Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard..."
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Java');

INSERT INTO theme (name, description)
SELECT 'Next.js', "Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard..."
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Next.js');

INSERT INTO theme (name, description)
SELECT 'Python', "Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard..."
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'Python');

INSERT INTO theme (name, description)
SELECT 'React', "Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard..."
WHERE NOT EXISTS (SELECT 1 FROM theme WHERE name = 'React');