package cn.scut.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class CityServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.setContentType("text/xml;charset=utf-8");//注意这里把响应类型改为XML
		response.setContentType("text/html;charset=utf-8");//注意这里把响应类型改为HTML
		PrintWriter out=response.getWriter();
		// 解析XML文件
		InputStream in = this.getClass().getResourceAsStream("/China.xml");

		// OutputStream outputStream= new FileOutputStream("haha.txt");
		//
		// outputStream.write(in.read());
		SAXReader saxReader = null;
		StringBuilder sb = new StringBuilder("");
		Document doc = null;
//		一、用xml数据格式响应
//		try {
//			saxReader = new SAXReader();
//			doc = saxReader.read(in);
//			String pname = request.getParameter("pname");
//			// String nameString=doc.getRootElement().getName();
//			Element ele = (Element)doc.selectSingleNode("//province[@name='" + pname
//					+ "']");//获取的是元素
//			String xmlString=ele.asXML();//将xml转换成字符串
//			out.print(xmlString);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
		
//		二、用text格式响应
		try {
			saxReader = new SAXReader();
			doc = saxReader.read(in);
			String pname = request.getParameter("pname");
			// String nameString=doc.getRootElement().getName();
			Element ele = (Element)doc.selectSingleNode("//province[@name='" + pname
					+ "']");//获取的是元素
//			String nameString=ele.getName();
//			获取该节点的所有子节点"item""//province[@name='" + pname
//			+ "']"都属于xpath,有它特殊的定义，不用逐步解析效率高
			List<Node> arrList=ele.selectNodes("item");//获取的是元素
			Iterator<Node> it = arrList.iterator();
			while (it.hasNext()) {
				String s = ((Element) it.next()).getText();
				sb = sb.append(s + ",");
			}

			String p = sb.substring(0, sb.length() - 1);
			
			out.print(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		三、还可以用Json格式响应
		
	}

}
