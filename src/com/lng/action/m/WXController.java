package com.lng.action.m;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseMController;
import com.lng.model.biz.RequestOrder;
import com.lng.model.biz.partner.Customer;
import com.lng.service.biz.order.ArticleService;
import com.lng.service.biz.order.partner.CustomerService;
import com.lng.util.Const;
import com.lng.util.OkHttpClientUtil;
import com.lng.util.Sign;
import com.lng.util.Util;
import com.lng.util.wx.PayInfo;
import com.lng.util.wx.WXPay;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;

/**
 * 微信
 * 1 第一步：用户同意授权，获取code

2 第二步：通过code换取网页授权access_token

3 第三步：刷新access_token（如果需要）

4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 */
@Controller
@RequestMapping("/wx")
public class WXController extends BaseMController{

	private static final String DIR = "wx/";
	private static final Logger logger = LoggerFactory.getLogger(WXController.class);
	
	private static final String APPID = "wx707c260de2cc5a8c";
	private static final String APP_SECRET= "a36cdf8c50904732756f14e82c43cc5e";
	private static final String KEY= "Thelongestjourneybeginswiththefi";
	private static final String MERCHANT =  "1503102301"; //商户号
	private static final String URI = "http://www.yaomaiqi.com";//118.126.92.54
	private static final String REDIRECT_URI =  URI+"/wx/getCode.do";
	private static final String NOTICE_URL =  URI+"/wx/noticeUrl.do";
	
