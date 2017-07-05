package com.sjzl.york.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sjzl.york.util.ExceptionPrintUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//import java.io.PrintWriter;

/**
 * 
 * @author tianyh 
 * @Description:HTTP 请求
 */
@Component
public class ChuangLanSmsUtil {
	private final static Logger logger = Logger.getLogger(ChuangLanSmsUtil.class);


	@Value("${application.parameter.chuanglan.url}")
	private String smsSendurl;

	@Value("${application.parameter.chuanglan.account}")
	private String account;

	@Value("${application.parameter.chuanglan.password}")
	private String password;



	/**
	 * 
	 * @author tianyh 
	 * @Description 
	 * @param phone
	 * @param content
	 * @return String 
	 * @throws
	 */
	public void sendMessage(String phone,String content) {

		SmsSendRequest request = new SmsSendRequest(account,password,content,phone);
		String postContent = JSON.toJSONString(request);
		String res = "";
		URL url = null;
		try {
			url = new URL(smsSendurl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
			httpURLConnection.setReadTimeout(2000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			
//			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
//			printWriter.write(postContent);
//			printWriter.flush();

			httpURLConnection.connect();
			OutputStream os=httpURLConnection.getOutputStream();
			os.write(postContent.getBytes("UTF-8"));
			os.flush();
			
			StringBuilder sb = new StringBuilder();
			int httpRspCode = httpURLConnection.getResponseCode();
			if (httpRspCode == HttpURLConnection.HTTP_OK) {
				// 开始获取数据
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				SmsSendResponse response = JSONObject.toJavaObject((JSON) JSON.parse(sb.toString()),SmsSendResponse.class);
				if (!String.valueOf(0).equals(response.getCode())){
					logger.info("send short messge error :" + response.getErrorMsg());
				}
			}

		} catch (Exception e) {
			logger.info(ExceptionPrintUtil.printStackTrace(e));
		}
	}

}
