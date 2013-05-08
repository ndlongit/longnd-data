package com.structis.fichesst.server.service.exportpdf;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.structis.fichesst.shared.util.Constants;


@SuppressWarnings("serial")
public abstract class ExportPdfServlet extends HttpServlet {//DependencyInjectionRemoteServiceServlet  {
	
	protected ResourceBundleMessageSource messageSource;
	protected String headerPathPatern = "";
	protected String footerPathPatern = "";
	protected String headerFileName = "header_portrait.jpg";
	protected String footerFileName = "footer_portrait.jpg";
	protected String headerPathPaternLandScape = "";
	protected String footerPathPaternLandScape = "";
	protected String headerFileNameLandScape = "header_landscape.jpg";
	protected String footerFileNameLandScape = "footer_landscape.jpg";
	protected SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
	protected Locale clientLocale = Locale.FRENCH;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		messageSource = new ResourceBundleMessageSource();	
	    messageSource.setBasename("com.structis.fichesst.client.message.Messages");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			response.setContentType("application/pdf");			
			String localeString = request.getParameter("locale");	
			if(localeString != null){
				clientLocale = new Locale(localeString);
			}
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			
		}
		//Will call doGet or doPost of sub-class depending
		super.service(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//real path solution
		this.headerPathPatern = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + headerFileName;
		this.footerPathPatern = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + footerFileName;
		this.headerPathPaternLandScape = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + headerFileNameLandScape;
		this.footerPathPaternLandScape = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + footerFileNameLandScape;
		//url path solution
		/*
		String baseUrl = "http://"+request.getLocalName()+":"+request.getLocalPort();
		String contextName = this.getServletContext().getContextPath();
		if(StringUtils.isNotBlank(contextName)) {
			baseUrl +="/"+contextName;
		}
		
		this.headerPathPatern = baseUrl+"/images/UO/" + "%s" + "/" + headerFileName;
		this.footerPathPatern = baseUrl+"/images/UO/" + "%s" + "/" + footerFileName;
		*/
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		//real path solution
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.headerPathPatern = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + headerFileName;
		this.footerPathPatern = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + footerFileName;
		this.headerPathPaternLandScape = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + headerFileNameLandScape;
		this.footerPathPaternLandScape = getServletContext().getRealPath("/img/UO") + File.separator + "%s" + File.separator + footerFileNameLandScape;
	}
}
