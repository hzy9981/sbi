package net.jeetech.service;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.baidu.aip.imagesearch.AipImageSearch;
@Service
public class BaiduTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "10602168";
    public static final String API_KEY = "XUUeiKaGD2yOceiDLGKlsWBl";
    public static final String SECRET_KEY = "OOgXhK0qiaa4uBDY5phzIMoQNnnGGGOM";

    public static void main(String[] args) {
        // 初始化一个AipImageSearch
        AipImageSearch client = new AipImageSearch(APP_ID, API_KEY, SECRET_KEY);

//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "C:\\Users\\yangguang\\git\\sbi\\baidu\\2.jpg";
        JSONObject res = client.similarSearch(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
        
    }
}