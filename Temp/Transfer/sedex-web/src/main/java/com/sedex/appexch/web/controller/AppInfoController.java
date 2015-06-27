package com.sedex.appexch.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sedex.appexch.model.AppInfo;
import com.sedex.appexch.web.controller.base.AbstractController;

@Controller
@RequestMapping(value = "/" + AppInfoController.NAME_SPACE + "/")
public class AppInfoController extends AbstractController {

    protected static final String NAME_SPACE = "app";

    private static final String MODEL_NAME = "AppInfo";

    @RequestMapping(value = PRE_CREATE, method = RequestMethod.GET)
    public ModelAndView createAppPre(@ModelAttribute(MODEL_NAME) AppInfo app, BindingResult result) {
        mnv.setViewName(NAME_SPACE + "/uploadApp");
        return mnv;
    }

    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public ModelAndView createApp(@ModelAttribute(MODEL_NAME) AppInfo app, BindingResult result) {
        return new ModelAndView(NAME_SPACE + "/listApps");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView editAppPre(@PathVariable("id") String appId) {
        logger.info("appId = " + appId);
        return new ModelAndView(NAME_SPACE + "/listApps");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView editApp() {
        return new ModelAndView(NAME_SPACE + "/listApps");
    }

    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public ModelAndView listApps(@ModelAttribute(MODEL_NAME) AppInfo app, BindingResult result) {
        mnv.setViewName(NAME_SPACE + "/listApps");
        return mnv;
    }
}
