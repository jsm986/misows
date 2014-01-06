package pl.edu.agh.iso.service;

import java.util.List;

public interface CalculationsService {
   public boolean isCalculating();
   public void doCalculate(String filename, double error);
   public List<String> getLogs();
}
