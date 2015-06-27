package com.sedex.appexch.web.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public abstract class AbstractController {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpSession session;
}
