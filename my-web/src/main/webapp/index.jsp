<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<meta http-equiv="Content-Type" content="text/html charset=utf-8">
<script>
    var websocket = null;
    var host = document.location.host;
    var username = "haha"; // 获得当前登录人员的userName
    // alert(username)
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        alert("支持中文吗")
        alert("浏览器支持Websocket")
        websocket = new WebSocket('ws://'+host+'/webSocket/'+username);
    } else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function() {
        alert("WebSocket连接发生错误")
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function() {
        alert("WebSocket连接成功")
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function(event) {
        alert("接收到消息的回调方法")
        alert("这是后台推送的消息："+event.data);
        websocket.close();
        alert("webSocket已关闭！")
    }

    //连接关闭的回调方法
    websocket.onclose = function() {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function() {
        closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }
</script>
<body>
<h2 id="message">Hello World!</h2>
</body>
</html>
