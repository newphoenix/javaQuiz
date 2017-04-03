DROP PROCEDURE IF EXISTS split_row;

CREATE PROCEDURE split_row(delimiter varchar(1))
BEGIN

    DECLARE id1 INT(11) DEFAULT 0;
    DECLARE name1 varchar(50);
    DECLARE delimiter_number INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE value VARCHAR(50);
    DECLARE done INT(11) DEFAULT FALSE;
    DECLARE cur CURSOR FOR SELECT id, name FROM sometbl WHERE name != '' AND name is not null;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    DROP TEMPORARY TABLE IF EXISTS temp;
    CREATE TEMPORARY TABLE temp(id INT NOT NULL,name VARCHAR(50) NOT NULL) ENGINE=Memory;

    OPEN cur;

      read_loop: LOOP

        FETCH cur INTO id1, name1;

        IF done THEN
          LEAVE read_loop;
        END IF;


        SET delimiter_number = (SELECT LENGTH(name1)- LENGTH(REPLACE(name1, delimiter, ''))+1);
        SET i=1;

        WHILE i <= delimiter_number DO

          SET value =(SELECT REPLACE(SUBSTRING(SUBSTRING_INDEX(name1, delimiter, i),

          LENGTH(SUBSTRING_INDEX(name1, delimiter, i - 1)) + 1), delimiter, ''));

          INSERT INTO temp VALUES (id1, value);

          SET i = i + 1;
        END WHILE;

      END LOOP;

      SELECT * FROM temp;

    CLOSE cur;

  END ;