package com.epam.lab.news.database.dao.news;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epam.lab.news.database.dao.HibernateBaseDAOImpl;
import com.epam.lab.news.model.News;
/**
 * NewsDAO implementation based on hibernate framework
 */
@Repository
public class NewsDAO extends HibernateBaseDAOImpl<News, Integer> implements INewsDAO {

	private static final String GET_NEWS_ORDERED_BY_COMMENTS = 
			"SELECT NEWS_ID, title, brief, news_content, news.CREATION_DATE, news.MODIFICATION_DATE "
			+ "FROM news JOIN (select NEWS, COUNT(news) AS target FROM comments GROUP BY NEWS) "
			+ "ON news.NEWS_ID = news ORDER BY target DESC";

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> getList(int amount, int skip) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(News.class).setFirstResult(skip)
				.setMaxResults(amount)
				.addOrder(Order.desc("creationDate"));

		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(News news) {

		sessionFactory.getCurrentSession().saveOrUpdate(news);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		
		return (long) sessionFactory.getCurrentSession().createCriteria(News.class)
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> fetchByTag(String tagName, int amount, int skip) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(News.class)
				.createAlias("tags", "tags")
				.add(Restrictions.eq("tags.tagName", tagName))
				.setFirstResult(skip).setMaxResults(amount);

		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long countByTag(String tag) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(News.class) 
				.createAlias("tags", "tags")
				.add(Restrictions.eq("tags.tagName", tag))
				.setProjection(Projections.rowCount());
		
		return (long) criteria.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> fetchByAuthor(String name, int amount, int skip) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(News.class).createAlias("authors", "authors")
				.add(Restrictions.eq("authors.name", name))
				.setFirstResult(skip).setMaxResults(amount);

		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long countByAuthor(String name) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(News.class) 
				.createAlias("authors", "authors")
				.add(Restrictions.eq("authors.name", name))
				.setProjection(Projections.rowCount());
		
		return (long) criteria.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> getMostComentedNews(int amount, int skip) {

		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(GET_NEWS_ORDERED_BY_COMMENTS)
				.addEntity(News.class)
				.setFirstResult(skip)
				.setMaxResults(amount);
		
		return query.list();
	}
}
