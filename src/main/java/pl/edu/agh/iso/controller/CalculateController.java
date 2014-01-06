package pl.edu.agh.iso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iso.model.entity.MockSession;
import pl.edu.agh.iso.service.CalculationsService;
import pl.edu.agh.iso.service.MockSessionService;

@Controller
public class CalculateController {
	
	private static final String NODE_EMPTY_MESSAGE = "No node parameter has been specified. It will erase all information about"
			+ " sessions will be deleted. Would you like to continue? ";
	
	@Autowired
    @Qualifier("mockSessionService")
	private MockSessionService mockSessionService;
	
	@Autowired
	@Qualifier("calculationsService")
	private CalculationsService calculationsService;
	
	@RequestMapping(value="/destroySession", method=RequestMethod.POST)
	public String destroySession() {
		mockSessionService.deleteAll();
		return "redirect:calculate.do";
	}
	
	
	@RequestMapping(value = "/calculate", method=RequestMethod.GET)
	public ModelAndView calculate(HttpServletRequest request) throws InterruptedException {
		ModelAndView mav = new ModelAndView();
		String node = request.getParameter("node");	
		if (StringUtils.isBlank(node)) {
			mav.addObject("nodeEmpty", NODE_EMPTY_MESSAGE);
			return mav;
		}
		else {
			if (mockSessionService.nodeExists()) {
				if (mockSessionService.isRightNode(node)) {
					mav.addObject("logs", mockSessionService.findByKey().getCalculations());
					return mav;
				}
				else {
					return new ModelAndView(String.format("redirect:calculate.do?node=%s", node));
				}
			}
			else {
		    	if(calculationsService.isCalculating()){
		            mav.addObject("logs", mockSessionService.findByKey().getCalculations());
		     	}	
		    	else {
		    		mav.addObject("info", "Calculation is starting...");
		    		calculationsService.doCalculate("/sixHalfBalls.dat", 0.00005);
		    		Thread.sleep(1000);
		    		mockSessionService.addNode(node, calculationsService.getLogs());
		    	}
			}
		}
		return mav;
	}
}
