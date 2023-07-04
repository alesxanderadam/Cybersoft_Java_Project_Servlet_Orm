<%@ page import="java.util.List" %>
<%@ page import="entity.RoleModel" %>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Pixel Admin</title>
</head>

<body>
<%--    <!-- Page Content -->--%>
<%--    <div id="page-wrapper">--%>
<%--        <div class="container-fluid">--%>
<%--            <div class="row bg-title">--%>
<%--                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">--%>
<%--                    <h4 class="page-title">Chi tiết thành viên</h4>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- /.row -->--%>
<%--            <!-- .row -->--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-4 col-xs-12">--%>
<%--                    <div class="white-box">--%>
<%--                        <div class="user-bg"><img width="100%" alt="user" src="../../plugins/images/large/img1.jpg">--%>
<%--                            <div class="overlay-box">--%>
<%--                                <div class="user-content">--%>
<%--                                    <a href="javascript:void(0)"><img src="../../plugins/images/users/genu.jpg"--%>
<%--                                                                      class="thumb-lg img-circle" alt="img"></a>--%>
<%--                                    <h4 class="text-white">Nguyễn Văn Tèo</h4>--%>
<%--                                    <h5 class="text-white">info.teo@gmail.com</h5>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-8 col-xs-12">--%>
<%--                    <!-- BEGIN THỐNG KÊ -->--%>
<%--                    <div class="row">--%>
<%--                        <!--col -->--%>
<%--                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">--%>
<%--                            <div class="white-box">--%>
<%--                                <div class="col-in row">--%>
<%--                                    <div class="col-xs-12">--%>
<%--                                        <h3 class="counter text-right m-t-15 text-danger">20%</h3>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-xs-12">--%>
<%--                                        <i data-icon="E" class="linea-icon linea-basic"></i>--%>
<%--                                        <h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-md-12 col-sm-12 col-xs-12">--%>
<%--                                        <div class="progress">--%>
<%--                                            <div class="progress-bar progress-bar-danger" role="progressbar"--%>
<%--                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"--%>
<%--                                                 style="width: 20%"></div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <!-- /.col -->--%>
<%--                        <!--col -->--%>
<%--                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">--%>
<%--                            <div class="white-box">--%>
<%--                                <div class="col-in row">--%>
<%--                                    <div class="col-xs-12">--%>
<%--                                        <h3 class="counter text-right m-t-15 text-megna">50%</h3>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-xs-12">--%>
<%--                                        <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>--%>
<%--                                        <h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-md-12 col-sm-12 col-xs-12">--%>
<%--                                        <div class="progress">--%>
<%--                                            <div class="progress-bar progress-bar-megna" role="progressbar"--%>
<%--                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"--%>
<%--                                                 style="width: 50%"></div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <!-- /.col -->--%>
<%--                        <!--col -->--%>
<%--                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">--%>
<%--                            <div class="white-box">--%>
<%--                                <div class="col-in row">--%>
<%--                                    <div class="col-xs-12">--%>
<%--                                        <h3 class="counter text-right m-t-15 text-primary">30%</h3>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-xs-12">--%>
<%--                                        <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>--%>
<%--                                        <h5 class="text-muted vb text-center">HOÀN THÀNH</h5>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-md-12 col-sm-12 col-xs-12">--%>
<%--                                        <div class="progress">--%>
<%--                                            <div class="progress-bar progress-bar-primary" role="progressbar"--%>
<%--                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"--%>
<%--                                                 style="width: 30%"></div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <!-- /.col -->--%>
<%--                    </div>--%>
<%--                    <!-- END THỐNG KÊ -->--%>

