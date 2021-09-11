<%--
  Created by IntelliJ IDEA.
  User: radof
  Date: 03.09.2021
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>My PARSER</title>
</head>
<body>

Upload File:
<form action="${pageContext.request.contextPath}/load" method="post" enctype="multipart/form-data">
  <input type="file" name="data"/> <br>
  <button type="submit" name="upload">Upload</button>
</form>

Parse XML file:
<form action="${pageContext.request.contextPath}/parser" method="post">
  <button type="submit" name="button" value="DOM">DOMParser</button>
  <button type="submit" name="button" value="SAX">SAXParser</button>
  <button type="submit" name="button" value="STAX">StAXParser</button>
</form>

</body>
</html>
