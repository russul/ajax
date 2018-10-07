<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>JS动态交互效果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 原生JS是不能直接访问服务器的，所以只能显示客户端自己的数据而不能得到服务器的数据，这里模拟JS的动态交互功能（基于事件驱动） -->
	<!--给一个按钮，点击按钮，显示一段字符串 -->
	<script type="text/javascript">
		window.onload=function(){//在window对象上添加一个onload事件监视器，一旦加载，便执行这个函数
			var ele=document.getElementById("1");
			ele.onclick=function(){//在ele上添加onclick事件监听器
				document.getElementById("2").innerHTML="hello";
			}
		}
	</script>
  </head>
  
  <body>
    <p id="2"></p> 
    <button id="1" name="提交">点击这里</button>
  </body>
</html>
