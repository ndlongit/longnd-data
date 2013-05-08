package org.tapestry5.demo.web.pages.base;

import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public abstract class AbstractPage {

	@Inject
	private Messages messages;

	public String getTitle() {
		return getAppName() + " - " + getPageTitle();
	}

	public String getAppName() {
		return messages.get("global.app.name");
	}

	protected String getPageTitle() {
		return messages.get("page.title." + getPageClassName());
	}

	public String getPageClassName() {
		return getClass().getSimpleName();
	}

	public String getSidebarTitle() {
		return "";
	}

	protected Messages getMessages() {
		return messages;
	}
}
