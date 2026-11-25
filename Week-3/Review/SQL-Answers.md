# SQL Review - Answers

1. Improve performance, save space, normalization of tables, etc... It also helps reduce duplication.

2. Basic queries including `SELECT`, `WHERE`, `JOIN`, and `GROUP BY` tend to carry over. Some data types may need to change.

3. It allows 10 digits total with 2 digits after the decimal point. `DECIMAL` stores cents exactly while `FLOAT` can introduce rounding errors.

4. Primary keys enforce both the `UNIQUE` and `NOT NULL` contraints. This gives all rows a reliable identifier for updates, deletes and references from other tables.

5. Foreign keys prevent `accounts_customers` from pointing to a nonexistent account or customer. With ordinary immediate checks, create the parent tables and insert the parent rows first, then drop the linking table before dropping its parents.

6. A single foreign key on either table can point to only one row, but both customers and accounts can have multiple matches.

7. `DELETE FROM accounts` removes the rows and leaves the table structure in place - DML. `DROP TABLE accounts` removes the data and the table itself - DDL.

8. The 5% interest update affects every account. Without a `WHERE` clause, all rows are affected while including the `WHERE` clause ensures you are only affecting the given rows.

9. The `%` matches zero or more characters. `'% Smith'` requires the name to end in a space followed by `Smith` while `'%Smith%'` can also match names like Smithson.

10. Grouping by state puts all rows for each state together. `SUM` can return one total per state. A state can contain several customers, so there's no single customer name to return after that group.

11. The join can return one row per matching account, so a customer with two distinct accounts under $50 appears twice. `SELECT DISTINCT` on the customer columns or an `EXISTS` filter can return each customer once.

12. Use the `LEFT JOIN` so that customers still appears and the account columns get `NULL`.

13. You can simple swap the two sides of the join. For example, swap the `RIGHT JOIN` to a `LEFT JOIN`; this in turn makes it easier to see which side's rows are kept.

14. Use a self-join when rows relate to other rows in the same table, such as employees and their managers. Aliases give the two uses of the table distinct names so SQL knows which columns you mean.

15. Each of the 5 addresses is paired with each of the 7 accounts, giving 35 rows. This is useful when you need every possible combination, such as every product paired with every available size.

16. Equi joins match rows using `=`. It is essentially a type of theta join, which can also use comparisons like `<`, `>`, or `<>`.

17. Both queries must return the same number of columns in the same logical order, with compatible data types in each position. Column names can differ.

18. `UNION ALL` is usually faster because it keeps all rows and skips duplicate removal. Use `UNION` when each distinct result shold appear only once.

19. `GRANT` and `REVOKE` control who can read or change data. Constraints control which values and relationships are valid when someone makes a change.

20. If the debit succeeds and the credit fails, $199.99 could disappear from the combined balance. Atomicity lets you commit both updates together or roll them both back.