<%@include file="/common/taglib.jsp" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User Management</title>
</head>
<body>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Danh sách thành viên</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                <a href="<c:url value="/user/add" />" class="btn btn-sm btn-success">Thêm mới</a>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /row -->
        <div class="row">
            <div class="col-sm-12">
                <div class="white-box">
                    <div class="table-responsive">
                        <table class="table" id="example" style="font-size: 14px; font-weight: 200">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Avatar</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${userModelList}">
                                <tr>
                                    <td><c:out value="${user.getId()}"/></td>
                                    <td><c:out value="${user.getFullname()}"/></td>
                                    <td><c:out value="${user.getEmail()}"/></td>
                                    <td><c:out value="${user.getRole_name()}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty user.getAvatar()}">
                                                <img src="<c:out value="${appRootDir}plugins/images/users/${user.getAvatar()}" />" class="img-circle" width="50" height="50" alt="avatar_user">
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa fa-user-circle-o fa-fw" aria-hidden="true"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="<c:url value='/user/update?user_id=${user.getId()}' />" class="btn btn-sm btn-primary">Sửa</a>
                                        <a href="#" class="btn btn-sm btn-danger btn-deleteUser" fileName="${user.getAvatar()}" userId="${user.getId()}">Xóa</a>
                                        <a href="<c:url value='/user/details?user_id=${user.getId()}' />" class="btn btn-sm btn-info">Xem</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
</div>
<!-- /#page-wrapper -->
</body>
</html>