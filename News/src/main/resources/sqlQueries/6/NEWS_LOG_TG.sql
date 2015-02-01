--------------------------------------------------------
--  DDL for Trigger NEWS_LOG_TG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "NEWS_MANAGER"."NEWS_LOG_TG" 
BEFORE INSERT ON NEWS FOR EACH ROW
DECLARE
  v_content VARCHAR(4000);
BEGIN

v_content := 'title:' || :new.title || ';' || 'brief:' 
|| :new.brief || ';' || 'content:' || :new.news_content || ';';
              
v_content := LOG_CONTENT_CHECK(:new.creation_date, v_content, 'CREATION_DATE');
v_content := LOG_CONTENT_CHECK(:new.modification_date, v_content, 'MODIFICATION_DATE');
v_content := LOG_CONTENT_CHECK(:new.news_id, v_content, 'NEWS_ID');

WRITE_NEWS_LOG('NEWS', v_content);
END;
