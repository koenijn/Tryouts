package nl.vandenboomen.tryouts.springhateoaswebservice.interfaces.facade.webservice;

import nl.vandenboomen.tryouts.springhateoaswebservice.interfaces.facade.webservice.dto.Greeting;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (c) 2013 vandenboomen.nl - All rights reserved
 * Created by mark on 25-11-13.
 */

@Controller
@RequestMapping(value = "/greeting")
public class GreetingController {

	private static final String template = "Hello %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/{name}")
	public HttpEntity<Greeting> greeting(@PathVariable String name) {
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
		ResponseEntity<Greeting> result = new ResponseEntity<>(greeting, HttpStatus.NOT_FOUND);

		return result;
	}
}
