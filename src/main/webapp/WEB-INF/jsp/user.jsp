<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<script src="../../${pageContext.request.contextPath}/jquery-1.8.3.js"></script>
<head>
    <title>用户信息</title>
</head>
<body>
    <table>
        <tr>
            <th>用户ID</th>
            <th>登录名</th>
            <th>用户名</th>
            <th>邮件</th>
            <th>地址</th>
            <th>备注</th>
        </tr>
        <c:forEach items="${userList}" var="list" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>${list.userId}</td>
                    <td>${list.loginName}</td>
                    <td>${list.userName}</td>
                    <td>${list.email}</td>
                    <td>${list.address}</td>
                    <td>${list.remark}</td>
                    <td><input type="button" value="修改" onclick="update(this)"></td>
                </tr>
        </c:forEach>
    </table>
</body>

<script>

    function update(va) {
        var userId= $(va).parent().parent().children("td").get(1).innerHTML;
        var loginName= $(va).parent().parent().children("td").get(2).innerHTML;
        var userName= $(va).parent().parent().children("td").get(3).innerHTML;
        var email= $(va).parent().parent().children("td").get(4).innerHTML;
        var address= $(va).parent().parent().children("td").get(5).innerHTML;
        var remark= $(va).parent().parent().children("td").get(6).innerHTML;

        location.href='${pageContext.request.contextPath}/updateUser?userId='+userId+"&loginName+"+loginName
        +"&userName="+userName+"&email="+email+"&address="+address+"&remark="+remark;
    }

</script>


</html>
