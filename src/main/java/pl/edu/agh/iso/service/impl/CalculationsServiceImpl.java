package pl.edu.agh.iso.service.impl;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.edu.agh.iso.calculations.adaptation.HAdaptationMultiThreadComputationReferencePartitioner;
import pl.edu.agh.iso.calculations.data.DataStructure;
import pl.edu.agh.iso.calculations.part.ReferenceCube;
import pl.edu.agh.iso.calculations.partitions.ReferencePartitioner;
import pl.edu.agh.iso.service.CalculationsService;

@Service("calculationsService")
public class CalculationsServiceImpl implements CalculationsService {

	private boolean calculating = false;

	private List<String> logs; 
	
	@Override
	public synchronized boolean isCalculating() {
		if (!calculating) {
			calculating = true;
			return false;
		}
		return true;
	}

	@Override
	public void doCalculate(final String resourceFileName, final double error) {
		ReferenceCube initialCube = new ReferenceCube(0.0, 1.0, 1.0, 1.0, 0.0,
				0.0, 0, null);
		ReferencePartitioner p = new ReferencePartitioner(initialCube);

		final HAdaptationMultiThreadComputationReferencePartitioner x = new HAdaptationMultiThreadComputationReferencePartitioner(
				p, 4);

		logs = x.getLog();
		System.out.println(new File(".").getAbsolutePath());
		URL url = getClass().getResource(resourceFileName);
		File f = null;
		try {
			f = new File(url.toURI());
		}
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (f == null || !f.exists()) {
			System.out.println("Data file does not exist");
			System.exit(0);
		}

		initialCube = new ReferenceCube(0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, null);
		p = new ReferencePartitioner(initialCube);
        final String filepath = f.getAbsolutePath();
		Thread worker = new Thread() {
			@Override
			public void run() {
				x.startAdaptiveMultiThreadComputation(new DataStructure(
						filepath), error, 4);
				turnCalculationsOff();
			}
		};
		worker.start();
	}
	
	@Override
	public List<String> getLogs() {
		return logs;
	}
	
	private synchronized void turnCalculationsOff(){ 
		calculating = false; 
	}
}
