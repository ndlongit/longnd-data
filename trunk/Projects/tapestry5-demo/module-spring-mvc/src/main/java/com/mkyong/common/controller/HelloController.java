package com.mkyong.common.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HelloController {

    @RequestMapping(value = "/p1")
    public String printWelcome(ModelMap model, @CookieValue("JSESSIONID") String cookie) {

        model.addAttribute("message", new Date());
        return "hello";

    }

    /** Not accept GET? */
    @RequestMapping(value = "/p2", method = RequestMethod.POST)
    public void handle(@RequestBody String body, Writer writer) throws IOException {
        writer.write(body);
    }

    /** Not accept GET? */
    @RequestMapping(value = "/p3", method = RequestMethod.POST)
    @ResponseBody
    public String helloWorld() {
        return "<h1>Hello World</h1>";
    }

    @RequestMapping("/p4")
    public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity) throws UnsupportedEncodingException {
        String requestHeader = requestEntity.getHeaders().getFirst("MyRequestHeader");
        byte[] requestBody = requestEntity.getBody();

        // do something with request header and body
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
    }

    /** It's called before all actions */
    @ModelAttribute
    public void populateModel(@RequestParam(required = false, defaultValue = "1") Integer id,
            @RequestParam(required = false, defaultValue = "Long Nguyen") String name, Model model) {
        Account account2 = new Account(id, name);
        model.addAttribute("account", account2);
    }

    /** It's called before all actions */
    @ModelAttribute
    public Account addAccount(@RequestParam(required = false, defaultValue = "1") Integer id,
            @RequestParam(required = false, defaultValue = "Long Nguyen") String name) {
        return new Account(id, name);
    }

    @RequestMapping
    public String myHandleMethod(WebRequest request, Model model) {

        long lastModified = 11111;// 1. application-specific calculation

        if (request.checkNotModified(lastModified)) {
            // 2. shortcut exit - no further processing necessary
            return null;
        }

        // 3. or otherwise further request processing, actually preparing content
        // model.addAttribute(...);
        return "myViewName";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            // store the bytes somewhere
            return "redirect:uploadSuccess";
        }

        return "redirect:uploadFailure";
    }
}
