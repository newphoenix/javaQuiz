DROP TABLE IF EXISTS votes;
CREATE TABLE votes ( name CHAR(10), votes INT );
INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);

SELECT @rank := @rank + 1 as ranking, v.name, v.votes
FROM (SELECT @rank := 0 ) rt,votes v
ORDER BY v.votes DESC;