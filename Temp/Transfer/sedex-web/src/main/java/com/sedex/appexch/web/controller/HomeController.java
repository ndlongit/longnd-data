package com.sedex.appexch.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sedex.appexch.web.controller.base.AbstractController;

@Controller
public class HomeController extends AbstractController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexForm() {
		return new ModelAndView("redirect:contact");
	}
}
