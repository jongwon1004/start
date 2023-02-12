<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style>
    h1 {
      text-align: center;
    }

    .homehe {
      text-align: center;
      font-size: 30px;
    }
  </style>
</head>
<body>
<h1>${user.name}様、会員登録が完了しました。</h1>
<h1>id=${user.id}</h1>
<h1>pwd=${user.pwd}</h1>
<h1>name=${user.name}</h1>
<h1>email=${user.email}</h1>
<h1>birth=${user.birth}</h1>
<h1>sns=${user.sns}</h1>

<div class="homehe"><a href="<c:url value='/'/>">Homeへ</a></div>

</body>
</html>