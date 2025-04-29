package com.example.handlingformsubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackServiceController {
	@Autowired
	ProviderService providerService;
	@Autowired
	ClientService clientService;

	@PostMapping("/notifyService/{id}")
	public void updated(@PathVariable long id) {
		providerService.notifyProvider(id);
	}

	@GetMapping("/getFeedback/{id}")
	public FeedbackData getFeedback(@PathVariable long id) {
		return clientService.getById(id);
	}
}
