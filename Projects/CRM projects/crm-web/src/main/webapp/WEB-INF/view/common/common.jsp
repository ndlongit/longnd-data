<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles" %>
<%@ taglib prefix="f" uri="http://sastruts.seasar.org/functions" %>
<%@ taglib prefix="s" uri="http://www.inte.co.jp/cspfw-s" %>
<%@ taglib prefix="fwTiles" uri="http://www.inte.co.jp/cspfw-tiles" %>
<%@ taglib prefix="fw" uri="http://www.inte.co.jp/cspfw" %>
<%@ taglib prefix="fwf" uri="http://www.inte.co.jp/cspfw-f" %>
<%
    response.setHeader("Expires","0");
    response.setHeader("Cache-Control","no-cache, must-revalidate");
    response.setHeader("Pragma","no-cache");
%>
