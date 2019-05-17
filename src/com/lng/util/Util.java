package com.lng.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sun.misc.BASE64Encoder;

public class Util {

	public static String dateToStr2(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	public static Date strtoDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String datetoStr(Date source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(source);
	}

	// 通用
	public static Date strtoDate(String source, String paramStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(paramStr);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 通用
	public static String datetoStr(Date source, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(source);

	}

	// 取得uuid，600d47c1-90da-488d-9c95-decc046c05a8
	public static String getUUID() {
		String uuidStr = UUID.randomUUID().toString();
		return uuidStr.replaceAll("-", "");
	}

	public static boolean checkEmailFormat(String email) {
		boolean tag = true;
		final String pattern1 = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	// 乘法
	public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
		BigDecimal result = v1.multiply(v2);
		return result.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	// 乘法
	public static BigDecimal multiply(BigDecimal v1, BigDecimal v2,
			int paramInt1) {
		BigDecimal result = v1.multiply(v2);
		return result.setScale(paramInt1, BigDecimal.ROUND_HALF_UP);
	}

	// 乘法
	public static BigDecimal multiply(BigDecimal v1, BigDecimal v2,
			int paramInt1, int paramInt2) {
		BigDecimal result = v1.multiply(v2);
		return result.setScale(paramInt1, paramInt2);
	}

	// 加法
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		return v1.add(v2);
		// return result.setScale(2,BigDecimal.ROUND_HALF_UP); //四舍五入 保留2位小数
	}

	/**
	 * md5加密成24位字符串
	 * 
	 * @param targetStr
	 * @return
	 */
	/*public static String md5(String targetStr) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			// 加密后的字符串
//			str = base64en.encode(md5.digest(targetStr.getBytes("utf-8")));
			 byte[] result = md5.digest(targetStr.getBytes("utf-8"));
			 StringBuffer buffer = new StringBuffer();
	            // 把每一个byte 做一个与运算 0xff;
	            for (byte b : result) {
	                // 与运算
	                int number = b & 0xff;// 加盐
	                String str = Integer.toHexString(number);
	                if (str.length() == 1) {
	                    buffer.append("0");
	                }
	                buffer.append(str);
	            }

	            // 标准的md5加密后的结果
	            return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}*/

	public static String md5(String targetStr) {
		String str = null;
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			str = base64en.encode(md5.digest(targetStr.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;

	}
	

	public static boolean isMobile() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Device device = DeviceUtils.getCurrentDevice(request);
		if (device!=null&&(device.isMobile()||device.isTablet())) {
			//移动端
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	
	/**
	 * 汉字转换位汉语拼音首字母，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * 汉字转换位汉语拼音，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	
	public static boolean isEmptyString(String s) {
		
		if(s == null || s.length() == 0 || "null".equalsIgnoreCase(s))
			return true;
		else
			return false;
		
	}
	
	
	 public static String MD5(String data) throws Exception {
	        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(data.getBytes("UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        for (byte item : array) {
	            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
	        }
	        return sb.toString().toUpperCase();
	    }
	 

/**
	 * 解析地址
	 * @author lin
	 * @param address
	 * @return
	 */
	public static List<Map<String,String>> addressResolution(String address){
		String regex="(?<province>[^省]+省|.+自治区)(?<city>[^市]+市|.+自治州)(?<county>[^县]+[市|县]|.+区)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
		Matcher m=Pattern.compile(regex).matcher(address);
		String province=null,city=null,county=null,town=null,village=null;
		List<Map<String,String>> table=new ArrayList<Map<String,String>>();
		Map<String,String> row=null;
		while(m.find()){
			row=new LinkedHashMap<String,String>();
			province=m.group("province");
			row.put("province", province==null?"":province.trim());
			city=m.group("city");
			row.put("city", city==null?"":city.trim());
			county=m.group("county");
			row.put("county", county==null?"":county.trim());
			town=m.group("town");
			row.put("town", town==null?"":town.trim());
			village=m.group("village");
			row.put("village", village==null?"":village.trim());
			table.add(row);
		}
		return table;
	}
	
	
	/** 
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式 
     */ 
    public static String digitUppercase(double n) {  
        String fraction[] = { "角", "分"};  
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};  
        String unit[][] = { { "元", "万", "亿"}, { "", "拾", "佰", "仟"}};  
           
        String head = n < 0 ? "负" : "";  
        n = Math.abs(n);  
           
        String s = "";  
        for (int i = 0; i < fraction.length; i++) {  
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");  
        }  
        if (s.length() < 1) {  
            s = "整";  
        }  
        int integerPart = (int) Math.floor(n);  
           
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {  
            String p = "";  
            for (int j = 0; j < unit[1].length && n > 0; j++) {  
                p = digit[integerPart % 10] + unit[1][j] + p;  
                integerPart = integerPart / 10;  
            }  
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;  
        }  
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");  
    }  

	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(converterToFirstSpell("欢迎来到最棒的Java中文社区"));
//		System.out.println(converterToSpell("欢迎来到最棒的Java中文社区"));
//		System.err.println(Util.md5("123456"));
		
		System.err.println(URLEncoder.encode("http://www.newlpg.com/wx/getCode.do","UTF-8"));
		System.err.println(URLDecoder.decode("http%3A%2F%2Fwx.lianguo360.com%2Fwxpay%2Fwx_oauth.php","UTF-8"));
		
		System.out.println(digitUppercase(123.34));
		
	}

}
