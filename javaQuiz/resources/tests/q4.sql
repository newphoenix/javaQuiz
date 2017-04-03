DROP TABLE IF EXISTS bugs;

CREATE TABLE bugs (
  id int(11) DEFAULT NULL,
  open_date date DEFAULT NULL,
  close_date date DEFAULT NULL,
  severity varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO bugs VALUES 
(1,'2017-03-01','2017-03-02','fatal'),
(2,'2017-03-01','2017-03-02','error'),
(3,'2017-03-01',NULL,'critical'),
(4,'2017-03-02','2017-03-03','critical'),
(5,'2017-03-02','2017-03-03','critical'),
(6,'2017-03-02','2017-03-03','critical'),
(7,'2017-03-02','2017-03-03','fatal'),
(8,'2017-03-02','2017-03-03','error'),
(9,'2017-03-02','2017-03-04','error'),
(11,'2017-03-02',NULL,'error'),
(12,'2017-03-04',NULL,'fatal'),
(13,'2017-03-04',NULL,'critical'),
(14,'2017-03-04',NULL,'critical'),
(15,'2017-03-04',NULL,'fatal'),
(16,'2017-03-04',NULL,'error'),
(17,'2017-03-05',NULL,'error'),
(18,'2017-03-05',NULL,'error'),
(19,'2017-03-06',NULL,'error');

DROP PROCEDURE IF EXISTS show_open_bugs;

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

END ;



CALL show_open_bugs('2017-03-01','2017-03-18');