	@Resource
	private ArticleService articleService;
	
	
	@Resource
	private CustomerService customerService;
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @category 第一步：用户同意授权，获取code
	 */
	@RequestMapping("/requestCode.do")
	public String requestCode(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("我是requestCode");
		
		StringBuffer url = new StringBuffer();
		url.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		url.append(APPID);
		url.append("&redirect_uri=");
		url.append(URLEncoder.encode(REDIRECT_URI,"UTF-8"));
		url.append("&response_type=code&scope=");
		
		String state = request.getParameter("state");
		if("senior".equals(state)) {//弹出授权页面，获取更多信息
			url.append("snsapi_userinfo");
		}else {//不弹出授权页面
			url.append("snsapi_base");
		}
		url.append("&state=");
		url.append(state);
		url.append("#wechat_redirect");
		logger.info(url.toString());
//		request.getRequestDispatcher(url.toString()).forward(request, response);
//		response.sendRedirect(url.toString());
		 return "redirect:"+url.toString();
	}
	
	
	/**
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ServletException 
	 * @category 第二步：通过code换取网页授权access_token、第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 */
	@RequestMapping("/getCode.do")
	public String getCode(HttpServletRequest request) throws Exception {
		logger.info("我是getCode");
		logger.info("request.getRequestURL():"+request.getRequestURL());
		logger.info("request.getQueryString():"+request.getQueryString()); 
		
		String code = request.getParameter("code");
		if(StringUtils.isBlank(code)) {
			throw new Exception("code为空");
		}
		logger.info("code:"+code);
		
		String state = request.getParameter("state");
		if("senior".equals(state)) {//弹出授权页面，获取更多信息
			
			StringBuffer url = new StringBuffer();
			url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
			url.append(APPID);
			url.append("&secret=");
			url.append(APP_SECRET);
			url.append("&code=");
			url.append(code);
			url.append("&grant_type=authorization_code");
			logger.info("请求url:"+url);
			
			String returnStr = OkHttpClientUtil.httpGet(url.toString());
			logger.info("returnStr:"+returnStr);
			
			
			JSONObject jsonResult = JSONObject.fromObject(returnStr);
			logger.info("jsonResult:"+jsonResult);

			Integer errcode = (Integer)jsonResult.get("errcode");
			if(errcode != null) {
				logger.info("jsonResult:"+errcode);
				throw new Exception("请求出现错误，jsonResult："+jsonResult);
			}
			
			//第四步：拉取用户信息(需scope为 snsapi_userinfo)
			
			String access_token = (String)jsonResult.get("access_token");//特有access_token
//			request.getServletContext().setAttribute("access_token",access_token);//存到全局（未关注公众号）
			
			String openid = (String)jsonResult.get("openid");
			
			request.setAttribute("openid", openid);
			
			StringBuffer userinfoUrl = new StringBuffer();
			userinfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
			userinfoUrl.append(access_token);
			userinfoUrl.append("&openid=");
			userinfoUrl.append(openid);
			userinfoUrl.append("&lang=zh_CN");
			logger.info("请求userinfoUrl:"+userinfoUrl);
			
			String userinfo = OkHttpClientUtil.httpGet(userinfoUrl.toString());
			logger.info("userinfo:"+userinfo);
			JSONObject userinfoJSON = JSONObject.fromObject(userinfo);
			logger.info("userinfoJSON:"+userinfoJSON);
			
			errcode = (Integer)userinfoJSON.get("errcode");
			if(errcode != null) {
				throw new Exception("拉取用户信息出现错误，"+userinfoJSON);
			}
			
			request.setAttribute("openid",userinfoJSON.get("openid"));
			request.setAttribute("nickname",(String)userinfoJSON.get("nickname"));
			request.setAttribute("sex",userinfoJSON.get("sex"));
			request.setAttribute("country",userinfoJSON.get("country"));
			request.setAttribute("province",userinfoJSON.get("province"));
			request.setAttribute("city",userinfoJSON.get("city"));
			request.setAttribute("headimgurl",userinfoJSON.get("headimgurl"));
			
			logger.info("nickname:"+(String)userinfoJSON.get("nickname"));
			
		}else {//不弹出授权页面
			String openid = request.getParameter("openid");
			request.setAttribute("openid",openid );
			logger.info("不弹出授权页面，openid:"+openid);
		}
		
		/**
		 * 微信js调用扫一扫
		 */
		
		request.setAttribute("APPID",APPID);
		
		//开始签名 第一步应该从全局获取ticket，如果存在不在请求
		//1、获取accessToken
	/*	String accessToken = (String)request.getServletContext().getAttribute("access_token");
		if(StringUtils.isBlank(accessToken)) {
			//应该从新获取
		}*/
		
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
		url.append(APPID);
		url.append("&secret=");
		url.append(APP_SECRET);
		logger.info("请求url:"+url);
		
		String returnStr = OkHttpClientUtil.httpGet(url.toString());
		logger.info("returnStr:"+returnStr);
		
		JSONObject jsonResult = JSONObject.fromObject(returnStr);
		logger.info("jsonResult:"+jsonResult);
		
		String accessToken = (String)jsonResult.get("access_token");
		request.getServletContext().setAttribute("accessToken",accessToken);//存到全局
		
		//2、获取jsapi_ticket
		url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=");
		url.append(accessToken);
		url.append("&type=jsapi");
		
		returnStr = OkHttpClientUtil.httpGet(url.toString());
		logger.info("returnStr:"+returnStr);
		
		jsonResult = JSONObject.fromObject(returnStr);
		logger.info("jsonResult:"+jsonResult);
		
		String ticket = (String)jsonResult.get("ticket");
		request.getServletContext().setAttribute("ticket",ticket);//ticket保存全局缓存
		
		//3、签名生成
		String currentUrl = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		
		Map<String, String> ret = Sign.sign(ticket, currentUrl.split("#")[0]);
		for (Map.Entry entry : ret.entrySet()) {
			logger.info(entry.getKey() + ", " + entry.getValue());
			request.setAttribute((String)entry.getKey(),entry.getValue());
		}
		
		
		/**
		 * 微信支付
		 */
		logger.info("微信支付开始");
		String prepay_Id =downOrder(request);
		logger.info("prepay_Id："+prepay_Id);
		request.setAttribute("wxPay",getWxPayInfo(prepay_Id));
		
		return DIR+"user";
	}
	/**
     * 获取页面上weixin支付JS所需的参数
     * @param map
     * @return
	 * @throws Exception 
     */
    private WXPay getWxPayInfo(String prepay_id) throws Exception {
        String nonce = Sign.create_nonce_str().replace("-", "");
        String timeStamp = Sign.create_timestamp();
        //再算签名
        String newPrepay_id = "prepay_id="+prepay_id;
        String args = "appId="+APPID
                      +"&nonceStr="+nonce
                      +"&package="+newPrepay_id
                      +"&signType=MD5"
                      +"&timeStamp="+timeStamp
                      +"&key="+KEY;
        logger.info("args："+args); 
        
        String paySign = Util.MD5(args);
        WXPay wxPay = new WXPay();
        wxPay.setNonce_str(nonce);
        wxPay.setPaySign(paySign);
        wxPay.setPrepay_id(newPrepay_id);
        wxPay.setTimeStamp(timeStamp);
        
        logger.info("wxPay："+wxPay); 
        return wxPay;
    }
	