<%--                </div>--%>
<%--            </div>--%>
<%--            <br/>--%>
<%--            <!-- /.row -->--%>
<%--            <!-- BEGIN DANH SÁCH CÔNG VIỆC -->--%>
<%--            <h4>DANH SÁCH CÔNG VIỆC</h4>--%>
<%--            <div class="row">--%>
<%--                <div class="col-sm-12">--%>
<%--                    <div class="white-box">--%>
<%--                        <div class="table-responsive">--%>
<%--                            <table class="table" id="example" style="font-size: 14px; font-weight: 200">--%>
<%--                                <thead>--%>
<%--                                <tr>--%>
<%--                                    <th>STT</th>--%>
<%--                                    <th>Tên Công Việc</th>--%>
<%--                                    <th>Dự Án</th>--%>
<%--                                    <th>Ngày Bắt Đầu</th>--%>
<%--                                    <th>Ngày Kết Thúc</th>--%>
<%--                                    <th>Trạng Thái</th>--%>
<%--    &lt;%&ndash;                                <th>Hành Động</th>&ndash;%&gt;--%>
<%--                                </tr>--%>
<%--                                </thead>--%>
<%--                                <tbody>--%>
<%--                                <c:forEach var="task" items="${userTaskDetail}">--%>
<%--                                    <tr>--%>
<%--                                        <td>${task.getId()}</td>--%>
<%--                                        <td>${task.getName()}</td>--%>
<%--                                        <td>${task.getJob_name()}</td>--%>
<%--                                        <td>${task.getStart_date()}</td>--%>
<%--                                        <td>${task.getEnd_date()}</td>--%>
<%--                                        <td>--%>
<%--                                            <label>--%>
<%--                                                <select class="form-control form-control-line">--%>
<%--                                                    <c:forEach var="status" items="${statusModelList}">--%>
<%--                                                        <option status_id="${status.getId()}" value="${status.getId()}"--%>
<%--                                                                <c:if test="${status.getId() == task.getStatus_id()}">selected</c:if>>${status.getName()}</option>--%>
<%--                                                    </c:forEach>--%>
<%--                                                </select>--%>
<%--                                            </label>--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            <button task_id="${task.getId()}" user_id="${task.getUser_id()}" class="btn btn-sm btn-primary btn-updateTask">Cập nhật</button>--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>
<%--                                </c:forEach>--%>
<%--                                </tbody>--%>
<%--                            </table>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- END DANH SÁCH CÔNG VIỆC -->--%>
<%--        </div>--%>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Chi tiết thành viên</h4>
            </div>
        </div>
        <!-- /.row -->
        <!-- .row -->
        <div class="row">
            <div class="col-md-4 col-xs-12">
                <div class="white-box">
                    <div class="user-bg">
                        <img width="100%" alt="user"
                             src="<c:out value="${appRootDir}plugins/images/users/${userDetailModel.getAvatar()}" />"/>
                        <div class="overlay-box">
                            <div class="user-content">
                                <a href="javascript:void(0)"><img
                                        src="<c:out value="${appRootDir}plugins/images/users/${userDetailModel.getAvatar()}" />"
                                        class="thumb-lg img-circle" alt="img"></a>
                                <h4 class="text-white">${userDetailModel.getFullname()}</h4>
                                <h5 class="text-white">${userDetailModel.getEmail()}</h5>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-md-8 col-xs-12">
                <!-- BEGIN THỐNG KÊ -->
                <div class="row">
                    <div class="row">
                        <c:forEach var="status" items="${statusModelList}">
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box">
                                    <div class="col-in row">
                                        <div class="col-xs-12">
                                            <h3 class="counter text-center m-t-15 text-megna">
                                                <c:set var="taskCount" value="0"/>
                                                <c:forEach var="task" items="${userTaskDetail}">
                                                    <c:if test="${task.getStatus_id() == status.getId()}">
                                                        <c:set var="taskCount" value="${taskCount + 1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:out value="${taskCount}"/>
                                            </h3>
                                        </div>
                                        <div class="col-xs-12">
                                            <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                                            <h5 class="text-muted vb text-center">${status.getName()}</h5>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-megna" role="progressbar"
                                                     aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                     style="width: 50%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- END THỐNG KÊ -->

            </div>
        </div>
        <br/>
        <!-- /.row -->
        <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
        <div class="flex justify-content-between align-items-center"
             style="display: flex; justify-content: space-between; align-items: center;">
            <h4>DANH SÁCH CÔNG VIỆC</h4>
        </div>
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
                                <th>Ngày Bắt Đầu</th>
                                <th>Ngày Kết Thúc</th>
                                <th>Trạng Thái</th>
                                <th>Hành Động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="task" items="${userTaskDetail}">
                                <tr>
                                    <td>${task.getId()}</td>
                                    <td>${task.getName()}</td>
                                    <td>${task.getJob_name()}</td>
                                    <td>${task.getStart_date()}</td>
                                    <td>${task.getEnd_date()}</td>
                                    <td>
                                        <label>
                                            <select class="form-control form-control-line">
                                                <c:forEach var="status" items="${statusModelList}">
                                                    <option value="${status.getId()}"
                                                            <c:if test="${status.getId() == task.getStatus_id()}">selected</c:if>>${status.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                    </td>
                                    <td>
                                        <p task_id="${task.getId()}" user_id="${task.getUser_id()}"
                                           class="btn btn-sm btn-primary btn-updateTask">Cập nhật
                                        </p>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- END DANH SÁCH CÔNG VIỆC -->
    </div>
    <!-- /.container-fluid -->
</div>
</body>

</html>