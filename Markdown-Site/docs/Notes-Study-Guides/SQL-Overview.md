# SQL Overview

## Table of Contents
- [1. High-Level Overview](#1-high-level-overview)
- [2. SQL Sublanguage Ecosystem](#2-sql-sublanguage-ecosystem)
    - [2.1 Classification Overview](#21-classification-overview)
- [3. Data Definition Langauge (DDL)](#3-data-definition-language-dql)
- [4. Data Manipulation Language (DML)](#4-data-manipulation-langauge-dml)
- [5. Data Query Language (DQL)](#5-data-query-language-dql)
- [6. Data Control Language (DCL)](#6-data-control-language-dcl)
- [7. Transaction Control Language (TCL)](#7-transaction-control-language-tcl)

## 1. High-Level Overview
**SQL (Structured Query Language)** is the standard language used to communicate with **Relational Database Management Systems (RDBMS)**. While most developers associate SQL primarily with retrieving data, it is actually  multi-functional language divided into several specified **sublanguages**.

These sublanguages allow users to perform different categories of tasks:
- Defining database structure
- Managing the data within the structure
- Querying the data
- Controlling access to the data
- Managing transaction integrity

[&uarr; Back to Table of Contents](#table-of-contents)

## 2. SQL Sublanguage Ecosystem
SQL is not a single monolithic command set. It is divided into functional categories based on the type of operation being performed.

### 2.1 Classification Overview
| Sublanguage | Full Name | Primary Purpose | Core Operations |
| :--- | :--- | :--- | :--- |
| **DDL** | Date Definition Language | Defines / Modifies **Structure** (Schema) | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` |
| **DML** | Data Manipulation Language | Manages the **Content** (Rows) Within Structures | `INSERT`, `UPDATE`, `DELETE` |
| **DQL** | Data Query Language | Retrieves and **Fetches** Data | `SELECT` |
| **DCL** | Data Control Language | Manages **Permissions** and Security | `GRANT`, `REVOKE` |
| **TCL** | Transaction Control Language | Manages **Database Transactions** | `COMMIT`, `ROLLBACK`, `SAVEPOINT` |

[&uarr; Back to Table of Contents](#table-of-contents)

## 3. Data Definition Language (DQL)
**DDL** is used to define the database schema. It deals with tables, indexes, views, and databases, rather than the data inside them. DDL commands are typically **auto-committed**, meaning once you run them, the change is permanent and cannot be rolled back in most RDBMS.

### Core DDL Commands
- **`CREATE`** - Creates a new object (Table, Database, Index)
```sql
CREATE TABLE Users(UserID INT PRIMARY KEY, Username VARCHAR(50), Email VARCHAR(100));
```
- **`ALTER`** - Modifies the structure of an existing object (Adding a column)
```sql
ALTER TABLE Users ADD Age INT;
```
- **`DROP`** - Deletes an entire object and its structure from the database
```sql
DROP TABLE Users;
```
- **`TRUNCATE`** - Removes all records from a table, but keeps the table structure intact.

> [!IMPORTANT]
> **DDL is Destructive** - Be extremely careful with `DROP` and `TRUNCATE`. Unlike DML, these operations usually bypass transaction logs for individual rows, making "undoing" them nearly impossible without a full database backup.

[&uarr; Back to Table of Contents](#table-of-contents)

## 4. Data Manipulation Langauge (DML)
**DML** is used to manage the actual data stored within the database objects. While DDL builds the "house", DML manages the "furniture" (the rows of data).

### Core DML Commands
- **`INSERT`** - Adds new rows to a table
```sql
INSERT INTO Users (UserID, Username, Email)
VALUES (1, 'jdoe', 'jdoe@example.com');
```
- **`UPDATE`** - Modifies existing data within a table
```sql
UPDATE Users SET Email = 'john.doe@newmail.com' WHERE UserID = 1;
```
- **`DELETE`** - Removes specific rows from a table
```sql
DELETE FROM Users WHERE UserID = 1;
```

> [!WARNING]
> **The Missing WHERE Clause** - Always double check your `WHERE` clause in `UPDATE` and `DELETE` statements. Running `DELETE FROM Users;` without a `WHERE` clause will wipe every single row in the table!

[&uarr; Back to Table of Contents](#table-of-contents)

## 5. Data Query Language (DQL)
**DQL** is used to fetch data from the database. It is the most widely used part of SQL by analysts and application developers.

### Core DQL Command: `SELECT`
The `SELECT` statement is the heart of DQL. It allows you to specify exactly which columns and which rows you want to see.

**Basic Syntax Structure**
```sql
SELECT column1, column2
FROM table_name
WHERE condition
ORDER BY column1 ASC;
```

**Key DQL Components**
- **`SELECT`** - Specifies the columns to retrieve
= **`FROM`** - Specifices the table(s) to query
- **`WHERE`** - Filters the rows based on specific criteria
- **`GROUP BY`** - Aggregates data (often used with `COUNT`, `SUM`, `AVG`)
- **`ORDER BY`** - Sorts the resulting data

**DQL vs DML Comparison**
| Feature | DQL (`SELECT`) | DML (`INSERT` / `UPDATE` / `DELETE`) |
| :--- | :--- | :--- |
| **Primary Action** | Reading / Fetching | Writing / Modifying |
| **Impact on Data** | None (Read-Only) | Direct Change to Stored Values |
| **Typical User** | Data Analysts, App UI | Backend Services, Data Entry |

[&uarr; Back to Table of Contents](#table-of-contents)

## 6. Data Control Language (DCL)
**DCL** is the security layer of SQL. It is used to grant or revoke permissions to database users, ensuring that sensitive data is only accessible to authorized personnel.

### Core DCL Commands
- **`GRANT`** - Gives a user permissions to perform specific tasks
```sql
GRANT SELECT, INSERT ON Users TO `analyst_user`;
```
- **`REVOKE`** - Removes permissions previously granted to a user.
```sql
REVOKE INSERT ON Users FROM 'analyst_user';
```

[&uarr; Back to Table of Contents](#table-of-contents)

## 7. Transaction Control Language (TCL)

**TCL** manages **Transacitons**. A transaction is a logical unit of work that consists of one ore more DML statements. TCL ensures the **ACID** properties (Atomicity, Consistency, Isolation, Durability) of the database.

### Core TCL Commands
- **`COMMIT`** - Saves all changes made during the current transaction permanently to the database
```sql
UPDATE Accounts SET Balance = Balance - 100 WHERE UserID = 1;
UPDATE Accounts SET Balance = Balance + 100 WHERE UserID = 2;
COMMIT; -- Both Updates Are Now Permanent
```
- **`ROLLBACK`** - Undoes all changes made in the current transaction if an error occurs, returning the database to its previous state
```sql
-- If Something Fails Midway:
ROLLBACK;
```
- **`SAVEPOINT`** - Creates a "checkpoint" within a transaction that allows you to roll back to a specific point rather than the very beginning
```sql
SAVEPOINT point1;
-- Some Operations
ROLLBACK TO point1; -- Undoes Only What Happened Afer point1
```

[&uarr; Back to Table of Contents](#table-of-contents)