WITH random_half AS
  (SELECT NAME, rownum as row_num
  FROM
    (SELECT name FROM com ORDER BY SYS.DBMS_RANDOM.VALUE)
  WHERE rownum <=(SELECT COUNT(*) FROM COM ) / 2
  )
SELECT A.name AS AUTHOR_1,
       B.name AS AUTHOR_2
FROM (
  random_half A
JOIN
  (SELECT name, rownum AS row_num from com WHERE NAME NOT IN (SELECT NAME FROM random_half)) B
ON A.row_num = B.row_num );
