--------------------------------------------------------
--  DDL for Trigger NEWS_AUTHORS_LOG_TG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "NEWS_MANAGER"."NEWS_AUTHORS_LOG_TG" 
BEFORE INSERT ON NEWS_AUTHORS FOR EACH ROW
DECLARE 
  v_content VARCHAR(500);
BEGIN
  v_content := 'NEWS:' || :new.news || ';' || 'AUTHOR:' || :new.author || ';';           
  v_content := LOG_CONTENT_CHECK(:new.ID, v_content, 'ID');

WRITE_NEWS_LOG('NEWS_AUTHORS', v_content);
END;
