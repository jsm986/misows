package pl.edu.agh.iso.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseDaoImpl {

	@Autowired
	protected SessionFactory sessionFactory;
}
