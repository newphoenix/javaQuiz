DROP FUNCTION IF EXISTS initcap;

CREATE FUNCTION initcap(str VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE len INT;
	DECLARE i INT;

	SET len   = CHAR_LENGTH(str);
	SET str = LOWER(str);
	SET i = 0;

	WHILE (i < len) DO
		IF (MID(str,i,1) = ' ' OR i = 0) THEN

			IF (i < len) THEN

				SET str = CONCAT(
					LEFT(str,i),
					UPPER(MID(str,i + 1,1)),
					RIGHT(str,len - i - 1)
				);

			END IF;
		END IF;

		SET i = i + 1;
	END WHILE;

	RETURN str;

END ;