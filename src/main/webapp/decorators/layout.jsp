<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><dec:title/></title>
    <jsp:include page="/common/user-management/head.jsp"/>
    <dec:head/>
</head>
<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>

<div id="wrapper">
    <jsp:include page="/common/user-management/header.jsp"/>

    <jsp:include page="/common/user-management/side-bar.jsp"/>

    <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    <dec:body/>
</div>
<jsp:include page="/common/user-management/script.jsp" />
</body>
</html>


