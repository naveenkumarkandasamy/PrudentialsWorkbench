package org.activiti.app.rest.runtime.pru;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.pru.model.CashPaymentModel;

@Controller
@RequestMapping("/custom-api/createtasks")
public class RestController {

	static final Logger logger = Logger.getLogger(RestController.class);

	@RequestMapping(value = "/cashpayments", method = RequestMethod.GET)
	public @ResponseBody String helloWorld(HttpServletRequest request, HttpServletResponse response) {
		return "Hello World";
	}	
	
	@RequestMapping(value = "/cashpayments", method = RequestMethod.POST)
	@ResponseBody
	public void createCashPaymentTasks(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CashPaymentModel cashPaymentModel) {
		try {
			System.out.println(cashPaymentModel.getCashPayments().length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}
