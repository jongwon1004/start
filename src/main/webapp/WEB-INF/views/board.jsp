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
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
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
  let msg = "${msg}";
  if(msg == "WRT_ERR") alert("登録に失敗しました。再度試してください。");
  if(msg == "MOD_ERR") alert("修正に失敗しました。再度試してください。")
</script>
<div style="text-align:center">
  <h2>掲示物を${mode == "new"? "書く": "見る"}</h2>
  <form action="" id="form">
    <input type="hidden" name="bno" value="${boardDto.bno}"> <!-- 게시물 번호는 입력하면 안되기 떄문에 hidden으로 숨겨줌, 테스트일때는 text로 -->
    <input type="text" name="title" value="${boardDto.title}" ${mode=="new"? '' :'readonly="readonly"'}>
    <textarea name="content" id="" cols="30" rows="10" ${mode=="new"? '' :'readonly="readonly"'}>${boardDto.content}</textarea>
    <button type="button" id="writeBtn" class="btn">書く</button>
    <button type="button" id="modifyBtn" class="btn">수정</button>
    <button type="button" id="removeBtn" class="btn">삭제</button>
    <button type="button" id="listBtn" class="btn">목록</button>
  </form>
</div>
<script>
  $(document).ready(function (){  // main()
    $('#listBtn').on("click",function (){
      location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}";
    });


    $('#modifyBtn').on("click",function (){
      // 읽기 상태이면 수정 상태로 변경
      let form = $('#form');
      let isReadOnly = $("input[name = title]").attr('readonly');

      if(isReadOnly == 'readonly') {
        $("input[name = title]").attr('readonly', false); // title
        $("textarea").attr('readonly', false); // content
        $('modifyBtn').html("登録");
        $('h2').html("掲示物修正");
        return;
      }

      // 수정 상태면 , 수정된 내용을 서버로 전송
      form.attr("action", "<c:url value='/board/modify'/>");
      form.attr("method", "post");
      form.submit();
      <%--location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}";--%>
    });

    $('#removeBtn').on("click",function (){
      if(!confirm("本当に削除しますか？")) return;
      let form = $('#form');
      form.attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
      form.attr("method", "post");
      form.submit();
    });

    $('#writeBtn').on("click",function (){
      let form = $('#form');
      form.attr("action", "<c:url value='/board/write'/>");
      form.attr("method", "post");
      form.submit();
    });
  });
</script>
</body>
</html>