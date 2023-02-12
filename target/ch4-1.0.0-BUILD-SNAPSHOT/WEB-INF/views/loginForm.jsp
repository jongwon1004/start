<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
  <style>
    * { box-sizing:border-box; }
    a { text-decoration: none; }
    form {
      width:400px;
      height:500px;
      display : flex;
      flex-direction: column;
      align-items:center;
      position : absolute;
      top:50%;
      left:50%;
      transform: translate(-50%, -50%) ;
      border: 3px solid rgb(124,107,201);
      border-radius: 10px;
    }
    input[type='text'], input[type='password'] {
      width: 300px;
      height: 40px;
      border : 1px solid rgb(89,117,196);
      border-radius:5px;
      padding: 0 10px;
      margin-bottom: 10px;
    }
    button {
      background-color: rgb(124,107,201);
      color : white;
      width:300px;
      height:50px;
      font-size: 17px;
      border : none;
      border-radius: 5px;
      margin : 20px 0 30px 0;
    }
    #title {
      font-size : 50px;
      margin: 40px 0 30px 0;
    }
    #msg {
      height: 30px;
      text-align:center;
      font-size:16px;
      color:red;
      margin-bottom: 20px;
    }

    h1 {
      margin: 0px auto;
      padding-top: 40px;
      text-align: center;
    }
  </style>
</head>
<body>
<h1>
  <a href="<c:url value='/'/> ">
  <img src="./../resources/img/logo.png" width="340px" height="70px"/>
  </a>
</h1>
<form action="<c:url value='/login/login'/>" method="post" onsubmit="return formCheck(this);">
  <h3 id="title">Login</h3>
  <div id="msg">
    <c:if test="${not empty param.msg}">
      <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>
    </c:if>
  </div>
  <input type="text" name="id" value="${cookie.id.value}" placeholder="ID入力" autofocus>
  <input type="password" name="pwd" placeholder="パスワード">
  <input type="hidden" name="toURL" value="${param.toURL}">
  <button>ログイン</button>
  <div>
    <label><input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked"}>ID記憶</label> |
    <a href="">パスワード検索</a> |
    <a href="">新規登録</a>
  </div>
  <script>
    function formCheck(frm) {
      let msg ='';

      if(frm.id.value.length==0) {
        setMessage('idを入力してください。', frm.id);
        return false;
      }

      if(frm.pwd.value.length==0) {
        setMessage('パスワードを入力してください。', frm.pwd);
        return false;
      }
      return true;
    }

    function setMessage(msg, element){
      document.getElementById("msg").innerHTML = ` ${'${msg}'}`;

      if(element) {
        element.select();
      }
    }
  </script>
</form>
</body>
</html>