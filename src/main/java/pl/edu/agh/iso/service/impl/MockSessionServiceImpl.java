package pl.edu.agh.iso.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.iso.dao.MockSessionDao;
import pl.edu.agh.iso.model.entity.MockSession;
import pl.edu.agh.iso.service.MockSessionService;

@Service("mockSessionService")
public class MockSessionServiceImpl implements MockSessionService {

	@Autowired
	private MockSessionDao mockSessionDao;

	public List<MockSession> findAll() {
	  return mockSessionDao.findAll();
	}

	@Override
	public void deleteAll() {
		mockSessionDao.deleteAll();
	}

	@Override
	public boolean isRightNode(String nodeNoStr) {
		try {
			List<MockSession> nodes = mockSessionDao.findByKey(InetAddress.getLocalHost().getHostName());
			return nodes.size() == 1 && nodes.get(0).getValue().equals(nodeNoStr);
		}
		catch(UnknownHostException uhe) {
			
		}
		return false;
	}

	@Override
	public boolean nodeExists() {
		try {
			List<MockSession> nodes = mockSessionDao.findByKey(InetAddress.getLocalHost().getHostName());
			return nodes.size() == 1;
		}
		catch (UnknownHostException e) {

		}
		return false;
	}

	@Override
	public MockSession addNode(String nodeNoStr, List<String> calculations) {
		try {
			MockSession node = new MockSession();
			StringBuilder calcStr = new StringBuilder();
			for (String calculation : calculations) {
				calcStr.append(calculation).append("<br/>");
			}
			node.setCalculations(calcStr.toString());
			node.setKey(InetAddress.getLocalHost().getHostName());
			node.setValue(nodeNoStr);
			return mockSessionDao.addNode(node);
		}
		catch(UnknownHostException uhe) {
			
		}
		return null;
	}

	@Override
	public MockSession findByKey() {
		try {
			return mockSessionDao.findByKey(InetAddress.getLocalHost().getHostName()).get(0);
		} 
		catch (UnknownHostException e) {

		}
		return null;
	}
}
