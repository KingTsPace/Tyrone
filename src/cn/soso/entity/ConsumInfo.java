package cn.soso.entity;


/**
 * 消费记录
 * @author Administrator
 *
 */
public class ConsumInfo {

	
	private int talkTime;//通话时间
	private int smsCount;//短信数量
	private int flow;//流量
	private double price;//消费金额
	
	
	
	public ConsumInfo(int talkTime, int smsCount, int flow) {
		super();
		this.talkTime = talkTime;
		this.smsCount = smsCount;
		this.flow = flow;
		this.price = talkTime*0.2+smsCount*0.1+flow;
	}
	//[start]getter && setter
	public int getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}
	public int getSmsCount() {
		return smsCount;
	}
	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	//[end]
	
	
}
