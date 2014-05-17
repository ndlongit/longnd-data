package org.tapestry5.demo.web.pages;

import java.util.Date;

/**
 * Start page of application tapestry5-demo.
 */
public class Index {

    public String getCurrentTime() {
        return new Date().toString();
    }
}