	/**
	 * 微信支付：第一步 调用统一下单接口
	 * 文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_4
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/downOrder.do")
	public String downOrder(HttpServletRequest request) throws Exception {
		
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		String openid = (String)request.getAttribute("openid");
		PayInfo payInfo = this.createPayInfo(openid);
		logger.info("payInfo"+payInfo);
		payInfo.setSign(this.getSign(payInfo));
		String xml = this.payInfoToXML(payInfo);
		
		logger.info("url:"+url);
		logger.info("xml:"+xml);
		String returnStr = OkHttpClientUtil.httpPostXml(url, xml);	
		logger.info("returnStr:"+returnStr);
		Map<String, String> map = this.parseXml(returnStr);
		
		String returnCode = map.get("return_code");//通信标识，非交易标识
		 if("SUCCESS".equals(returnCode)) {
			  String resultCode = map.get("result_code");
		     if(!"SUCCESS".equals(resultCode)) {
		    	 return "统一下单错误！："+map.get("err_code_des"); 
		     }
		 }else{
			 return "统一下单错误！";
		 }
		        
		String prepay_Id = map.get("prepay_id");
		return prepay_Id;
	}

	/**
	 *
	 *保存来自微信的订单
	 * 
	 */
	@RequestMapping("/saveRequestOrder.do")
	public String saveRequestOrder(HttpServletRequest request) {
		
		//var params = "orderInfor=" + orderInfor + "&gasCardNo="+gasCardNo+"&customerName=" + customerName + "&customerTel="
		//		 + customerTel + "&customerAddress="+customerAddress+"&openid=" + openId;
		
		
		String orderInfor	 	= request.getParameter("orderInfor");
		String gasCardNo	 	= request.getParameter("gasCardNo");
		String customerName	 	= request.getParameter("customerName");
		String customerTel	 	= request.getParameter("customerTel");
		String customerAddress	= request.getParameter("customerAddress");
		String openId			= request.getParameter("openid");
		String acceptStation	= request.getParameter("acceptStation");//接单站点
		String expectArriveTime	= request.getParameter("expectArriveTime");//希望到到时间
		String note				= request.getParameter("note");
		
		
		String[] orderInfors = orderInfor.split("-");

		int[] qtys 				= {0,0,0};
		int[] prices 			= {0,0,0};
		double[] amounts 		= {0.00, 0.00, 0.00};
		String[] bottleTypes	= new String[]{"5KG", "15KG", "50KG"};

		for(int i = 0; i < orderInfors.length; i++) {
			String oneStr = orderInfors[i];
			
			String[] oneOderInfor = oneStr.split("_");
			if("5KG".equalsIgnoreCase(oneOderInfor[0])) {
				
				prices[0]	= Integer.valueOf(oneOderInfor[1]);	
				qtys[0]		= Integer.valueOf(oneOderInfor[2]);
				amounts[0]	= Double.valueOf(oneOderInfor[3]).doubleValue();
				
			} else if("15KG".equalsIgnoreCase(oneOderInfor[0])){
				
				prices[1]	= Integer.valueOf(oneOderInfor[1]);		
				qtys[1]		= Integer.valueOf(oneOderInfor[2]);
				amounts[1]	= Double.valueOf(oneOderInfor[3]).doubleValue();
				
			} else if("50KG".equalsIgnoreCase(oneOderInfor[0])){
				prices[2]	= Integer.valueOf(oneOderInfor[1]);	
				qtys[2]		= Integer.valueOf(oneOderInfor[2]);
				amounts[2]	= Double.valueOf(oneOderInfor[3]).doubleValue();
			}
		}		
		
		Integer belongToParty = 1;//所属公司Id,应该要从微信选择公司上来
		Integer orderCreateParty = 4;//创建人,目前默认为系统管理员
		
		List orderList = new ArrayList();
		
		for(int i = 0; i < qtys.length; i++) {
			
			if(qtys[i] > 0) {
				
				RequestOrder o = new RequestOrder();				
				
				//o.setCustomerId(customerId);//客户Id
				
				o.setCustomerName(customerName);//客户名称
				
				o.setGasCardNo(gasCardNo);//客户用气证号
				
				o.setCustomerAddress(customerAddress);//客户地址
				
				o.setCustomerTel(customerTel);//客户电话
				
				o.setBottleType(bottleTypes[i]);//瓶型
				
				o.setPrice(new BigDecimal(prices[i]));//单价
				
				o.setQuantity(qtys[i]);//瓶数
				
				o.setExpectArriveTime(expectArriveTime);//希望到达时间
				
				o.setAcceptStation(acceptStation);//接单站点
				
				//private String acceptPerson;//接单人
				
				//private String acceptPersonTel;//接单人电话
				
				//private String bizType;//业务类型，默认是销售，可能还有调拨
				
				o.setMoney(new BigDecimal(amounts[i]));//金额	
				
				o.setOrderSource("WECHAT");//来源,包括微信WECHAT,呼叫中心CALLCENTER,手工录入等
				
				o.setOrderSourceId(openId);//来源Id,微信就是openId,呼叫中心也可以放个派单员之类的
				
				//private String status;//状态
				o.setStatus(Const.ORDER_STATE_WAIT_HANDLE);//待受理(微信过来)
				
				o.setNote(note);//备注
								
				//公司Id
				o.setBelgon2PartyId(belongToParty);
				//创建时间
				o.setOrderCreateTime(Util.dateToStr2(new Date()));
				//创建人,目前默认为系统管理员
				o.setOrderCreateParty(orderCreateParty);
						
//				requestOrderService.save(o);
				
				orderList.add(o);
				
			}
		}
		
		request.setAttribute("orderlist", orderList);
		
		return DIR + "customer/customerOrderList";
	}
	
