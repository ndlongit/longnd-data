<html>
    <head>
        <jsp:include page="../../../xenlon-ui/xenlon-ui-conf.jsp" />
        <link type="text/css" rel="stylesheet" href="/stylesheets/common.css" />
        <link id="x_cssByUA" type="text/css" rel="stylesheet" />
        <script type="text/javascript" charset="UTF-8" src="/javascripts/jquery-ui-i18n/jquery.ui.datepicker-ja.js"></script>
        <script type="text/javascript" charset="UTF-8" src="/javascripts/common.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title" /></title>
    </head>
    <body>
        <tiles:insert page="../common/noscript.jsp" />
        <tiles:insert page="../../../xenlon-ui/xenlon-ui-plugins.jsp" />
        <div class="x_mainContents">
            <tiles:insert page="../common/header.jsp" />
            <tiles:insert attribute="content" />
            <tiles:insert page="../common/footer.jsp" />
        </div>
    </body>
</html>
