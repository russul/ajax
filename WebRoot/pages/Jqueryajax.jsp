<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Jqueryajax.jsp' starting page</title>
    <!-- 引入JQuery库文件  -->
	<script src="/ajax/js/jquery-3.3.1.min.js">
	</script>
	<script>
	/* 页面一旦加载就向服务器请求省份信息 */
		$(document).ready(function(){
			
			$.ajax({
				anysc:true,
				type:"GET",
				url:"/ajax/servlet/ProvinceServlet2?t="+Math.random(),
					/*success是请求成功的回调函数 result为服务器返回结果  */
				success:function(result){
					/* 将json字符串解析成json对象 */
					var provinces= eval("("+result+")");
					/* 遍历json对象 */
					for(var attr in provinces){
						/*  创建option元素 */
						var opt=$("<option></option>").html(provinces[attr]);
						
						$("#p").append(opt);
					}
				}
			});
			
			/* alert(p); */
			$("#p").change(function(){
				$("#c").empty();
				$("#c").append($("<option></option>").html("===请选择城市==="));
				$.ajax({
					anysc:true,
					type:"POST",
					data:"province="+p.value,
					url:"/ajax/CityServlet2?t="+Math.random(),
					success:function(data){
						
						/* 将json字符串解析成json对象 */
						var cities= eval("("+data+")");
						/* 遍历json对象 */
						for(var attr in cities){
							/*  创建option元素 */
							var opt=$("<option></option>").html(cities[attr]);
							
							$("#c").append(opt);
						}	
					}
				});
			});
		});
	
	
	</script>
  </head>
  
   <body>
    <h1>省市联动</h1>
    <select name="province" id="p">
    	<option>===请选择省份===</option>
    </select>  &nbsp&nbsp&nbsp
        
    <select name="city" id="c">
    	<option>===请选择城市===</option>
    </select>
  </body>
</html>
