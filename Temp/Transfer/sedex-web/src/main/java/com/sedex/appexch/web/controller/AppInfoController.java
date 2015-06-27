package com.sedex.appexch.web.controller;

import org.apache.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sedex.appexch.web.controller.base.AbstractController;

@Controller
@RequestMapping(value = "/" + AppInfoController.NAME_SPACE)
public class AppInfoController extends AbstractController {

	protected static final String NAME_SPACE = "app";

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView listApps() {
		logger.log(Level.INFO, "[listApps]");
		return new ModelAndView(NAME_SPACE + "/listApps");
	}

	@RequestMapping(value = "/app2", method = RequestMethod.GET)
	public ModelAndView listApps2() {
		logger.log(Level.INFO, "[listApps2]");
		return new ModelAndView(NAME_SPACE + "/listApps");
	}
}
