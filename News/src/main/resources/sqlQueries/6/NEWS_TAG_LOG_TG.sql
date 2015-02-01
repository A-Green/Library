--------------------------------------------------------
--  DDL for Trigger NEWS_TAG_LOG_TG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "NEWS_MANAGER"."NEWS_TAG_LOG_TG" 
BEFORE INSERT ON NEWS_TAG FOR EACH ROW
DECLARE
  v_content VARCHAR(500);
BEGIN

  v_content := 'NEWS:' || :new.news || ';' || 'TAG:' || :new.tag || ';';           
  v_content := LOG_CONTENT_CHECK(:new.NEWS_TAG_ID, v_content, 'NEWS_TAG_ID');

WRITE_NEWS_LOG('NEWS_TAG', v_content);
END;
