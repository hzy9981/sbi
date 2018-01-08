/**
 * 
 */
package net.jeetech.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author yangguang
 *
 */
public class FaceTestTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link net.jeetech.service.FaceTest#detect(java.lang.String)}.
	 */
	@Test
	public final void testFindByCamera() {
		new FaceTest().findByCamera("https://d1.awsstatic-china.com/Startups/Jeff-Barr_Bio-Pic.1a460787ac8fc7cad6cc3c9f892281bce9126e28.png");
//		org.apache.jackson
		JSONObject JSON2 = JSONObject.parseObject("{\"faces\":[{\"face_token\": \"3ece619fdbac1e3fa87d122fe479f925\"},{\"face_token\":\"2\"}]}");
//		JSONArray jsonArray = JSON2.forEach(action);("face_token");
//		jsonArray.();
//		System.out.println(jsonArray);
		
		JSONArray JSON = JSONObject.parseArray("[{\"face_token\": \"3ece619fdbac1e3fa87d122fe479f925\"},{\"face_token\":\"2\"}]");
//		System.out.printf(JSON.forEach(arg0);.toString());

	}

}
