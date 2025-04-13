package com.example.handlingformsubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackController {
	@Autowired DatabaseService dbs;

	@GetMapping("/review")
	public String greetingForm(Model model) {
		model.addAttribute("feedback", new Feedback());
		return "reviewpopup";
	}

	@PostMapping("/review")
	public String greetingSubmit(@ModelAttribute Feedback feedback, Model model) {
		dbs.sendFeedback(feedback);
		model.addAttribute("feedback", feedback);
		return "result";
	}

}
