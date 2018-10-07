package cn.scut.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.junit.Test;

public class TestFun {
	@Test
	public void fun() throws IOException {
//		String jsonString="{\"name\":\"Jack\"}";
//		JSONObject jsonObject=JSONObject.fromObject(jsonString);
//		jsonObject.put("age", "12");
//		System.out.println(jsonObject);
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("China1.txt");
		InputStreamReader inReader=new InputStreamReader(in,"UTF-8");
		BufferedReader bd=new BufferedReader(inReader);
		String line="";
		StringBuilder sb=new StringBuilder();
		while((line=bd.readLine())!=null){
//			System.out.println(line);
			sb.append(line);
			
		}
		
		JSONObject jsonObject=JSONObject.fromObject(sb.toString());
		Iterator<String> keys=jsonObject.keys();
		StringBuilder sbd=new StringBuilder("{");
		while (keys.hasNext()) {
			JSONObject json=jsonObject.getJSONObject(keys.next());
			System.out.println(json);
			sbd.append("\"name\":"+"\""+json.getString("name")+"\""+",");
		}
		String s=sbd.substring(0,sbd.length()-1);
		s=s+"}";
		System.out.println(s);
//		System.out.println(jsonObject);
	}
	
}
