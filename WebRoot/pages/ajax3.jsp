<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>模拟注册用户名提示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	//JS实现异步交互
		function createXMLHttpRequest(){
			try{
				return new XMLHttpRequest();//大部分浏览器
			}catch(e){
				try{
					return new ActiveXObject("Msxml2.XMLHTTP");//IE6.0
				}catch(e){
					try{
						return new ActiveXObject("Microsoft.XMLHTTP");//IE5.5及以前
					}catch(e){
						alert("请问您使用的是什么浏览器");
						throw e;
					}
				}
				
			}
		}

		window.onload=function(){
			var tet=document.getElementById("i1")
			tet.onblur=function(){    //给文本框添加失去焦点事件监听器
				/* document.getElementById("2").innerHTML="hello"; */
			    //四步骤
				//第一步：得到XMLHttpRequest对象
				var httpRequest=createXMLHttpRequest();
				//第二部：打开连接
				
				httpRequest.open("POST", "/ajax/servlet/PrintServlet?t="+Math.random(), true);
				//加一个Math.random()防止浏览器缓存而不能多次访问服务器，open()的URL参数应该是一个客户端跳转必须是客户端路径（要手动添加项目名）
				/***添加请求头Content-Type,表示数据提交的方式*********/
				httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
				//第三步：发送请求
				//var s="username=zhangsan&password=123123";
				httpRequest.send("username="+tet.value);
				
				//第四步：对HttpRequest对象添加onreadystatechange事件监听器
				httpRequest.onreadystatechange=function(){
					if(httpRequest.status==200&&httpRequest.readyState==4){
						var text = httpRequest.responseText;
						document.getElementById("i4").innerHTML=text;
					}
				} 
			}

		}
		
	</script>
  </head>
  
  <body>
   	<form action="" method="post">
   		Username:<input type="text" name="username" id="i1"/><span id ="i4"></span><br>
   		Password:<input type="password" name="password" id="i2"/><br>
   		<input type="button" name="btn" value="注册" id="i3"/>
   	</form>
  </body>
</html>
