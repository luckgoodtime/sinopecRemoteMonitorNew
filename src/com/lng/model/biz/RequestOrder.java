package com.lng.model.biz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 *需求订单的关键信息包括：<br>
 *  创建时间，用户，用户电话，用户地址，瓶型和数量，派单时间<br>
 *  接单站点，接单人，接单人电话，到达时间,金额状态。 <br>
 *  如果一个客户要多个瓶型，那就多个订单，只是在界面展示的时候，同一个客户的
 *  订单和其他客户订单要在一起<br>
 * 
 */
@Entity
public class RequestOrder {
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name="requestOrderNo")	
	private Integer requestOrderNo;//需求号
	
	/*********查询参数开始*************/
	@Transient
	private Date startTime;//开始时间
	@Transient
	private Date endTime;//结束时间
	@Transient
	private List<String> orderSourceList;//来源,包括微信WECHAT,呼叫中心CALLCENTER,手工录入HANDWORD等
	@Transient
	private List<String> statusList;//订单状态
	@Transient
	private String sort;//排序字段
	@Transient
	private String order;//排序升序|降序 desc|asc
	@Transient
	private String gasType;//供气品种
	/*********查询参数结束*************/
	
	private String orderCreateTime;//订单创建时间
	
	private Integer orderCreateParty;//订单创建人
	
	private String customerId;//客户Id
	
	private String customerName;//客户名称
	
	private String gasCardNo;//客户用气证号
	
	private String customerAddress;//客户地址
	
	private String customerTel;//客户电话
	
	private String bottleType;//瓶型
	
	private BigDecimal price;//单价
	
	private Integer quantity;//瓶数
	
	@Column(precision=10, scale=3)
	private BigDecimal realSaleWeight;//实际销量
	
	private String expectArriveTime;//期望到到时间
	
	private String acceptStation;//接单站点
	
	private String acceptPerson;//接单人
	
	private String acceptPersonTel;//接单人电话
	
	private String scanFinishTime;//扫描结束时间
	
	private String arrivedTime;//到达时间
		
	private String bizType;//业务类型，默认是销售，可能还有调拨
	
	@Column(precision=12, scale=8)
	private BigDecimal longitude;//经度、南北，有效位数12，小数位8
	@Column(precision=12, scale=8)
	private BigDecimal latitude;//纬度
	
	@Column(precision=10, scale=2)
	private BigDecimal money;//应收金额
	
	@Column(precision=10, scale=2)
	private BigDecimal freight;//运费,有正,有负,负数的情况代表公司委托第三方去送气,如果是是正数,需要加入到应收金额,负数，需要付给第三方的不要加入到应收金额里面
	
	@Transient
	private String moneyCN;//应收金额大写
	
	private String otherPayWay;//代金券,积分抵扣,优惠券
	
	private int voucherCount;//代金券,积分抵扣,优惠券张数
	
	@Column(precision=10, scale=2)
	private BigDecimal otherPayMount;
	
	@Column(precision=10, scale=2)
	private BigDecimal accountMount;//账户扣款金额
	@Transient
	private String accountMountCN;//账户扣款大写
	
	@Column(precision=10, scale=2)
	private BigDecimal amountReceived;//实收金额	
	@Transient
	private String amountReceivedCN;//实收金额大写
	
	private String orderSource;//来源,包括微信WECHAT,呼叫中心CALLCENTER,手工录入HANDWORD等
	
	private String orderSourceId;//来源Id,微信就是openId,呼叫中心也可以放个派单员之类的
	
	private String status;//状态
	@Transient
	private String statusNm;//状态名称
	@Transient
	private String cylinderNos;//钢瓶号	
	@Transient
	private String shortCustomerAddress;//短的送气地址,去掉省市
	
	private String isSigning;//用户是否接收
	
	private String noSignReason;//未接收原因
	
	private String payTime;//结算时间
	
	private String cashier;//收银员/结算人
	
	private String note;//备注
	
	/**属于公司ID*/
	private Integer belgon2PartyId;

	public Integer getRequestOrderNo() {
		return requestOrderNo;
	}

	public void setRequestOrderNo(Integer requestOrderNo) {
		this.requestOrderNo = requestOrderNo;
	}

	public Integer getOrderCreateParty() {
		return orderCreateParty;
	}

	public void setOrderCreateParty(Integer orderCreateParty) {
		this.orderCreateParty = orderCreateParty;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getGasCardNo() {
		return gasCardNo;
	}

	public void setGasCardNo(String gasCardNo) {
		this.gasCardNo = gasCardNo;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	
	public String getBottleType() {
		return bottleType;
	}

	public void setBottleType(String bottleType) {
		this.bottleType = bottleType;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getRealSaleWeight() {
		return realSaleWeight;
	}

	public void setRealSaleWeight(BigDecimal realSaleWeight) {
		this.realSaleWeight = realSaleWeight;
	}

	public String getExpectArriveTime() {
		return expectArriveTime;
	}

	public void setExpectArriveTime(String expectArriveTime) {
		this.expectArriveTime = expectArriveTime;
	}

	public String getAcceptStation() {
		return acceptStation;
	}

	public void setAcceptStation(String acceptStaton) {
		this.acceptStation = acceptStaton;
	}

	public String getAcceptPerson() {
		return acceptPerson;
	}

	public void setAcceptPerson(String acceptPerson) {
		this.acceptPerson = acceptPerson;
	}

	public String getAcceptPersonTel() {
		return acceptPersonTel;
	}

	public void setAcceptPersonTel(String acceptPersonTel) {
		this.acceptPersonTel = acceptPersonTel;
	}
	
	public String getScanFinishTime() {
		return scanFinishTime;
	}

	public void setScanFinishTime(String scanFinishTime) {
		this.scanFinishTime = scanFinishTime;
	}

	public String getArrivedTime() {
		return arrivedTime;
	}

	public void setArrivedTime(String arrivedTime) {
		this.arrivedTime = arrivedTime;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getMoneyCN() {
		return moneyCN;
	}

	public void setMoneyCN(String moneyCN) {
		this.moneyCN = moneyCN;
	}

	public String getOtherPayWay() {
		return otherPayWay;
	}

	public void setOtherPayWay(String otherPayWay) {
		this.otherPayWay = otherPayWay;
	}
	
	public int getVoucherCount() {
		return voucherCount;
	}

	public void setVoucherCount(int voucherCount) {
		this.voucherCount = voucherCount;
	}

	public BigDecimal getOtherPayMount() {
		return otherPayMount;
	}

	public void setOtherPayMount(BigDecimal otherPayMount) {
		this.otherPayMount = otherPayMount;
	}
	
	public BigDecimal getAccountMount() {
		return accountMount;
	}

	public void setAccountMount(BigDecimal accountMount) {
		this.accountMount = accountMount;
	}
	
	public String getAccountMountCN() {
		return accountMountCN;
	}

	public void setAccountMountCN(String accountMountCN) {
		this.accountMountCN = accountMountCN;
	}

	public BigDecimal getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(BigDecimal amountReceived) {
		this.amountReceived = amountReceived;
	}
	
	public String getAmountReceivedCN() {
		return amountReceivedCN;
	}

	public void setAmountReceivedCN(String amountReceivedCN) {
		this.amountReceivedCN = amountReceivedCN;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderSourceId() {
		return orderSourceId;
	}

	public void setOrderSourceId(String orderSourceId) {
		this.orderSourceId = orderSourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getIsSigning() {
		return isSigning;
	}

	public void setIsSigning(String isSigning) {
		this.isSigning = isSigning;
	}

	public String getNoSignReason() {
		return noSignReason;
	}

	public void setNoSignReason(String noSignReason) {
		this.noSignReason = noSignReason;
	}
	
	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getBelgon2PartyId() {
		return belgon2PartyId;
	}

	public void setBelgon2PartyId(Integer belgon2PartyId) {
		this.belgon2PartyId = belgon2PartyId;
	}

	public String getStatusNm() {
		return statusNm;
	}

	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}
	
	public String getCylinderNos() {
		return cylinderNos;
	}

	public void setCylinderNos(String cylinderNos) {
		this.cylinderNos = cylinderNos;
	}
	
	public String getShortCustomerAddress() {
		return shortCustomerAddress;
	}

	public void setShortCustomerAddress(String shortCustomerAddress) {
		this.shortCustomerAddress = shortCustomerAddress;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<String> getOrderSourceList() {
		return orderSourceList;
	}

	public void setOrderSourceList(List<String> orderSourceList) {
		this.orderSourceList = orderSourceList;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getGasType() {
		return gasType;
	}

	public void setGasType(String gasType) {
		this.gasType = gasType;
	}
	
}
