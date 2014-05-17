<tiles:insert template="/WEB-INF/view/common/layout.jsp">
<tiles:put name="title" value="お仕事検索 / Demo (デモ)"/>
<tiles:put name="content" type="string">
<link rel="stylesheet" type="text/css" href="/css/demo/common.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/demo/sogo_header.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/demo/sogo_body.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/demo/sogo_footer.css" media="all" />
<s:form>
<div id="layout">
    <%@ include file="/WEB-INF/view/common/header.jsp"%>
        <div id="sideline">
            <div style="MARGIN-BOTTOM: 250px" id="content">
                <span style="font-size: 20px; font-weight: bold; color: #FF2F00;">業務エラー</span>
                <div class="bigtitle">&nbsp;</div>
                <p class="txt12_gray2 maT10B20L10">
                     <%@ include file="/WEB-INF/view/common/messagearea.jsp" %>
                </P>
                <div><img alt="" src="temp_files/spacer.gif" width="804" height="1"></div>
                <div class="twinbtn">
                    <c:if test="${errorForm.forwardUrl == null || errorForm.forwardTitle == null}">
                        <s:link href="/demo/topPage/" styleClass="x_linkSubmit"><img alt="" src="/img/demo/btn_backtohome_off.gif" width="100" height="40"></s:link>
                    </c:if>
                    <c:if test="${errorForm.forwardUrl != null && errorForm.forwardTitle != null}">
                        <s:link href="${f:h(errorForm.forwardUrl)}" styleClass="x_linkSubmit">${f:h(errorForm.forwardTitle)}</s:link>
                    </c:if>
                </div>
            </div>
<div id="seoSiteLink"></div>
<%@ include file="/WEB-INF/view/common/footer.jsp"%>
</div>
    <div id="lastbox"><img alt="" src="/img/demo/footer1.gif" width="820" height="6"></div>
</div>
</s:form>
</tiles:put>
</tiles:insert>



