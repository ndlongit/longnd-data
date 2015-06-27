package com.sedex.appexch.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sedex.appexch.exception.DataConstraintException;
import com.sedex.appexch.model.AppInfo;
import com.sedex.appexch.service.AppInfoService;
import com.sedex.appexch.web.controller.base.AbstractController;

@Controller
@RequestMapping(value = "/" + AppInfoController.NAME_SPACE + "/")
public class AppInfoController extends AbstractController {

    protected static final String NAME_SPACE = "app";
    private static final String MODEL_NAME = "AppInfo";

    @Resource
    private AppInfoService appInfoService;

    @RequestMapping(value = PRE_CREATE, method = RequestMethod.GET)
    public ModelAndView createAppPre(@ModelAttribute(MODEL_NAME) AppInfo app, BindingResult result) {
        ModelAndView mnv = initModelAndView(NAME_SPACE + "/uploadApp");
        String action = getFullUrl(NAME_SPACE + "/" + CREATE);
        mnv.addObject("action", action);

        return mnv;
    }

    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public ModelAndView createApp(@ModelAttribute(MODEL_NAME) AppInfo app, BindingResult result) {
        try {
            appInfoService.save(app);
        } catch (DataConstraintException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mnv = initModelAndView(NAME_SPACE + "/listApps");
        return mnv;
    }

    @RequestMapping(value = PRE_EDIT + "/{id}", method = RequestMethod.GET)
    public ModelAndView editAppPre(@PathVariable("id") String appId) {
        logger.info("appId = " + appId);
        return new ModelAndView(NAME_SPACE + "/uploadApp");
    }

    @RequestMapping(value = EDIT + "/{id}", method = RequestMethod.POST)
    public ModelAndView editApp() {
        return new ModelAndView(NAME_SPACE + "/uploadApp");
    }

    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public ModelAndView listApps(@ModelAttribute(MODEL_NAME) AppInfo app, BindingResult result) {
        ModelAndView mnv = initModelAndView(NAME_SPACE + "/listApps");
        return mnv;
    }
}
