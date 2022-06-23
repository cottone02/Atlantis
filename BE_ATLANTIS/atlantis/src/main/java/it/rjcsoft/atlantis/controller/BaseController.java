package it.rjcsoft.atlantis.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BaseController {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	
}
