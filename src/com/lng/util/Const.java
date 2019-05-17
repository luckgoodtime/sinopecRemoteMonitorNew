
package com.lng.util;

public class Const {

	
    public static final int PAGESIZE_DEFAULT = 15;// 分页默认显示数量
	public static final int PAGESIZE_DEFAULT_M = 10;// 分页默认显示页数（移动端）
	public static final int PARTY_STATUS_USE = 1;// 可用
	public static final int PARTY_STATUS_NOUSE = -1;// 不可用
	
	public static final int PARTY_TYPE_PERSON = 1;//Person
	public static final int PARTY_TYPE_CORP = 2;//Corporation
	public static final int PARTY_TYPE_SOURCEPOINT = 3;//SourcePoint
	public static final int PARTY_TYPE_ENDPOINT = 4;//EndPoint
	
	public static final String WSH = "未审核";
	public static final String TG = "通过";
	public static final String WTG = "未通过";
	public static final String WJH = "未激活";
	public static final String YJH = "已激活";
	
	public static final String DEFAULT_PWD = "123456";
	
	public static final int ROLE_ADMIN = 2;// 管理员
	
	/**
	 * 订单状态：待受理(微信过来)，待派单，已扫描，已完成，已作废
	 * s 代表状态status/state 首字母，数字为了体现顺序
	 */
	public static final String ORDER_STATE_WAIT_HANDLE = "S00_WAIT_HANDLE";//待受理(微信过来)
	public static final String ORDER_STATE_WAIT_DISPATCH = "S01_WAIT_DISPATCH";//待派单
	public static final String ORDER_STATE_WAIT_SCAN = "S02_WAIT_SCAN";//待扫描
	public static final String ORDER_STATE_ALREADY_SCAN = "S03_ALREADY_SCAN";//已扫描
	
	public static final String ORDER_STATE_WAIT_FILL = "S05_WAIT_FILL";//待灌装

	public static final String ORDER_STATE_WAIT_PAY = "S06_WAIT_PAY";//待结算
	
	public static final String ORDER_STATE_ALREADY_FINISH = "S08_ALREADY_FINISH";//已完成
	public static final String ORDER_STATE_DEL = "S99_DEL";//已作废
	
	public static final String ORDER_BIZ_TYPE_SCAN_SALE = "SCAN_SALE";
	public static final String ORDER_BIZ_TYPE_NO_SCAN_SALE = "NO_SCAN_SALE";
	
	/**
	 *检查维修单状态:待受理(微信过来),待派单,待处理,已完成，已作废
	 *
	 */
	
	public static final String CHECK_STATE_WAIT_HANDLE = "S00_WAIT_HANDLE";//待受理(微信过来)
	public static final String CHECK_STATE_WAIT_DISPATCH = "S01_WAIT_DISPATCH";//待派单
	public static final String CHECK_STATE_WAIT_TREAT = "S02_WAIT_TREAT";//待处理
	public static final String CHECK_STATE_ALREADY_FINISH = "S08_ALREADY_FINISH";//已完成
	public static final String CHECK_STATE_DEL = "S99_DEL";//已作废
	
	
	/**
	 *费用类型
	 * 
	 */
	public static final String FEE_OF_BOTTLE_DEPOSIT = "BOTTLE_DEPOSIT";//钢瓶押金
	
	public static final String FEE_OF_BOTTLE_DEPOSIT_NAME = "钢瓶押金";//
	
	public static final String FEE_OF_BOTTLE_USE = "BOTTLE_USE_FEE";//钢瓶使用费
	
	public static final String FEE_OF_BOTTLE_USE_NAME = "钢瓶使用费";//
	

	
	/**
	 *
	 *费用状态
	 * 
	 */
	public static final String FEE_STATE_ALREADY_FINISH = "S04_ALREADY_FINISH";//已完成
	public static final String FEE_STATE_ALREADY_RETURN = "S08_ALREADY_RETURN";//已退费
	public static final String FEL_STATE_DEL = "S99_DEL";//已作废
	
	
	/**
	 * 
	 * 调拨单状态
	 * 
	 */
	public static final String ALLOCATE_WAIT_CONFIRM = "S02_WAIT_CONFIRM";//待确认
	
	public static final String ALLOCATE_ALREADY_FINISH = "S06_ALREADY_FINISH";//已完成
	
	public static final String ALLOCATE_STATE_DEL = "S99_DEL";//已作废
	
	/**
	 * 盘点类型，期初盘点(STOCK_TAKING_INIT),与每日盘点(STOCK_TAKING_DAILY)
	 */
	public static final String STOCK_TAKING_INIT = "STOCK_TAKING_INIT";//期初盘点
	
	public static final String STOCK_TAKING_DAILY = "STOCK_TAKING_DAILY";//每日盘点
	
	public static final String STOCK_TAKING_STATE_NORMAL = "正常";
	
	/**
	 *订单类型 
	 */
	public static final String ORDER_REQUESTORDER = "REQUESTORDER";//普通的送气订单
	
	public static final String ORDER_REQUESTORDER_B = "REQUESTORDER_B";//工业户订单
	
	/**
     * 价格用于 CivilPrice
     */
    public static final String PRICE_FOR_RESIDENT = "resident";//居民户
    public static final String PRICE_FOR_BUSINESS = "business";//商业户
    public static final String PRICE_FOR_INDUSTRY = "industry";//工业户
    

    
    public static final String CIVIL_PRICE_STATE_WAIT_APPROVE = "待审批";
    public static final String CIVIL_PRICE_STATE_APPROVED = "已审批";
    
    public static final String STOCK_BIZ_TYPE_IN = "盘点入库";
    public static final String STOCK_BIZ_TYPE_OUT = "盘点出库";
    
    public static final String IN_STOCK = "1";//入库
    public static final String OUT_STOCK = "-1";//出库
    
    public static final String GAS_TYPE = "gasType";//气品
    public static final String GAS_TYPE_NONSTANDARD = "gasTypeNonstandard";//气品(非标准)
}
