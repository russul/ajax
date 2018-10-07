<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>省市联动</title>
    <script type="text/javascript">
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

			
			    //四步骤
				//第一步：得到XMLHttpRequest对象
				var httpRequest=createXMLHttpRequest();
				//第二部：打开连接
				
				httpRequest.open("GET", "/ajax/servlet/ProvinceServlet?t="+Math.random(), true);
				//加一个Math.random()防止浏览器缓存而不能多次访问服务器，open()的URL参数应该是一个客户端跳转必须是客户端路径（要手动添加项目名）
				/***添加请求头Content-Type,表示数据提交的方式*********/
			/* 	httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded") */
				//第三步：发送请求
		/* 		var s="username=zhangsan&password=123123"; */
				httpRequest.send(null);
				
				//第四步：对HttpRequest对象添加onreadystatechange事件监听器
				httpRequest.onreadystatechange=function(){
					if(httpRequest.status==200&&httpRequest.readyState==4){
						var text = httpRequest.responseText;
						
						var arr=text.split(",");
						for(var i=0;i<arr.length;i++){
							var eleOption=document.createElement("option");
							//option的实际值，提交时的值<option value="">xxx</option>
							eleOption.value=arr[i];
							//option的显示值，即XXX
							var textOption=document.createTextNode(arr[i]);
							eleOption.appendChild(textOption);
							document.getElementById("p").appendChild(eleOption);
							
						}
					}
				 
			}
				//对省份select添加监听器，一旦他改变 就要像服务器发送得到城市信息并显示
				var prov=document.getElementById("p");
				
				prov.onchange=function(){

				    //四步骤
					//第一步：得到XMLHttpRequest对象
					var httpRequest=createXMLHttpRequest();
					//第二部：打开连接
					
					httpRequest.open("POST", "/ajax/servlet/CityServlet?t="+Math.random(), true);
					//加一个Math.random()防止浏览器缓存而不能多次访问服务器，open()的URL参数应该是一个客户端跳转必须是客户端路径（要手动添加项目名）
					/***添加请求头Content-Type,表示数据提交的方式*********/
					httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8") 
					//第三步：发送请求
			/* 		var s="username=zhangsan&password=123123"; */
					httpRequest.send("pname="+prov.value);
					
					//第四步：对HttpRequest对象添加onreadystatechange事件监听器
					httpRequest.onreadystatechange=function(){
						if(httpRequest.status==200&&httpRequest.readyState==4){
							var city=document.getElementById("c");
							//为了防止在省份切换时，城市在不断的累加，每次得到响应前先删除城市信息
							//获取city的所有子元素
							var options=city.getElementsByTagName("option");
							while(options.length>1){
								city.removeChild(options[1]);//每次删除1位置，这样就保留了请选择这项内容
							}
							//用xml响应--->用responseXML
							/* var doc = httpRequest.responseXML;
							var cityEleList=doc.getElementsByTagName("item");
							var cityName;
							for(var i=0;i<cityEleList.length;i++){
								
								cityName=cityEleList[i].textContent;
								var op=document.createElement("option");
								op.value=cityName;
								op.appendChild(document.createTextNode(cityName));
								city.appendChild(op);
							} */
							
							//用文本格式响应--》用responseText接收处理
							var text_city=httpRequest.responseText;
							var cityList=text_city.split(",");
							//遍历cityList，并且把这些城市信息都添加到select中
							for(var i=0;i<cityList.length;i++){
								//创建option元素
								var ele_city=document.createElement("option");
								//option的实际值为cityList[i]
								ele_city.value=cityList[i];
								//创建文本节点
								var text_node=document.createTextNode(cityList[i]);
								//将文本节点添加到option里
								ele_city.appendChild(text_node);
								//将option节点添加到select里
								city.appendChild(ele_city);
							}
							
						}
					 
				}
				}

		}
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
