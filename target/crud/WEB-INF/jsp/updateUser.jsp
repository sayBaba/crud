<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>修改</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/updateUserInfo" method="post">
        用户id：<input type="text" value="${userResp.userId}" disabled  /> <br>
        <input type="hidden" value="${userResp.userId}" name="userId" /> <br>
        登录名：<input type="text" value="${userResp.loginName}"  name="userName"/>  <br>
        邮件：<input type="text" value="${userResp.email}"  name="email"/> <br>
        地址：<input type="text" value="${userResp.address}"  name="address"/> <br>
        备注：<input type="text" value="${userResp.remark}"  name="remark"/> <br>
        <input type="submit" value="提交">
    </form>


</body>
</html>
