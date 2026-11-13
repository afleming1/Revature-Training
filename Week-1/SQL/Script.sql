CREATE TABLE teams(
	-- Between the (), we declare what columns we need in the table by assigning column names and types along with potential constraints

	/*
	 * With the id column below, we have three things happening
	 * 1. Column given the name of "id"
	 * 2. Set the type of data stored in the column as the "integer" type
	 * 3. We declare this column to be the "primary key"
	 */
	id INTEGER PRIMARY KEY,
	name TEXT,
	city TEXT
);

CREATE TABLE players(
	id INTEGER PRIMARY KEY,
	name TEXT,
	home_town TEXT,
	
	/*
	 * Here we are leveraging the relational power of relational databases
	 * team_id column is set up as a foreign key that points to the primary key of the teams table
	 * Each player record must have a valid team_id in order for it to be added to the table, this allows us to always know what player is associated with what team
	 * 
	 * You want to avoid having "orphaned" records - a record with a foreign key that points to something that does not exist.
	 * To avoid this, tell you database how you want to update records with foreign keys that are no longer valid
	 */
	team_id INTEGER REFERENCES teams(id) ON DELETE CASCADE
);

INSERT INTO teams (name, city) VALUES ('Trailblazers', 'Portland');

INSERT INTO teams VALUES (2, 'Lakers', 'Los Angeles');

INSERT INTO players (name, home_town, team_id) VALUES
	('Damian Lillard', 'Oakland', 1),
	('Luka Doncic', 'Lubljanja?', 2);

INSERT INTO players (name, home_town, team_id) VALUES ('Brandon Roy', 'Seattle', 1);

UPDATE players SET home_town = 'Ljubljana' WHERE id = 2;

UPDATE players SET name = 'Brandon Roy';

DELETE FROM players WHERE id = 1;

DELETE FROM players;

-- * indicates you want all columns
SELECT * FROM players;

-- If you only want specific columns, you specify them
SELECT id, name FROM players;

-- Where clause to filter records you get back
SELECT * FROM players WHERE team_id = 1;

-- Where clause can have multiple filters
SELECT name, home_town FROM players WHERE team_id = 1 AND home_town = 'Oakland';

-- If you want to filter by partial match, you can use % and _ to set that up
-- % matches 0 or more charactrs, _ matches a single character
SELECT name, home_town FROM players WHERE name LIKE '%o%';
SELECT * FROM players WHERE name LIKE '%cic';

-- We can limit the number of results from a result set
SELECT * FROM players LIMIT 3;

-- We can also order the results
SELECT * FROM players ORDER BY home_town DESC;

-- You can leverage limits and ordering to see subsets of data
SELECT * FROM players ORDER BY home_town DESC LIMIT 3;

-- You can also offset your result sets, which tells the database to skip some records when returning your result set
SELECT * FROM players ORDER BY home_town ASC LIMIT -1 OFFSET 2;

-- Scalar Functions
SELECT upper(name) FROM players;

-- Aggregate Functions
SELECT count(*) FROM players;
SELECT sum(id) as id_total FROM players;

-- Aliases can be applied to any column
SELECT name as player_name from players;

-- Multiple aliases can be set
SELECT name AS 'Player Name', home_town AS 'City', team_id AS 'Employeer' FROM players;

INSERT INTO players (name, home_town, team_id) VALUES
	('Ja', 'Oakland', 1),
	('Austin', 'Lubljanja?', 2);

-- Select all player and team names from both the players and teams table and join them on their ids
SELECT p.name AS 'Player Name', t.name AS 'Team Name' FROM players p JOIN teams t ON p.team_id = t.id ORDER BY t.id ASC;

INSERT INTO teams (name, city) VALUES ('Spurs', 'San Antonio');

-- If you ever need ALL records from a table, whether or not there is a match based on your on clause, you can use a left or right join depending
-- on which table you need all records from

-- This will get all teams records and matching player records.
-- Spurs have no players so on the Spurs row, the player info will all be null
SELECT * FROM teams t LEFT JOIN players p ON t.id = p.team_id;

-- This will get all the players info and matching teams info
-- Because no players are matched with the Spurs, the Spurs data will not be included
SELECT * FROM teams t RIGHT JOIN players p ON p.team_id = t.id;

-- Full join retrieves all records from both tables, if there are no matches for a record, the associated columns are set to null
SELECT * FROM teams t FULL JOIN players p ON t.id = p.team_id;

-- If you want to see all possible combinations (cartesian product) of your records, you can use a cross join
SELECT p.name AS 'Player Name', t.name as 'Team Name' FROM players p CROSS JOIN teams t;

-- Joins put data side-by-side in the same record. If you instead wanted to combine the data into the same column, you can use a union instead
SELECT name AS 'Player Names & Team Cities' FROM players UNION SELECT name FROM teams;

-- Whenever you use aggregate functions, there are two more clauses you should be aware of
-- GROUP BY tells the database how to organize your aggregate function results
-- HAVING is your filter statement - it acts like the where clause, except it is specifically for filtering querues that use aggregate functions
SELECT count(p.id) as 'Player Count', t.name as 'Team' FROM players p
RIGHT JOIN teams t ON p.team_id = t.id
GROUP BY Team HAVING 'Player Count' > 2 ORDER BY 'Player Count';

-- POSTGRES
CREATE TABLE public."employees" (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30)
);

INSERT INTO public.employees VALUES (default, 'Billy'), (default, 'Sally');

CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30)
);

INSERT INTO products (name) VALUES ('Keyboard'), ('Mouse'), ('Monitor');

-- Only customers would be able to see product information, database admins would be able to interact with both
CREATE USER customer WITH PASSWORD 'customer-password';
CREATE USER administrator WITH PASSWORD 'administrator-password';

-- Once you have users, you can grand and revoke capabilities.
-- This has to be targeted, so you must specify what schemas, tables, etc... you are providing or removing capabilities for
GRANT SELECT ON products TO customer;
GRANT SELECT ON products, employees TO administrator;

-- Customer can query products table, but not employee tabls
SELECT * FROM products;

-- Only adminitrator can query employee data
SELECT * FROM employees;

-- If you want to remove a user / role, you must first remove the permissions associated with resource you want deleted
revoke select on products from customer;

DROP ROLE customer;

-- See current permissions for a user
select grantee, privilege_type, table_schema, table_name
from information_schema.role_table_grants
where grantee = 'administrator';

-- TCL Keywords
start;
begin;

savepoint my_save_point;

rollback;
rollback to my_save_point;

end;
commit;