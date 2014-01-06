package pl.edu.agh.iso.dao;

import java.util.List;

import pl.edu.agh.iso.model.entity.MockSession;

public interface MockSessionDao {

	public List<MockSession> findAll();
	public void deleteAll();
	public List<MockSession> findByKey(String key);
	public List<MockSession> findByValue(String value);
	public MockSession findById(Long id);
	public MockSession addNode(MockSession node);
}
