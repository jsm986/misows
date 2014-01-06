package pl.edu.agh.iso.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.edu.agh.iso.dao.MockSessionDao;
import pl.edu.agh.iso.model.entity.MockSession;

@Repository("mockSessionDao")
public class MockSessionDaoImpl extends BaseDaoImpl implements MockSessionDao {

	@SuppressWarnings("unchecked")
	public List<MockSession> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(MockSession.class).list();
	}

	@Override
	public void deleteAll() {
		for (MockSession toDelete : findAll()) {
			sessionFactory.getCurrentSession().delete(toDelete);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MockSession> findByKey(String key) {
		return sessionFactory.getCurrentSession().createCriteria(MockSession.class).add(Restrictions.eq("key", key)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MockSession> findByValue(String value) {
		return sessionFactory.getCurrentSession().createCriteria(MockSession.class).add(Restrictions.eq("value", value)).list();	
	}

	@Override
	public MockSession addNode(MockSession node) {
		Long saved = (Long) sessionFactory.getCurrentSession().save(node);
		return findById(saved);
	}

	@Override
	public MockSession findById(Long id) {
		MockSession ms = (MockSession) sessionFactory.getCurrentSession().createCriteria(MockSession.class).add(Restrictions.eq("id", id)).uniqueResult();
		return ms;
	}
	
}
