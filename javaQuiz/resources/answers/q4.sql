CREATE PROCEDURE show_open_bugs(fromDate DATE, toDate DATE)

BEGIN
DECLARE number_of_bugs INT DEFAULT 0;
DROP TEMPORARY TABLE IF EXISTS tmp;
WHILE fromDate <= toDate DO
       SET number_of_bugs = (SELECT COUNT(id) FROM bugs
                             WHERE close_date IS NOT NULL AND DATE(open_date) <= fromDate AND DATE(close_date) > fromDate);                             
       CREATE TEMPORARY TABLE IF NOT EXISTS tmp(datum DATE, open_bugs INT) ENGINE=Memory;
       INSERT INTO tmp VALUES (fromDate, number_of_bugs);
       SET fromDate = ADDDATE(fromDate, INTERVAL 1 DAY);
END WHILE;
    SELECT  DATE_FORMAT(datum,'%Y-%m-%d') as 'date', open_bugs as 'Open bugs'  FROM tmp;
END;