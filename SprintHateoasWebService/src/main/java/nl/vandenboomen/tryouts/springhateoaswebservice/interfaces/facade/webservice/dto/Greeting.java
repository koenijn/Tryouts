package nl.vandenboomen.tryouts.springhateoaswebservice.interfaces.facade.webservice.dto;

/**
 * Copyright (c) 2013 vandenboomen.nl - All rights reserved
 * Created by mark on 25-11-13.
 */
public class Greeting {

	private final Long id;
	private final String content;

	public Greeting(Long id, String content) {
		this.id = id;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
