package com.sedex.appexch.web.controller;

import org.apache.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sedex.appexch.web.controller.base.AbstractController;

@Controller
public class AppInfoController extends AbstractController {

	private static final String NAME_SPACE = "app";

	@RequestMapping(value = "/apps", method = RequestMethod.GET)
	public ModelAndView listApps() {
		logger.log(Level.WARN, "[listApps]");
		return new ModelAndView(NAME_SPACE + "/listApps");
	}
}
