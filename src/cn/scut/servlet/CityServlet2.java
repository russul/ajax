package cn.scut.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class CityServlet2
 */
@WebServlet("/CityServlet2")
public class CityServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CityServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 解析json文件
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("China1.txt");
		InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
		BufferedReader bd = new BufferedReader(inReader);
		PrintWriter out = response.getWriter();
		String province = request.getParameter("province");
		JSONObject jsonObject = reader(bd);
		Iterator<String> keys = jsonObject.keys();
		String cities = "";
		StringBuilder sd = new StringBuilder("{");
		while (keys.hasNext()) {

			JSONObject json = jsonObject.getJSONObject(keys.next());
			System.out.println(json);
			if (((String) json.get("name")).equalsIgnoreCase(province)) {
				JSONObject provinceJson = json.getJSONObject("child");
				Iterator<String> keycity = provinceJson.keys();
				//引入i是为了让返回的数据属性名不同
				int i = 1;
				while (keycity.hasNext()) {
					JSONObject cityJson = provinceJson.getJSONObject(keycity
							.next());
					sd.append("\"name" + new Integer(i).toString() + "\"" + ":"
							+ "\"" + cityJson.getString("name") + "\"" + ",");
					i++;
				}
				cities = sd.substring(0, sd.length() - 1);
				cities = cities + "}";
				break;
			}

		}

		System.out.println(cities);
		out.print(cities);

	}

	public JSONObject reader(BufferedReader bd) throws IOException {
		String line = "";
		StringBuilder sb = new StringBuilder();
		while ((line = bd.readLine()) != null) {
			// System.out.println(line);
			sb.append(line);

		}

		JSONObject jsonObject = JSONObject.fromObject(sb.toString());
		return jsonObject;
	}
	


}
