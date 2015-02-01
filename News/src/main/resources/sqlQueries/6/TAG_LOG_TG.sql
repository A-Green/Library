--------------------------------------------------------
--  DDL for Trigger TAG_LOG_TG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "NEWS_MANAGER"."TAG_LOG_TG" 
BEFORE INSERT ON TAG FOR EACH ROW
BEGIN
WRITE_NEWS_LOG('TAG','tag:' || :new.tag);
END;
