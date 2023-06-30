<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>User Update</title>
</head>
<body>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Thêm mới thành viên</h4>
            </div>
        </div>
        <!-- /.row -->
        <!-- .row -->
        <div class="row">
            <div class="col-md-2 col-12"></div>
            <div class="col-md-8 col-xs-12">
                <div class="white-box">
                    <form action="<c:url value="/user/update" />" class="form-horizontal form-material" enctype='multipart/form-data' method="post">
                        <div class="form-group">
                            <label class="col-md-12">User Id</label>
                            <div class="col-md-12">
                                <input type="text" hidden="hidden" value="<c:out value="${userDetailModel.getId()}" />"
                                       name="user_id" class="form-control form-control-line">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Full Name</label>
                            <div class="col-md-12">
                                <input type="text" value="<c:out value="${userDetailModel.fullname}" />"
                                       placeholder="Johnathan Doe" name="fullname"
                                       class="form-control form-control-line">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="example-email" class="col-md-12">Email</label>
                            <div class="col-md-12">
                                <input type="email"
                                       placeholder="johnathan@admin.com"
                                       class="form-control form-control-line" name="email"
                                       id="example-email"
                                       value="<c:out value="${userDetailModel.email}" />"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Password</label>
                            <div class="col-md-12">
                                <input type="password" value="<c:out value="${userDetailModel.password}" />"
                                       class="form-control form-control-line" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-12">Select role</label>
                            <div class="col-sm-12">
                                <select class="form-control form-control-line" name="role_id">
                                    <c:forEach var="role" items="${roleList}">
                                        <option value="${role.getId()}"
                                                <c:if test="${role.getId() == userDetailModel.getRoleId()}">selected</c:if>>${role.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="file" name="imageFile"/>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-success">Update User</button>
                            <a href="<c:url value="/user" />" class="btn btn-primary">Back</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-2 col-12"></div>
        </div>
        <!-- /.row -->
    </div>
</div>
<!-- /#page-wrapper -->
</body>
</html>