	/**
	 *
	 * 通过微信OpenId查询订单,先通过openId查找到客户Id<br>
	 * 也有可能没有查到,如果存在就用客户id和openId共同来查找<br>
	 *
	 */
	@RequestMapping("/listRequestOrder.do")
	public String queryRequestOrder(HttpServletRequest request) {
		
		String openId			= request.getParameter("openid");		
		List customerList		= customerService.getCustomerListByOpenId(openId);		
		Integer[] customerIds	= new Integer[customerList.size()];		
		
		for(int i = 0,j = customerList.size(); i < j; i++) {			
			Customer c 		= (Customer)customerList.get(i);
			customerIds[i]  = c.getId();
		}
		
//		List<RequestOrder> list =  this.requestOrderService.getListByCustomerIdsOrOpenId(customerIds , openId);
//		
//		request.setAttribute("orderlist", list);
		
		return DIR + "customer/customerOrderList";
	}
	
	
	/**
	 * 微信支付：调用统一下单接口后，微信通知回调地址
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/noticeUrl.do")
	public String noticeUrl(HttpServletRequest request) throws Exception {
		
		logger.info("我是noticeUrl");
		logger.info("request.getRequestURL():"+request.getRequestURL());
		logger.info("request.getQueryString():"+request.getQueryString()); 
		
		return "";
	}
	
	private PayInfo createPayInfo(String openId) {

		PayInfo payInfo = new PayInfo();
		payInfo.setAppid(APPID);
		payInfo.setDevice_info("WEB");
		payInfo.setMch_id(MERCHANT);
		payInfo.setNonce_str(Util.getUUID());
//		payInfo.setNonce_str("e0e73658461545e5938dbf6317f44cca");
		payInfo.setBody("xuanbody");
		payInfo.setAttach("payTest");// 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
		payInfo.setOut_trade_no(Util.getUUID());// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*
													// 且在同一个商户号下唯一。
		payInfo.setTotal_fee(1);// 订单总金额，单位为分
		payInfo.setSpbill_create_ip("123.12.12.123");// APP和网页支付提交用户端ip
		payInfo.setNotify_url(NOTICE_URL);
		payInfo.setTrade_type("JSAPI");
		payInfo.setOpenid(openId);
		return payInfo;
	}
	 
	 /**
	     * 获取签名
	     * @param payInfo
	     * @return
	     * @throws Exception
	     */
	    public String getSign(PayInfo payInfo) throws Exception {
	        String signTemp = "appid="+payInfo.getAppid()
	                 +"&attach="+payInfo.getAttach()
	                 +"&body="+payInfo.getBody()
	                 +"&device_info="+payInfo.getDevice_info()
	                 +"&mch_id="+payInfo.getMch_id()
	                 +"&nonce_str="+payInfo.getNonce_str()
	                 +"&notify_url="+payInfo.getNotify_url()
	                 +"&openid="+payInfo.getOpenid()
	                 +"&out_trade_no="+payInfo.getOut_trade_no()
	                 +"&spbill_create_ip="+payInfo.getSpbill_create_ip()
	                 +"&total_fee="+payInfo.getTotal_fee()
	                 +"&trade_type="+payInfo.getTrade_type()
	                 +"&key="+KEY; //这个key注意
	       System.out.println("signTemp:"+signTemp); 
	       return Util.MD5(signTemp).toUpperCase();
	    }
	    
	    
	    /**
	     * 扩展xstream使其支持CDATA
	     */
	    private static XStream xstream = new XStream(new XppDriver() {
	        public HierarchicalStreamWriter createWriter(Writer out) {
	            return new PrettyPrintWriter(out) {
	                //增加CDATA标记
	                boolean cdata = true;

	                @SuppressWarnings("rawtypes")
	                public void startNode(String name, Class clazz) {
	                    super.startNode(name, clazz);
	                }
	                @Override
                    public String encodeNode(String name) {
                        return name;
                    }
	                protected void writeText(QuickWriter writer, String text) {
	                    if (cdata) {
	                        writer.write("<![CDATA[");
	                        writer.write(text);
	                        writer.write("]]>");
	                    } else {
	                        writer.write(text);
	                    }
	                }
	            };
	        }
	    });
	    
	    public static String payInfoToXML(PayInfo pi) {
	        xstream.alias("xml", pi.getClass());
	        return xstream.toXML(pi);
	    }
	    
	    public static Map<String, String> parseXml(String xml) throws Exception {
	        Map<String, String> map = new HashMap<String, String>();

	        Document document = DocumentHelper.parseText(xml);
	        Element root = document.getRootElement();
	        List<Element> elementList = root.elements();

	        for (Element e : elementList)
	            map.put(e.getName(), e.getText());
	        return map;
	    }
	    
	    public static void main(String[] args) throws Exception {
	    	
	    	WXController wxController = new WXController();
	    	PayInfo payInfo = wxController.createPayInfo("oW2s4005yfhPKHBWd8W0lcVKyAhI");
			System.out.println("payInfo"+payInfo);
			payInfo.setSign(wxController.getSign(payInfo));
			String xml = wxController.payInfoToXML(payInfo);
			
			System.out.println("xml:"+xml);
		}
}
