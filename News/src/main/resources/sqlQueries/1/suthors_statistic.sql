
select B.NAME as AUTH_NAME,
  NEWS_AMOUNT,
  avg_Comments,
  tag from ((
SELECT author_id,
  NEWS_AMOUNT,
  avg_Comments,
  tag
FROM (((
  (SELECT COUNT(NEWS_AUTHORS.AUTHOR) AS NEWS_AMOUNT, /*News amount of each author*/
          author                     AS author_id
  FROM NEWS_AUTHORS
  GROUP BY author
  )
JOIN
  (SELECT author,
    ROUND(AVG(newsCommCount)) AS avg_Comments /*average amount of comments to author news*/
  FROM
    (SELECT author,
      COUNT(comments.NEWS) AS newsCommCount
    FROM NEWS_AUTHORS
    JOIN comments
    ON NEWS_AUTHORS.NEWS = comments.NEWS
    GROUP BY author,
      NEWS_AUTHORS.NEWS
    )
  GROUP BY author
  ) ON author_id = author))
JOIN
  (SELECT DISTINCT AUTHOR AS tag_author,            
    FIRST_VALUE(TAG) OVER(PARTITION BY Author) tag
  FROM
    (SELECT author, TAG, COUNT(TAG) AS tags_count /*count each tag in authors news*/
    FROM (NEWS_AUTHORS                           
    JOIN NEWS_TAG
    ON NEWS_AUTHORS.NEWS = NEWS_TAG.NEWS)
    GROUP BY TAG,
      author
    ORDER BY NEWS_AUTHORS.AUTHOR,
      tags_count DESC
    )
  ORDER BY AUTHOR
  )
ON author_id = tag_author)) A
JOIN AUTHORS B ON A.author_id = B.AUTHOR_ID
);
