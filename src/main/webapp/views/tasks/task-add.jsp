<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Task Add</title>
</head>

<body>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Thêm mới công việc</h4>
            </div>
        </div>
        <!-- /.row -->
        <!-- .row -->
        <div class="row">
            <div class="col-md-2 col-12"></div>
            <div class="col-md-8 col-xs-12">
                <div class="white-box">
                    <form class="form-horizontal form-material" method="post">
                        <div class="form-group">
                            <label class="col-md-12">Dự án</label>
                            <div class="col-md-12">
                                <select class="form-control form-control-line" name="job_id">
                                    <c:forEach var="job" items="${jobModelList}">
                                        <option value="${job.getId()}">${job.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Tên công việc</label>
                            <div class="col-md-12">
                                <input type="text" placeholder="Tên công việc"
                                       class="form-control form-control-line" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Người thực hiện</label>
                            <div class="col-md-12">
                                <select class="form-control form-control-line" name="user_id">
                                    <c:forEach var="user" items="${userModelList}">
                                        <option value="${user.getId()}">${user.getFullname()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Ngày bắt đầu</label>
                            <div class="col-md-12">
                                <input type="text" placeholder="dd/MM/yyyy"
                                       class="form-control form-control-line" name="start_date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Ngày kết thúc</label>
                            <div class="col-md-12">
                                <input type="text" placeholder="dd/MM/yyyy"
                                       class="form-control form-control-line" name="end_date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Trạng Thái</label>
                            <div class="col-md-12">
                                <select class="form-control form-control-line" name="status_id">
                                    <c:forEach var="status" items="${statusModelList}">
                                        <option value="${status.getId()}">${status.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-success">Lưu lại</button>
                                <a href="<c:url value="/task" />" class="btn btn-primary">Quay lại</a>
                            </div>
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