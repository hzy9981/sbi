package net.jeetech.service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class FaceTest {
	public static void main(String[] args) throws MalformedURLException {
		// File file = new File(args[1]);
		File file = new File("D:\\workspace\\tupu-java-sdk-master\\resource\\4.jpg");
		URL png = new URL(
				"https://d1.awsstatic-china.com/Startups/Jeff-Barr_Bio-Pic.1a460787ac8fc7cad6cc3c9f892281bce9126e28.png");
		// file = new File(png.getPath());
		byte[] buff = getBytesFromFile(file);
		String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
		HashMap<String, String> map = new HashMap<>();
		HashMap<String, byte[]> byteMap = new HashMap<>();
		map.put("faceset_token", "5d8b8a7f152405579f587e756bb53104");
		map.put("api_key", "MVQI33KGEqS_1QuQWOQoOYGC_Px23d4y");
		map.put("api_secret", "7NPStbEepWcEbpl_1X3w2N1Vu2T4PCuj");
		map.put("return_landmark", "1");
		map.put("return_attributes",
				"gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
		// byteMap.put("image_file", buff);
		map.put("image_url",
				"https://d1.awsstatic-china.com/Startups/Jeff-Barr_Bio-Pic.1a460787ac8fc7cad6cc3c9f892281bce9126e28.png");
		String str = null;
		try {
			byte[] bacd = post(url, map, byteMap);
			str = new String(bacd);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return str;

	}

	public String findByCamera(String args) {

		String url = "https://api-cn.faceplusplus.com/facepp/v3/search";
		HashMap<String, String> map = new HashMap<>();
		HashMap<String, byte[]> byteMap = new HashMap<>();
		map.put("faceset_token", "5d8b8a7f152405579f587e756bb53104");
		map.put("api_key", "MVQI33KGEqS_1QuQWOQoOYGC_Px23d4y");
		map.put("api_secret", "7NPStbEepWcEbpl_1X3w2N1Vu2T4PCuj");
		map.put("return_landmark", "1");
		map.put("return_attributes",
				"gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
		File file = null;			String URI = null;

		try {
			if (new URL(args) != null)
				URI = args;
			else
				file = new File(args);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (URI == null)
			byteMap.put("image_file", getBytesFromFile(file));
		else
			map.put("image_url", URI);
		String str = null;
		try {
			byte[] bacd = post(url, map, byteMap);
			str = new String(bacd);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public String detect(String args) {

		String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
		HashMap<String, String> map = new HashMap<>();
		HashMap<String, byte[]> byteMap = new HashMap<>();
		map.put("faceset_token", "5d8b8a7f152405579f587e756bb53104");
		map.put("api_key", "MVQI33KGEqS_1QuQWOQoOYGC_Px23d4y");
		map.put("api_secret", "7NPStbEepWcEbpl_1X3w2N1Vu2T4PCuj");
		map.put("return_landmark", "1");
		map.put("return_attributes",
				"gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
		// byteMap.put("image_file", buff);
		map.put("image_url",
				"https://d1.awsstatic-china.com/Startups/Jeff-Barr_Bio-Pic.1a460787ac8fc7cad6cc3c9f892281bce9126e28.png");
		File file = null;			String URI = null;

		try {
			if (new URL(args) != null)
				URI = args;
			else
				file = new File(args);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (URI == null)
			byteMap.put("image_file", getBytesFromFile(file));
		else
			map.put("image_url", URI);
		String str = null;
		try {
			byte[] bacd = post(url, map, byteMap);
			str = new String(bacd);
			url = "https://api-cn.faceplusplus.com/facepp/v3/faceset/addface";
			JSONObject parseObject = JSONObject.parseObject(str);
			JSONArray jsonArray = parseObject.getJSONArray("faces");
			JSONObject[] a = new JSONObject[] {};
			JSONObject[] b = jsonArray.toArray(a);
			String face_tokens = "";
			for (JSONObject jo : b)
				face_tokens += jo.getString("face_token") + ",";
			face_tokens = face_tokens.substring(0, face_tokens.length() - 1);
			map.put("face_tokens", face_tokens);
			parseObject.put("face_tokens", face_tokens);// str重新赋值，增加了face_tokens
			str = parseObject.toJSONString();
			byte[] bacd1 = post(url, map, byteMap);// 一张图片多个face关联一张图片，不能覆盖，所以头像管理功能相关图片必须使用数据库模糊查询
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
			str = null;
		}
		return str;
	}

	private final static int CONNECT_TIME_OUT = 30000;
	private final static int READ_OUT_TIME = 50000;
	private static String boundaryString = getBoundary();

	protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap)
			throws Exception {
		HttpURLConnection conne;
		URL url1 = new URL(url);
		conne = (HttpURLConnection) url1.openConnection();
		conne.setDoOutput(true);
		conne.setUseCaches(false);
		conne.setRequestMethod("POST");
		conne.setConnectTimeout(CONNECT_TIME_OUT);
		conne.setReadTimeout(READ_OUT_TIME);
		conne.setRequestProperty("accept", "*/*");
		conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
		conne.setRequestProperty("connection", "Keep-Alive");
		conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
		DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry) iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			obos.writeBytes("--" + boundaryString + "\r\n");
			obos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"\r\n");
			obos.writeBytes("\r\n");
			obos.writeBytes(value + "\r\n");
		}
		if (fileMap != null && fileMap.size() > 0) {
			Iterator fileIter = fileMap.entrySet().iterator();
			while (fileIter.hasNext()) {
				Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
				obos.writeBytes("--" + boundaryString + "\r\n");
				obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey() + "\"; filename=\""
						+ encode(" ") + "\"\r\n");
				obos.writeBytes("\r\n");
				obos.write(fileEntry.getValue());
				obos.writeBytes("\r\n");
			}
		}
		obos.writeBytes("--" + boundaryString + "--" + "\r\n");
		obos.writeBytes("\r\n");
		obos.flush();
		obos.close();
		InputStream ins = null;
		int code = conne.getResponseCode();
		try {
			if (code == 200) {
				ins = conne.getInputStream();
			} else {
				ins = conne.getErrorStream();
			}
		} catch (SSLException e) {
			e.printStackTrace();
			return new byte[0];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[4096];
		int len;
		while ((len = ins.read(buff)) != -1) {
			baos.write(buff, 0, len);
		}
		byte[] bytes = baos.toByteArray();
		ins.close();
		return bytes;
	}

	private static String getBoundary() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 32; ++i) {
			sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(
					random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
		}
		return sb.toString();
	}

	private static String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}

	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

}