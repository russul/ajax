package cn.scut.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

public class ProvinceServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 解析XML文件
		InputStream in = this.getClass().getResourceAsStream("/China.xml");

		// OutputStream outputStream= new FileOutputStream("haha.txt");
		//
		// outputStream.write(in.read());
		SAXReader saxReader = null;
		StringBuilder sb = new StringBuilder("");
		Document doc=null;
		try {
			saxReader = new SAXReader();
			doc = saxReader.read(in);
//			String nameString=doc.getRootElement().getName();
			List<Node> arrList = doc.selectNodes("//province/@name");//获取的是属性

			Iterator<Node> it = arrList.iterator();
			while (it.hasNext()) {
				String s = ((Attribute) it.next()).getData().toString();
				sb = sb.append(s + ",");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		String p = sb.substring(0, sb.length() - 1);
		PrintWriter out = response.getWriter();
		out.print(p);
	}

}
