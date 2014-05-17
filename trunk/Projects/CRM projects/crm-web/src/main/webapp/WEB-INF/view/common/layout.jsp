<html>
    <head>
        <jsp:include page="/xenlon-ui/xenlon-ui-conf.jsp" />
        <link id="x_cssByUA" type="text/css" rel="stylesheet" />
        <script type="text/javascript" charset="UTF-8" src="/js/jquery-ui-i18n/jquery.ui.datepicker-ja.js"></script>
        <script type="text/javascript" charset="UTF-8" src="/js/common.js"></script>
        <script type="text/javascript" charset="UTF-8" src="/js/demo-function.js"></script>
        <title><tiles:getAsString name="title" /></title>
        
        <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%
            response.setHeader("Expires","0");
            response.setHeader("Cache-Control","no-cache, must-revalidate");
            response.setHeader("Pragma","no-cache");
        %>
    </head>
    <body>
        <tiles:insert page="/WEB-INF/view/common/noscript.jsp" />
        <tiles:insert page="/xenlon-ui/xenlon-ui-plugins.jsp" />
        <div class="x_mainContents">
            <tiles:insert attribute="content" />
        </div>
        <iframe style="height:0px;width:0px;visibility:hidden" src="about:blank">
           this frame prevents back forward cache
        </iframe>
    </body>
</html>
