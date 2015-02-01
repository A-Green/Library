package com.epam.lab.news.database.dao.tags;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.epam.lab.news.database.dao.HibernateBaseDAOImpl;
import com.epam.lab.news.model.NewsTag;
/**
 * NewsTagDAO implementation based on hibernate framework
 */
@Repository
public class NewsTagDAO extends HibernateBaseDAOImpl<NewsTag, String> implements INewsTagDAO {
	
	private static final String GROUP_COUNT = "select TAG, COUNT(TAG) from NEWS_TAG GROUP BY TAG";	
	
	private static final String GET_TAGS_BY_NEWS_ID = "select TAG from NEWS_TAG WHERE NEWS = ?";

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsTag> getAllTags() {
		return sessionFactory.getCurrentSession().createCriteria(NewsTag.class).list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> countNewsByTag() {

		@SuppressWarnings("rawtypes")
		Iterator iterator = sessionFactory.getCurrentSession().createSQLQuery(GROUP_COUNT)
				.addScalar("TAG", StringType.INSTANCE)
				.addScalar("COUNT(TAG)", IntegerType.INSTANCE).list().iterator();
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		while(iterator.hasNext()){
			
			Object[] objects = (Object[]) iterator.next();         
            result.put((String) objects[0], (int) objects[1]);        
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsTag> getTagsByNewsId(int newsId) {

		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(GET_TAGS_BY_NEWS_ID)
				.addEntity(NewsTag.class)
				.setInteger(0, newsId);
	
		return query.list();
	}
}
