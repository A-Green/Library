--------------------------------------------------------
--  DDL for Trigger COMMENTS_LOG_TG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "NEWS_MANAGER"."COMMENTS_LOG_TG" 
BEFORE INSERT ON COMMENTS FOR EACH ROW
DECLARE
  v_content VARCHAR(1000);
BEGIN

v_content := 'COMMENT_TEXT:' || :new.comment_text || ';' || 'NEWS:' || :new.NEWS || ';';
v_content := LOG_CONTENT_CHECK(:new.CREATION_DATE, v_content, 'CREATION_DATE');
v_content := LOG_CONTENT_CHECK(:new.COMMENT_ID, v_content, 'COMMENT_ID');

WRITE_NEWS_LOG('COMMENTS', v_content);

END;
