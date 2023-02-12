<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
  <title>会員登録</title>

  <style>
    * { box-sizing:border-box; }

    .logo{
      height: 200px;

    }

    div .item-text {
      width:500px;
      height:530px;
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

    h1 {
      text-align: center;
      height: 45px;
      margin-top: 45px;
    }

    .item-text {
      margin: 0 auto;
      text-align: center;
    }

    body {
      background-color: rgba(252, 255, 255, 0.842);
    }

    .ookisa{
      width: 300px;
      height: 30px;
      border: 1.5px solid black;
    }

    .account-box {

      background-color: rgb(127, 139, 194);
      width: 406px;
      height: 40px;
      margin-top: 25px;
      font-size: 15px;
      font-weight: bold;
    }

    .sns-chk{
      display: flex;
      justify-content: center;
    }

    .test .sns-chk label{
      margin-right: 35px;
    }

    .msg {
      height: 30px;
      text-align:center;
      font-size:16px;
      color:red;
      margin-bottom: 20px;
    }

    i {
      margin-top: 28px;
    }


  </style>
  <title>Register</title>
</head>
<body>
<div>
  <div class="logo">
    <h1>
      <a href="<c:url value='/'/> ">
        <img src="./../resources/img/logo.png" width="340px" height="70px"/>
      </a>
    </h1>
  </div>

  <div class="item-text">
    <form action="<c:url value="/register/save"/>" method="post" onsubmit="return formCheck(this)">
<%--    <form:form modelAttribute="user">--%>
      <div id="msg" class="msg"><form:errors path="user.id"></form:errors></div>
      <div class="text-all">
        <p>
          <label for="userID" class="id"><span style="font-weight: bold;">ユーザID</span></label><br>
          <input id="userID" type="text" name="id" class="ookisa" placeholder="8 ~ 12桁の英語の大文字、小文字と数字の組み合わせ">
        </p>
        <p>
          <label for="password"><span style="font-weight: bold;">パスワード</span></label><br>
          <input id="password" type="password" name="pwd" class="ookisa" placeholder="8 ~ 12桁の英語の大文字、小文字と数字の組み合わせ">
        </p>
        <p>
          <label for="name" class="name"><span style="font-weight: bold;">名前</span></label><br>
          <input id="name" type="text" name="name" class="ookisa" placeholder="崔　鐘元">
        </p>
        <p>
          <label for="email"><span style="font-weight: bold;">メールアドレス</span></label><br>
          <input id="email" type="email" name="email" class="ookisa" placeholder="example@gmail.com">
        </p>
        <p>
          <label><span style="font-weight: bold;">生年月日</span></label><br>
          <input class="ookisa" type="text" name="birth" placeholder="2023-1-1">
        <div class="test">
          <div class="sns-chk">
            <label><input type="checkbox" name="sns" value="facebook"/>facebook</label>
            <label><input type="checkbox" name="sns" value="line"/>line</label>
            <label><input type="checkbox" name="sns" value="instagram"/>instagram</label>
          </div>
        </div>
        <input type="submit" value="新規登録" class="account-box"/>
        </p>
      </div>
      <a href="login.html">すでにアカウントお持ちの方（ログインページ）</a>
<%--  </form:form>--%>
    </form>
  </div>
  </div>
</div>
<script>
  function formCheck(frm) {
    let msg ='';
    if(frm.id.value.length<3) {
      setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
      return false;
    }
    return true;
  }
  function setMessage(msg, element){
    document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
    if(element) {
      element.select();
    }
  }
</script>
</body>
</html>
