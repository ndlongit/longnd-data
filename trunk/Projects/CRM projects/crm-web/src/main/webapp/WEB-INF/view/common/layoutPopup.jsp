<html>
    <head>
        <base target="_self" />
        <jsp:include page="/xenlon-ui/xenlon-ui-conf.jsp" />
        <link id="x_cssByUA" type="text/css" rel="stylesheet" />
        <script type="text/javascript" charset="UTF-8" src="/js/jquery-ui-i18n/jquery.ui.datepicker-ja.js"></script>
        <script type="text/javascript" charset="UTF-8" src="/js/common.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title" /></title>
    </head>
    <body>
        <tiles:insert page="/WEB-INF/view/common/noscript.jsp" />
        <tiles:insert page="/xenlon-ui/xenlon-ui-plugins.jsp" />
        <div class="x_mainContents">
            <tiles:insert attribute="content" />
        </div>
    </body>
</html>
