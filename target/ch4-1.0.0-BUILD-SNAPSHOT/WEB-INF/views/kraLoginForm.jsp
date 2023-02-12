<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン</title>
    <link rel="stylesheet" href="/resources/css/accountCss.css">
</head>
<body>
<div class="login-page">
    <div class="logo">
        <h1>
            <a href="/Users/choijongwon/Desktop/SIC/SIC/sicHTML.html"><img src="/resources/img/logo%20v2.png" width="240" height="47"></a>
        </h1>
    </div>

    <div class="item-text">
        <form action="<c:url value='/login/login'/>" method="post" onsubmit="return formCheck(this);">
            <div class="text-all">
                <p>
                    <label for="userID" class="id">ユーザID</label><br>
                    <input id="userID" type="text" name="id">
                </p>

                <p>
                    <label for="password">パスワード</label><br>
                    <input id="password" type="password" name="pwd">
                </p>

            </div>


            <p>
                <button class="account-box">ログイン</button>
            </p>

            <script>
                function formCheck(frm) {
                    let msg ='';

                    if(frm.id.value.length==0) {
                        setMessage('id를 입력해주세요.', frm.id);
                        return false;
                    }

                    if(frm.pwd.value.length==0) {
                        setMessage('password를 입력해주세요.', frm.pwd);
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
    </div>
</div>
</body>
</html>