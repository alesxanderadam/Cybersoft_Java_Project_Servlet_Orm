<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top m-b-0">
    <div class="navbar-header">
        <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse"
           data-target=".navbar-collapse">
            <i class="fa fa-bars"></i>
        </a>
        <div class="top-left-part">
            <a class="logo" href="<c:url value="/" />">
                <b>

                    <img src="<c:url value="/plugins/images/pixeladmin-logo.png" /> " alt="home"/>
                </b>
                <span class="hidden-xs">
                            <img src="<c:url value="/plugins/images/pixeladmin-text.png" /> " alt="home"/>
                        </span>
            </a>
        </div>
        <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
            <li>
                <form role="search" class="app-search hidden-xs">
                    <input type="text" placeholder="Search..." class="form-control">
                    <a href="">
                        <i class="fa fa-search"></i>
                    </a>
                </form>
            </li>
        </ul>
        <ul class="nav navbar-top-links navbar-right pull-right">
            <li>
                <div class="dropdown">
                    <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#">
                        <img src="plugins/images/users/varun.jpg" alt="user-img" width="36"
                             class="img-circle"/>
                        <b class="hidden-xs">${userName}</b>
                    </a>
                    <ul class="dropdown-menu">
                        <c:if test="${not empty email}">
                            <li><a href="<c:url value="/profile?user_id=${infUserLogin.getId()}" />">Thông tin cá nhân</a></li>
                            <li class="divider"></li>
                        </c:if>
                        <form action="<c:url value="/auth/checkout" />" class="form-horizontal form-material"
                              method="post"
                              style="
                                 display: flex;
                                 flex-direction: column;
                                 justify-content: center;
                                 align-items: center;
                                 margin: 0;
                                ">
                            <div class="form-group" style="margin: 0 auto">
                                <div class="col-sm-12">
                                    <c:if test="${not empty email}">
                                        <button type="submit" class="btn btn-danger">Hello ${email}. Click to Logout
                                        </button>
                                        <input type="hidden" value="logout" name="action">
                                    </c:if>
                                    <c:if test="${empty email}">
                                        <button type="submit" class="btn btn-primary">Login</button>
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
    <!-- /.navbar-header -->
    <!-- /.navbar-top-links -->
    <!-- /.navbar-static-side -->
</nav>