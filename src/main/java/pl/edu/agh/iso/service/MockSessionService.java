package pl.edu.agh.iso.service;

import java.util.List;

import pl.edu.agh.iso.model.entity.MockSession;

public interface MockSessionService {
   public List<MockSession> findAll();
   public void deleteAll();
   public boolean isRightNode(String nodeNoStr);
   public boolean nodeExists();
   public MockSession addNode(String nodeNoStr, List<String> calculations);
   public MockSession findByKey();
}
