--------------------------------------------------------
--  DDL for Trigger AUTHORS_LOG_TG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "NEWS_MANAGER"."AUTHORS_LOG_TG" 
BEFORE INSERT ON AUTHORS for EACH row
DECLARE
  v_content VARCHAR(100);
BEGIN

v_content := 'name:' || :new.name || ';';
v_content := LOG_CONTENT_CHECK(:new.author_id, v_content, 'AUTHOR_ID');

WRITE_NEWS_LOG('AUTHORS', v_content);
END;
