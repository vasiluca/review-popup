package com.example.handlingformsubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FeedbackController {
	@Autowired
	ProviderService providerService;

	@PostMapping("/notifyService/{id}")
	public void updated(@PathVariable long id) {
		System.out.println("Received request for id = ");
		providerService.notifyProvider(id);
	}

}
