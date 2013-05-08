// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.ApplicationInitializerFilter;
import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.ExceptionReporter;
import org.apache.tapestry5.services.RequestExceptionHandler;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.ResponseRenderer;
import org.chenillekit.template.services.FreeMarker;
import org.chenillekit.template.services.TemplateService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.AccessDecisionManager;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.AuthenticationManager;

import vn.pyco.tinycms.services.ContentService;
import vn.pyco.tinycms.web.services.internal.NoBodyResponseResultProcessor;
import vn.pyco.tinycms.web.services.internal.PageRenderServiceImpl;
import vn.pyco.tinycms.web.services.internal.SecuredPageFilter;
import vn.pyco.tinycms.web.services.internal.SecurityCheckerImpl;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class AppModule {
    public static void bind(ServiceBinder binder) {
        binder.bind(RequestFilter.class, SecuredPageFilter.class).withId("securedPageFilter");
    }
    
    public static SecurityChecker buildSecurityChecker(
                                    final AuthenticationManager authenticationManager,
                                    @InjectService("tinyCMSAccessDecisionManager")
                                    final AccessDecisionManager httpRequestAccessDecisionManager)
                                    throws Exception {
        SecurityCheckerImpl checker = new SecurityCheckerImpl();
        checker.setAuthenticationManager(authenticationManager);
        checker.setAccessDecisionManager(httpRequestAccessDecisionManager);
        checker.afterPropertiesSet();
        
        return checker;
    }
    
    public static PageRenderService buildPageRenderService(
                                    HttpServletRequest request,
                                    @FreeMarker
                                    final TemplateService templateService,
                                    final ContentService contentService) {
        PageRenderService pageRenderService = new PageRenderServiceImpl(request.getContextPath(), templateService, contentService);
        
        return pageRenderService;
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en,vi");
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
        configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }
    
    public void contributeApplicationInitializer(OrderedConfiguration<ApplicationInitializerFilter> configuration,
                                    @Inject ApplicationContext applicationContext) {
        configuration.add("tinyCMSInitializer", new TinyCMSInitializer(applicationContext));
    }
    
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
                                         @InjectService("securedPageFilter")
                                         RequestFilter securityPageFilter) {
        configuration.add("tinyCMSSecurityPageFilter", securityPageFilter, "after:ErrorFilter");
    }
    
    public static void contributeFreeMarkerService(MappedConfiguration<String, freemarker.template.Configuration> configuration) {
        freemarker.template.Configuration fmConfiguration = new freemarker.template.Configuration();
        fmConfiguration.setWhitespaceStripping(false);
        fmConfiguration.setClassForTemplateLoading(fmConfiguration.getClass(), "/");
        configuration.add("freemarker.configuration", fmConfiguration);
    }
    
    public void contributeComponentEventResultProcessor(
                                    MappedConfiguration<Class, ComponentEventResultProcessor> configuration,
                                    Response response) {
        configuration.add(NoBodyResponse.class, new NoBodyResponseResultProcessor(response));
    }
    
    public RequestExceptionHandler decorateRequestExceptionHandler(
                                    final HttpServletResponse response,
                                    final ResponseRenderer renderer,
                                    final ComponentSource componentSource) {
        return new RequestExceptionHandler() {
            public void handleRequestException(Throwable exception) throws IOException {
                Throwable throwable = exception;
                while (throwable != null) {
                    if (throwable instanceof AccessDeniedException) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                    throwable = throwable.getCause();
                }
                ExceptionReporter errors = (ExceptionReporter) componentSource.getPage(vn.pyco.tinycms.web.pages.Exception.class);
                errors.reportException(exception);
                renderer.renderPageMarkupResponse("Exception");
            }
        };
    }
}
