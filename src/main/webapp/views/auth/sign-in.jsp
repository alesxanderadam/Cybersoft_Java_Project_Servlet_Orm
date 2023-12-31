<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V1</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<c:url value="/plugins/images/favicon.png" /> "/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css" />">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/vendor/animate/animate.css" /> ">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/vendor/css-hamburgers/hamburgers.min.css" />">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/vendor/select2/select2.min.css" /> ">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/util.css" /> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css" /> ">
    <!--===============================================================================================-->
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <c:if test="${not empty message}">
            <div class="alert alert-${alert}" role="alert">
                    ${message}
            </div>
        </c:if>

        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="<c:url value="/plugins/images/heo_vui_ve.jpg" />" alt="IMG">
            </div>

            <form action="<c:url value="/auth/signIn" />" class="login100-form validate-form" method="post">
					<span class="login100-form-title">
						Member Login
					</span>

                <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" name="email" placeholder="Email">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                </div>

                <div class="form-check float-right">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe">
                    <label for="exampleCheck1">Check me out</label>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Login
                    </button>
                </div>

                <div class="text-center p-t-12">
						<span class="txt1">
							Forgot
						</span>
                    <a class="txt2" href="#">
                        Username / Password?
                    </a>
                </div>

                <div class="text-center p-t-136">
                    <a class="txt2" href="#">
                        Create your Account
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
                <input type="hidden" value="login" id="action" name="action">
            </form>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="<c:url value="/vendor/jquery/jquery-3.2.1.min.js" /> "></script>
<!--===============================================================================================-->
<script src="<c:url value="/vendor/bootstrap/js/popper.js" /> "></script>
<script src="<c:url value="/vendor/bootstrap/js/bootstrap.min.js" /> "></script>
<!--===============================================================================================-->
<script src="<c:url value="/vendor/select2/select2.min.js" /> "></script>
<!--===============================================================================================-->
<script src="<c:url value="/vendor/tilt/tilt.jquery.min.js" /> "></script>
<script>
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="<c:url value="/js/main.js" /> "></script>
</body>
</html>