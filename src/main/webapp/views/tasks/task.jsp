<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Task Management</title>
</head>

<body>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Danh sách công việc</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                <a href="<c:url value="/task/add" />" class="btn btn-sm btn-success">Thêm mới</a>
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
                                <th>Tên Công Việc</th>
                                <th>Dự Án</th>
                                <th>Người Thực Hiện</th>
                                <th>Ngày Bắt Đầu</th>
                                <th>Ngày Kết Thúc</th>
                                <th>Trạng Thái</th>
                                <th>Hành Động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="task" items="${taskModelList}">
                                <tr>
                                    <td>${task.getId()}</td>
                                    <td>${task.getName()}</td>
                                    <td>${task.getJob_name()}</td>
                                    <td>${task.getUser_name()}</td>
                                    <td>${task.getStart_date_string()}</td>
                                    <td>${task.getEnd_date_string()}</td>
                                    <td>${task.getStatus_name()}</td>
                                    <td>
                                        <a href="<c:url value="/task/update?task_id=${task.getId()}" /> "
                                           class="btn btn-sm btn-primary">Sửa</a>
                                        <a href="#" class="btn btn-sm btn-danger btn-deleteTask" taskId="${task.getId()}">Xóa</a>
                                        <a href="#" class="btn btn-sm btn-info">Xem</a>
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