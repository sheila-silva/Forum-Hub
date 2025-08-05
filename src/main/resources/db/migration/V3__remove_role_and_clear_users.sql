-- Remove a coluna 'role' da tabela 'users'
ALTER TABLE users
DROP COLUMN role;

-- Remove todos os registros da tabela 'users'
DELETE FROM users;
