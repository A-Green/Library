create or replace FUNCTION TAGS_CONCAT 
(
  NEWS_ID IN NUMBER 
, SEPARATOR IN VARCHAR2 
) RETURN VARCHAR2 AS 
  v_result VARCHAR2(4000 CHAR);
BEGIN
  
  IF (COUNT_TAGS_LENGTH(NEWS_ID) + (COUNT_NEWS_TAGS(NEWS_ID)) * LENGTH(SEPARATOR) ) > 4000 
  THEN
  raise_application_error (-20001, 'Total result length exceeded limit of 4000 symbols');
  END IF;
  
   FOR tag IN (SELECT tag FROM NEWS_TAG where NEWS = NEWS_ID)
     LOOP
         v_result := v_result || tag.tag || SEPARATOR;
       END LOOP;      
  RETURN RTRIM(v_result, SEPARATOR); 
END TAGS_CONCAT;