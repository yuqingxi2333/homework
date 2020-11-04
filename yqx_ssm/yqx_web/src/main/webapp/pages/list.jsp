<%--
  Created by IntelliJ IDEA.
  User: Yuqingxi
  Date: 2020/10/28
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>01_测试类_查询所有</title>
</head>
<body>

<div style="text-align: center">
    <h1>查询所有测试类</h1>
      <c:forEach items="${list}" var="pro">
         ${pro.productName}<br>
      </c:forEach>
</div>

</body>
</html>
