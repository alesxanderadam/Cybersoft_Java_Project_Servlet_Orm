<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Task Update</title>
</head>

<body>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Chỉnh sửa công việc</h4>
            </div>
        </div>
        <!-- /.row -->
        <!-- .row -->
        <div class="row">
            <div class="col-md-2 col-12"></div>
            <div class="col-md-8 col-xs-12">
                <div class="white-box">
                    <form class="form-horizontal form-material" method="post">
                        <div class="form-group hidden">
                            <div class="col-md-12">
                                <input type="number" value="${taskDetailModel.getId()}"
                                       class="form-control form-control-line" name="id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Dự án</label>
                            <div class="col-md-12">
                                <select class="form-control form-control-line" name="job_id">
                                    <c:forEach var="job" items="${jobModelList}">
                                        <option value="${job.getId()}"
                                                <c:if test="${job.getId() == taskDetailModel.getJob_id()}">selected</c:if> >${job.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Tên công việc</label>
                            <div class="col-md-12">
                                <input type="text" value="${taskDetailModel.getName()}" placeholder="Tên công việc"
                                       class="form-control form-control-line" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Người thực hiện</label>
                            <div class="col-md-12">
                                <select class="form-control form-control-line" name="user_id">
                                    <c:forEach var="user" items="${userModelList}">
                                        <option value="${user.getId()}"
                                                <c:if test="${user.getId() == taskDetailModel.getUser_id()}">selected</c:if>>${user.getFullname()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Ngày bắt đầu</label>
                            <div class="col-md-12">
                                <input type="text" value="${taskDetailModel.getStart_date_string()}"
                                       placeholder="dd/MM/yyyy"
                                       class="form-control form-control-line" name="start_date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Ngày kết thúc</label>
                            <div class="col-md-12">
                                <input type="text" value="${taskDetailModel.getEnd_date_string()}"
                                       placeholder="dd/MM/yyyy"
                                       class="form-control form-control-line" name="end_date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Trạng Thái</label>
                            <div class="col-md-12">
                                <select class="form-control form-control-line" name="status_id">
                                    <c:forEach var="status" items="${statusModelList}">
                                        <option value="${status.getId()}"
                                                <c:if test="${status.getId() == taskDetailModel.getStatus_id()}">selected</c:if>>${status.getName()}</option>
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
<!-- jQuery -->
<script src="<c:url value="/plugins/bower_components/jquery/dist/jquery.min.js" />"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<!-- Menu Plugin JavaScript -->
<script src="<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"/>"></script>
<!--slimscroll JavaScript -->
<script src=<c:url value="/js/jquery.slimscroll.js"/>></script>
<!--Wave Effects -->
<script src=<c:url value="/js/waves.js"/>></script>
<!-- Custom Theme JavaScript -->
<script src=<c:url value="/js/custom.min.js"/>></script>
</body>

</html>