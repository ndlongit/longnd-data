package com.sedex.appexch.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sedex.appexch.web.controller.base.AbstractController;

@Controller
public class AppInfoController extends AbstractController {

	private static final String BASE = "app";

	@RequestMapping(value = "/apps", method = RequestMethod.GET)
	public ModelAndView listApps() {
		System.out.println("request = " + request);
		System.out.println("response = " + response);
		System.out.println("session = " + session);
		return new ModelAndView(BASE + "/listApps");
	}
}
