package org.occ.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(value = "/checkin/{memberSeq}", method = RequestMethod.GET)
	public String checkin(@PathVariable("memberSeq") String memberSeq, Model model) {
		boolean hasErrors = false;
		if (memberSeq.equals("1"))
			model.addAttribute("name", "翁X方");
		else if (memberSeq.equals("2"))
			model.addAttribute("name", "張X明");
		else
			hasErrors = true;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("timestamp", sdf.format(new Date()));
		
		if (!hasErrors)
			return "api/checkin/success";
		else
			return "api/checkin/fail";
	}
	
	@RequestMapping(value = "/opencam", method = RequestMethod.GET)
	public String checkin(Model model) {
			return "api/checkin/opencam";
	}

}
