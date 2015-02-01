SELECT TABLESPACE_NAME,
  ROUND(total  /1024 / 1024) "TOTAL MB",
  ROUND((total - used) /1024 / 1024) "AVAILABLE MB",
  ROUND((used  / total) * 100,2) "Used %"
FROM
  (SELECT TABLESPACE_NAME,
    SUM (bytes) used,      
    SUM (MAXBYTES) total
  FROM DBA_DATA_FILES
  GROUP BY TABLESPACE_NAME
  
  UNION
  
  SELECT TABLESPACE_NAME,
    SUM (bytes) used,
    SUM(MAXBYTES) total
  FROM DBA_TEMP_FILES
  GROUP BY TABLESPACE_NAME
  );
  
  select * from DBA_TEMP_FREE_SPACE;