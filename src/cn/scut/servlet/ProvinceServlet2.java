package cn.scut.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.*;

public class ProvinceServlet2 extends HttpServlet {

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

		// 解析json文件
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("China1.txt");
		InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
		BufferedReader bd = new BufferedReader(inReader);
		PrintWriter out = response.getWriter();
		String line = "";
		StringBuilder sb = new StringBuilder();
		while ((line = bd.readLine()) != null) {
			// System.out.println(line);
			sb.append(line);

		}

		JSONObject jsonObject = JSONObject.fromObject(sb.toString());
		Iterator<String> keys = jsonObject.keys();
//		将所需要的数据包装成json字符串的格式
		StringBuilder sbd = new StringBuilder("{");
		int i = 1;
		while (keys.hasNext()) {

			JSONObject json = jsonObject.getJSONObject(keys.next());
			System.out.println(json);
			sbd.append("\"name" + new Integer(i).toString() + "\"" + ":" + "\""
					+ json.getString("name") + "\"" + ",");
			i++;
		}
		String s = sbd.substring(0, sbd.length() - 1);
		s = s + "}";
		System.out.println(s);
		out.print(s);

	}

}
