<%@include file="/common/taglib.jsp" %>
<!-- Left navbar-header -->
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse slimscrollsidebar">
        <ul class="nav" id="side-menu">
            <li>
                <a href="<c:url value="/index.jsp" />" class="waves-effect">
                    <i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>
                    <span class="hide-menu">Dashboard</span>
                </a>
            </li>
            <c:choose>
                <c:when test="${infUserLogin.getRoleId() == 1}">
                    <li>
                        <a href="<c:url value="/user" />" class="waves-effect">
                            <i class="fa fa-user fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Users</span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/role" />" class="waves-effect">
                            <i class="fa fa-modx fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Roles</span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/groupwork" />" class="waves-effect">
                            <i class="fa fa-table fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Projects</span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/task" />" class="waves-effect">
                            <i class="fa fa-table fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Tasks</span>
                        </a>
                    </li>
                    <li>
                        <a href="../../blank.jsp" class="waves-effect">
                            <i class="fa fa-columns fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Blank Page</span>
                        </a>
                    </li>
                    <li>
                        <a href="../../404.html" class="waves-effect">
                            <i class="fa fa-info-circle fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Error 404</span>
                        </a>
                    </li>
                </c:when>
                <c:when test="${infUserLogin.getRoleId() == 2}">
                    <li>
                        <a href="<c:url value="/groupwork" />" class="waves-effect">
                            <i class="fa fa-table fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Projects</span>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/task" />" class="waves-effect">
                            <i class="fa fa-table fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Tasks</span>
                        </a>
                    </li>
                    <li>
                        <a href="../../blank.jsp" class="waves-effect">
                            <i class="fa fa-columns fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Blank Page</span>
                        </a>
                    </li>
                    <li>
                        <a href="../../404.html" class="waves-effect">
                            <i class="fa fa-info-circle fa-fw" aria-hidden="true"></i>
                            <span class="hide-menu">Error 404</span>
                        </a>
                    </li>
                </c:when>
                <c:when test="${infUserLogin.getRoleId() == 3}">
                </c:when>
            </c:choose>
        </ul>
    </div>
</div>
<!-- Left navbar-header end -->