<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginOutLink" value="${sessionScope.id == null ? 'login/login' : 'login/logout'}"/>
<c:set var="loginOut" value="${sessionScope.id == null ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>fastcampus</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
</head>
<body>
<div id="menu">
  <ul>
    <li id="logo"><a href="<c:url value='/'/>">Kraveler</a></li>
    <li><a href="<c:url value='/'/>">Home</a></li>
    <li><a href="<c:url value='/board/list'/>">Board</a></li>
    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
    <li><a href=""><i class="fas fa-search small"></i></a></li>
  </ul>
</div>
<script>
  <%--let msg = "${param.msg}"--%>
  let msg = "${msg}"
  if(msg == "WRT_OK") alert("掲示物を登録しました！")
  if(msg == "DEL_OK") alert("削除しました！")
  if(msg == "DEL_ERR") alert("削除を失敗しました！")
  if(msg == "MOD_OK") alert("修正をしました！")
</script>
<div style="text-align:center">
  <button type="button" id="writeBtn" onclick="location.href='<c:url value="/board/write"/>'">掲示物を書く</button>
  <table border="1">
    <tr>
      <th>番号</th>
      <th>タイトル</th>
      <th>名前</th>
      <th>投稿日</th>
      <th>ビュー</th>
    </tr>
    <c:forEach var="boardDto" items="${list}">
      <tr>
        <td>${boardDto.bno}</td>
        <td><a href="<c:url value='/board/read?bno=${boardDto.bno}&page=${page}&pageSize=${pageSize}'/>">${boardDto.title}</a></td>
        <td>${boardDto.writer}</td>
        <td>${boardDto.reg_date}</td>
        <td>${boardDto.view_cnt}</td>
      </tr>
    </c:forEach>
  </table>
  <br>
  <div>
    <c:if test="${ph.showPrev}">
      <a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
    </c:if>
    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
      <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
    </c:forEach>
    <c:if test="${ph.showNext}">
      <a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
    </c:if>
  </div>
</div>
</body>
</html>