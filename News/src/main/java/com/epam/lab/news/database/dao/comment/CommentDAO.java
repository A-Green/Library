package com.epam.lab.news.database.dao.comment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epam.lab.news.database.dao.HibernateBaseDAOImpl;
import com.epam.lab.news.model.NewsComment;
/**
 * NewsCommentDAO implementation based on hibernate framework
 */
@Repository
public class CommentDAO extends HibernateBaseDAOImpl<NewsComment, Integer> implements ICommentDAO {
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsComment> getNewsCommnets(int newsId, int amount, int skip) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(NewsComment.class)
				.add(Restrictions.eq("newsMessageId",newsId))
				.setFirstResult(skip)
				.setMaxResults(amount)
				.addOrder(Order.asc("creationDate"));
		
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count(int newsId) {
		
		return (long) sessionFactory.getCurrentSession().createCriteria(NewsComment.class)
				.add(Restrictions.eq("newsMessageId", newsId))
				.setProjection(Projections.rowCount()).uniqueResult();
	}
}
