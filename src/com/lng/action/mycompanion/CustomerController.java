package com.lng.action.mycompanion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lng.action.main.BaseController;
import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.model.biz.partner.Customer;
import com.lng.service.base.CorporationService;
import com.lng.service.base.PersonService;
import com.lng.service.biz.order.partner.CustomerService;
import com.lng.util.Util;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{

	public static final String DIR = "mycompanion/customer/";
	public static final String R = "redirect:/customer/list.do";

	@Resource
	private CustomerService customerService;
	@Resource
	private CorporationService corporationService;
	@Resource
	private PersonService personService;
	
	
	/**
	 * @category ajax公司客户列表
	 */
	@RequestMapping("/listAll.do")
	@ResponseBody
	public List listAll(HttpServletRequest request) {
		
		Person person = (Person) session.getAttribute("person");
		
		String q = request.getParameter("q");
		String hql = "from Customer where belgon2PartyId='" + person.getCorpPartyId() + "'";
		if(StringUtils.isNotBlank(q)) {
			//hql = hql +  " and ( gasCardNo like '" + q + "%' or cusContactTel like '" + q + "%' or cusName like '%" + q + "%' or cusShortName like '%" + q  + "%')";
			hql = hql +  " and ( gasCardNo like '" + q + "%')"; //只查用气证
		}
		
		List customerList = customerService.getListForPageDefault(hql).getDataList();
		
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		for(int i = 0; i < customerList.size(); i++){
			 Customer c = (Customer)customerList.get(i);
			  Map<String, String> m = new HashMap<String, String>();
			  m.put("id", String.valueOf(c.getId().intValue()));
			  m.put("name", c.getCusName()+"_" + c.getGasCardNo() + "_" + c.getCusContactTel() + "_" + c.getReceiptPlace());
			  m.put("no", c.getGasCardNo());
			  m.put("tel", c.getCusContactTel());
			  m.put("address", c.getReceiptPlace());
			  m.put("cusName", c.getCusName());
			  returnList.add(m);
		 }
		
		 return returnList;
	}
	

	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Customer customer,HttpServletRequest request) {
		
		request.setAttribute("p", customerService.getListForPageDefault(customer, false));
		request.setAttribute("customer", customer);
		return DIR +  "list";
	}

	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Customer o) {
		if (o.getId() != null) {
			model.addAttribute("o", customerService.get(Customer.class, o.getId()));
		}
		// 公司列表
		model.addAttribute("corporationList", corporationService.getList());
		// 人员列表
		model.addAttribute("personServiceList", personService.getList());

		return DIR + "addOrUpdate";
	}

	/**
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Customer o, HttpSession session, HttpServletRequest request) {
		
		Person person = (Person) session.getAttribute("person");
		
		//通过用气证号排重
		if(!StringUtils.isEmpty(o.getGasCardNo())) {
			List customerList = customerService.getCustomerListByGasCardNo(o.getGasCardNo(), person.getCorpPartyId());
			if(customerList.size() > 0){
				o = (Customer)customerList.get(0);
				request.setAttribute("id", o.getId());
				return "forward:/customer/addOrUpdate.do?id=" + o.getId();
			}
		}
		
		//通过身份证排重
		if(!StringUtils.isEmpty(o.getCusContactIdNo())) {
			List customerList = customerService.getCustomerListByContactIdNo(o.getCorpPartyId(), o.getCusContactIdNo());
			if(customerList.size() > 0){
				o = (Customer)customerList.get(0);
				request.setAttribute("id", o.getId());
				return "forward:/customer/addOrUpdate.do?id=" + o.getId();
			}
		}

		// 新增

		Corporation c = (Corporation) session.getAttribute("corporation");
		o.setCreateDate(Util.dateToStr2(new Date()));
		o.setCreateParty(person.getPartyId());
		o.setBelgon2PartyId(person.getCorpPartyId());
		customerService.save(o);
	
		return R;
	}
	
	/**
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(Customer customer, HttpSession session) {

		
		// 修改
		Customer o2 = (Customer) customerService.get(Customer.class, customer.getId());
		if (o2 != null) {
			o2.setCusName(customer.getCusName());
			o2.setBizManager(customer.getBizManager());
			o2.setBizStartDate(customer.getBizStartDate());
			o2.setSerialNo(customer.getSerialNo());
			o2.setRegion(customer.getRegion());
			o2.setCorpPartyId(customer.getCorpPartyId());
			o2.setCusContactTel(customer.getCusContactTel());			
		
			//联系人身份证号
			o2.setCusContactIdNo(customer.getCusContactIdNo());
			//送气地址
			o2.setReceiptPlace(customer.getReceiptPlace());
			//客户类型(居民户 ,企事业)
			o2.setCusCategory(customer.getCusCategory());
			//行业性质（工业，商业，企事业）
			o2.setCusType(customer.getCusType());
			//用气证号*/
			o2.setGasCardNo(customer.getGasCardNo());
			//销售类型（批发|直销）
			o2.setSaleType(customer.getSaleType());
			//钢瓶租用方式（全额押金|部分押金|免押金）
			o2.setBottleRentType(customer.getBottleRentType());
			//用气周期（天）*/	
			o2.setCycleDays(customer.getCycleDays());
			//结算周期（现结|月结|周结）
			o2.setPayCycle(customer.getPayCycle());
			//业务代表
			o2.setBizRep(customer.getBizRep());
			//定价方式（浮动价|月价|半月价|季度价|年价|议价）
			o2.setPriceType(customer.getPriceType());
			
			o2.setParentCusId(customer.getParentCusId());
			o2.setParentCusName(customer.getParentCusName());
			o2.setParentSerialNo(customer.getParentSerialNo());			
			
			customerService.update(o2);
		}
	
		return R;
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			customerService.del(ids);
		}
		return R;
	}
	
	/**
	 * @category 查询导入文件
	 */
	@RequestMapping(value = "/queryImportfile.do")
	public String queryImportFile(Model model) {
		
		List<JSONObject> result = new LinkedList<JSONObject>();
		
		String rootPath = "/usr";
		
		//String rootPath = "D:";
		
		String importDirPath = rootPath + File.separator + "importDir";
		
		File  importDir = new File(importDirPath);
		
		if(!importDir.exists()) importDir.mkdirs();
		
		File[] files = importDir.listFiles();
		
		for(File file : files) {
			JSONObject json = new JSONObject();
			json.put("filename", file.getName());
			result.add(json);
		}
		
		model.addAttribute("p", result);
		return DIR  + "queryFile4Import";
	}	
	
	
	/**
	 * @category 导入客户数据
	 */
	@RequestMapping(value = "/importcustomer.do")
	public String importDate(Model model, HttpServletRequest request) {
		
		Person person = (Person) session.getAttribute("person");
		
		String corpPartyId = request.getParameter("corpPartyId");
		
		String rootPath = "/usr";
		//String rootPath = "D:";
		
		String importDirPath = rootPath + File.separator + "importDir";
		
		File  importDir = new File(importDirPath);
		
		if(!importDir.exists()) importDir.mkdirs();
		
		File[] files = importDir.listFiles();
		
		List<JSONObject> result = new LinkedList<JSONObject>();
		
		for(File file : files) {
			
			System.out.println(file.getName());
			JSONObject json = new JSONObject();
			json.put("filename", file.getName());
			
			importFile(person, corpPartyId, file, json);
			
			result.add(json);
			
			file.delete();
		}
		
		model.addAttribute("p", result);
		
		return DIR  + "dataImportResult";
	}
	
	public void importFile(Person person,String corpPartyId, File file, JSONObject json) {
		if(file == null || !file.exists())
			return;
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 0;
			//一次读一行，读入null时文件结束
			List<Customer> list = new LinkedList<Customer>();
			int batchNum = 100;
			while ((tempString = reader.readLine()) != null) {
				//把当前行号显示出来
				System.out.println("line " + line + ": " + tempString);
				
				String[] tokens = tempString.split(",");
				if(tokens.length >= 2 && tokens[0].length() > 0) {
					Customer customer = new Customer();
					
					//bizStartDate,cusName,cusContactTel,cusContactIdNo,receiptPlace,cusType,gasCardNo,bottleRentType
					customer.setBizStartDate(tokens[0].trim());					
					customer.setCusName(tokens[1].trim());					
					customer.setCusContactTel(tokens[2].trim());
					customer.setCusContactIdNo(tokens[3].trim());
					
					customer.setReceiptPlace(tokens[4].trim());					
					customer.setCusType(tokens[5].trim());
					if(tokens.length > 6) {
						customer.setGasCardNo(tokens[6].trim());
					}
					if(tokens.length > 7) {
						customer.setBottleRentType(tokens[7].trim());
					}
					
					customer.setBelgon2PartyId(Integer.valueOf(corpPartyId));
					
					customer.setCreateDate(Util.datetoStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
					customer.setCreateParty(person.getPartyId());
					
					list.add(customer);
					batchNum++;
				}
				
				if(batchNum > 100); {
					this.baseService.batchSaveOrUpdate(list);
					list = null;
					list = new LinkedList<Customer>();
					batchNum = 0;
				}
				line++;
			}
			
			if(list.size() > 0)
				this.baseService.batchSaveOrUpdate(list);
			
			reader.close();
			
			json.put("line", line);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if (reader != null){
				
				try {
					reader.close();
					} catch (IOException e1) {
				}
			}
		}
	}	

}
