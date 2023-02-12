<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="loginOutLink" value="${sessionScope.id == null ? 'login/login' : 'login/logout'}"/>
<%--<c:set var="loginOut" value="${pageContext.request.session.getAttribute('id') == null? 'Login' : 'Logout'}"/>--%>
<c:set var="userName" value="${pageContext.request.session.getAttribute('id') == null? '' : '様、ようこそ' }"/>

<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOut" value="${loginId== null ? 'Login' : 'ID:'+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Kraveler</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/menu.css?after'/>">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
  <ul>
    <li id="logo"><a href="<c:url value='/'/>">Kraveler</a></li>
    <li><a href="<c:url value='/'/>">Home</a></li>
    <li><a href="<c:url value='/board/list'/>">Board</a></li>
    <li><a href="<c:url value='${loginOutLink}'/>" class="loginOut">${loginOut}</a></li>
    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
    <li><a href=""><i class="fas fa-search small"></i></a></li>
  </ul>
</div>
<div style="text-align:center">

  <img src="./resources/img/mainImg.png" class="mainPhoto">
  <h1>This is HOME</h1>
  <h1>This is HOME</h1>
  <h1>This is HOME</h1>
  <h1>${loginOut}</h1>
  <h1>${loginId}${userName}</h1>
</div>
