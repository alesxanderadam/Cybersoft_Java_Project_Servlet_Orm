<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Job Management</title>
</head>

<body>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Danh sách dự án</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                <a href="<c:url value="/groupwork/add"  />" class="btn btn-sm btn-success">Thêm mới</a>
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
                                <th>Tên Dự Án</th>
                                <th>Ngày Bắt Đầu</th>
                                <th>Ngày Kết Thúc</th>
                                <th>Hành Động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="job" items="${jobModelList}">
                                <tr>
                                    <td><c:out value="${job.getId()}"/></td>
                                    <td><c:out value="${job.getName()}"/></td>
                                    <td><c:out value="${job.getStart_date_string()}"/></td>
                                    <td><c:out value="${job.getEnd_date_string()}"/></td>
                                    <td>
                                        <a href="<c:url value="/groupwork/update?job_id=${job.getId()}" />"
                                           class="btn btn-sm btn-primary">Sửa</a>
                                        <a href="#" class="btn btn-sm btn-danger btn-deleteJob" jobId="${job.getId()}">Xóa</a>
                                        <a href="<c:url value="/" />" class="btn btn-sm btn-info">Xem</a>
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