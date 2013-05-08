package org.tapestry5.demo.web.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.java.demo.model.Account;
import org.java.demo.util.WebConstant;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

/**
 * Layout component for pages of application tapestry5-demo.
 */
// @IncludeStylesheet({WebConstant.CSS_FOLDER + "/layout.css"})
@IncludeStylesheet( { "classpath:META-INF/layout/layout.css" })
@SuppressWarnings("unused")
public class Layout {
	/** The page title, for the <title> element and the <h1>element. */
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;

	@Property
	private String pageName;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String sidebarTitle;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private Block sidebar;

	@Inject
	private ComponentResources resources;

	public String getClassForPageName() {
		return resources.getPageName().equalsIgnoreCase(pageName) ? "current_page_item" : null;
	}

	public String[] getPageNames() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth == null) {
//			return new String[] { "Login", "Index", "admin/CreateRole", "admin/CreateUser", "teacher/InputScores", "teacher/ViewStudentScores",
//			        "student/ViewScores" };
//		} else {
//			return new String[] { "Logout", "Index", "admin/CreateRole", "admin/CreateUser", "teacher/InputScores", "teacher/ViewStudentScores",
//			        "student/ViewScores" };
//		}
		if (auth == null) {
			return new String[] { "Login", "Index", "admin/CreateRole", "admin/CreateUser"};
		} else {
			return new String[] { "Logout", "Index", "admin/CreateRole", "admin/CreateUser"};
		}
	}
}
