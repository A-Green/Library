package com.epam.lab.news.database.dao.authors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.epam.lab.news.database.dao.HibernateBaseDAOImpl;
import com.epam.lab.news.model.NewsAuthor;

/**
 * NewsAuthorDAO implementation based on hibernate framework
 */
@Repository
public class NewsAuthorDAO extends HibernateBaseDAOImpl<NewsAuthor, Integer> implements INewsAuthorDAO {
	
	private static final String GROUP_COUNT = 
			"SELECT AUTHORS.NAME, COUNT(NEWS) FROM " +
	"NEWS_AUTHORS JOIN AUTHORS ON NEWS_AUTHORS.AUTHOR = AUTHORS.AUTHOR_ID GROUP BY AUTHORS.NAME";
	
	private static final String GET_AUTHORS_BY_NEWS_ID = 
	"SELECT AUTHOR_ID, name FROM NEWS_AUTHORS JOIN AUTHORS ON NEWS_AUTHORS.AUTHOR = AUTHORS.AUTHOR_ID WHERE NEWS_AUTHORS.NEWS = ?";
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsAuthor> getAuthors() {
		return sessionFactory.getCurrentSession().createCriteria(NewsAuthor.class).list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Integer> countNewsByAuthors() {
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GROUP_COUNT)
				.addScalar("NAME", StringType.INSTANCE)
				.addScalar("COUNT(NEWS)", IntegerType.INSTANCE);
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		Iterator iterator = query.list().iterator();
		
		while (iterator.hasNext()) {
			
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
	public List<NewsAuthor> getNewsAuthors(int newsId) {
		
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(GET_AUTHORS_BY_NEWS_ID)
				.addEntity(NewsAuthor.class).setInteger(0, newsId);
	
		return query.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public NewsAuthor getAuthorByName(String name) {
		return (NewsAuthor) sessionFactory.getCurrentSession()
				.createCriteria(NewsAuthor.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}
}
