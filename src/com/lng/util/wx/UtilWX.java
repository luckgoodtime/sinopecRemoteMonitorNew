package com.lng.util.wx;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.lng.util.OkHttpClientUtil;
import com.lng.util.Util;

public class UtilWX {

	private static final String APPID = "wx707c260de2cc5a8c";//appId
	private static final String APP_SECRET = "a36cdf8c50904732756f14e82c43cc5e";//app密码
	private static final String KEY = "Thelongestjourneybeginswiththefi";
	private static final String MERCHANT = "1503102301"; // 商户号
	private static final String URI = "http://www.yaomaiqi.com";// 118.126.92.54

	private static final Logger logger = LoggerFactory.getLogger(UtilWX.class);
	
	/**
	 *  跳转指定地址，获取用户授权
	 * @param returnUrl 授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getAuthorizeUrl(String returnUrl,String state)
			throws UnsupportedEncodingException {

		StringBuffer url = new StringBuffer();

		url.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
				.append(APPID).append("&redirect_uri=")
				.append(URLEncoder.encode(URI +returnUrl, "UTF-8"))
				.append("&response_type=code&scope=snsapi_userinfo&state=")
				.append(state).append("#wechat_redirect");

		return url.toString();
	}

	
	/**
	 * 通过code换取网页授权access_token 
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getAccessTokenUrl(String code)
			throws UnsupportedEncodingException {

		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
		url.append(APPID);
		url.append("&secret=");
		url.append(APP_SECRET);
		url.append("&code=");
		url.append(code);
		url.append("&grant_type=authorization_code");

		return url.toString();
	}
	
	/**
	 *  拉取用户信息Url
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getUserInfoAccessTokenUrl(String accessToken,String openid)
			throws UnsupportedEncodingException {

		StringBuffer userinfoUrl = new StringBuffer();
		
		userinfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
		userinfoUrl.append(accessToken);
		userinfoUrl.append("&openid=");
		userinfoUrl.append(openid);
		userinfoUrl.append("&lang=zh_CN");

		return userinfoUrl.toString();
	}
	
	public static String generateSignature(final Map<String, String> data,
			String key) throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append("=").append(data.get(k).trim()).append("&");
		}
		sb.append("key=").append(key);
		return Util.MD5(sb.toString()).toUpperCase();

	}
	
	public static WXUser getWxUser(HttpServletRequest request) throws Exception {
		
		String code = request.getParameter("code");//微信回调返回的code
		logger.info("code:"+code + " state:"+request.getParameter("state"));
		
		//1、 通过code换取网页授权access_token
		String accessTokenUrl = UtilWX.getAccessTokenUrl(code);
		
		String returnStr = OkHttpClientUtil.httpGet(accessTokenUrl);
		logger.info("returnStr:"+returnStr);
		
		JSONObject jsonResult = JSONObject.fromObject(returnStr);
//		logger.info("jsonResult:"+jsonResult);

		Integer errcode = (Integer)jsonResult.get("errcode");
		if(errcode != null) {
			if(!errcode.equals(40163)) {//TODO 临时忽略 errcode":40163,"errmsg":"code been used
				logger.info("jsonResult:"+errcode+" "+jsonResult);
				throw new Exception("请求出现错误，jsonResult："+jsonResult);
			}
		}
			
		//2、拉取用户信息
		String access_token = (String)jsonResult.get("access_token");//特有access_token
		String openid = (String)jsonResult.get("openid");
		
		String userinfoUrl = UtilWX.getUserInfoAccessTokenUrl(access_token, openid);
		logger.info("userinfoUrl:"+userinfoUrl);
		
		String userinfo = OkHttpClientUtil.httpGet(userinfoUrl);
		logger.info("userinfo:"+userinfo);
		
		JSONObject userinfoJSON = JSONObject.fromObject(userinfo);
//		logger.info("userinfoJSON:"+userinfoJSON);
		
		errcode = (Integer)userinfoJSON.get("errcode");
		if(errcode != null) {
			throw new Exception("拉取用户信息出现错误，"+userinfoJSON);
		}
		
		WXUser wxUser = new WXUser();
		wxUser.setOpenid((String)userinfoJSON.get("openid"));
		wxUser.setNickname((String)userinfoJSON.get("nickname"));
		wxUser.setSex((Integer)userinfoJSON.get("sex"));
		wxUser.setCountry((String)userinfoJSON.get("country"));
		wxUser.setProvince((String)userinfoJSON.get("province"));
		wxUser.setCity((String)userinfoJSON.get("city"));
		wxUser.setHeadimgurl((String)userinfoJSON.get("headimgurl"));
		
		//TODO 可以去掉以下代码
		request.setAttribute("openid",userinfoJSON.get("openid"));
		request.setAttribute("nickname",(String)userinfoJSON.get("nickname"));
		request.setAttribute("sex",userinfoJSON.get("sex"));
		request.setAttribute("country",userinfoJSON.get("country"));
		request.setAttribute("province",userinfoJSON.get("province"));
		request.setAttribute("city",userinfoJSON.get("city"));
		request.setAttribute("headimgurl",userinfoJSON.get("headimgurl"));
		
		logger.info("nickname:"+(String)userinfoJSON.get("nickname"));
		
		return wxUser;
	}
}
