package cn.soso.entity;


import cn.soso.abscract.*;

/**
 * 嗖嗖移动卡
 * 
 * @author Administrator
 *
 */
public class MobileCard {

	private String cardNumber;// 卡号
	private String userName;// 用户名
	private String passWord;// 密码
	private ServicePackage serPackage;// 所属套餐
	private double consumAmount;// 当月消费金额
	private double money;// 账户余额
	private int realTalkTime;// 当月实际通话时长

	private int realSMSCount;// 实际发送短信数
	private int realFlow;// 实际上网流量

	public void showMeg() {

	}

	/**
	 * 构造函数
	 * 
	 * @param cardNumber
	 * @param userName
	 * @param passWord
	 * @param serPackage
	 * @param consumAmount
	 * @param money
	 * @param realTalkTime
	 * @param realSMSCount
	 * @param realFlow
	 */
	public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage,
			double consumAmount, double money, int realTalkTime, int realSMSCount, int realFlow) {
		super();
		this.cardNumber = cardNumber;
		this.userName = userName;
		this.passWord = passWord;
		this.consumAmount = consumAmount;
		this.money = money;
		this.realTalkTime = realTalkTime;
		this.realSMSCount = realSMSCount;
		this.realFlow = realFlow;
		setSerPackage(serPackage);
		this.money=money-serPackage.price;
	}

	
	/**
	 * 计算资费
	 * @throws Exception 费用超出余额,抛出异常
	 */
	void countAmount() throws Exception {
		double talkAmount=0;
		double smsAmount=0;
		double flowAmount=0;
		double serAmount=0;
		double currenAmount=0;
		if(serPackage instanceof TalkPackage) {
			TalkPackage tp=(TalkPackage)serPackage;
			talkAmount=(this.realTalkTime>tp.getTalkTime()?this.realTalkTime-tp.getTalkTime():0)*0.2;
			smsAmount=(this.realSMSCount>tp.getSmsCount()?this.realSMSCount-tp.getSmsCount():0)*0.1;
			flowAmount=this.realFlow;
			serAmount=tp.getPrice();
		}else if(serPackage instanceof SuperPackage) {
			SuperPackage sp=(SuperPackage)serPackage;
			talkAmount=(this.realTalkTime>sp.getTalkTime()?this.realTalkTime-sp.getTalkTime():0)*0.2;
			smsAmount=(this.realSMSCount>sp.getSmsCount()?this.realSMSCount-sp.getSmsCount():0)*0.1;
			flowAmount=this.realFlow>sp.getFlow()?this.realFlow-sp.getFlow():0;
			serAmount=sp.getPrice();
		}else if(serPackage instanceof NetPackage) {
			NetPackage np=(NetPackage)serPackage;
			talkAmount=this.realTalkTime*0.2;
			smsAmount=this.realSMSCount*0.1;
			flowAmount=this.realFlow>np.getFlow()?this.realFlow-np.getFlow():0;
			serAmount=np.getPrice();
		}
		
		currenAmount=talkAmount+smsAmount+flowAmount;
		
		if(currenAmount>this.money) {
			throw new Exception();
		}
		consumAmount=currenAmount+serAmount;
		this.money=this.money-currenAmount;
	}
	
	// [start]getter && setter
	public ServicePackage getSerPackage() {
		return serPackage;
	}

	public void setSerPackage(ServicePackage serPackage) {
		this.serPackage = serPackage;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public double getConsumAmount() {
		return consumAmount;
	}

	public void setConsumAmount(double consumAmount) {
		this.consumAmount = consumAmount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getRealTalkTime() {
		return realTalkTime;
	}

	public void setRealTalkTime(int realTalkTime) throws Exception {
		this.realTalkTime = realTalkTime;
		countAmount();
	}

	public int getRealSMSCount() {
		return realSMSCount;
	}

	public void setRealSMSCount(int realSMSCount) throws Exception {
		this.realSMSCount = realSMSCount;
		countAmount();
	}

	public int getRealFlow() {
		return realFlow;
	}

	public void setRealFlow(int realFlow) throws Exception {
		this.realFlow = realFlow;
		countAmount();
	}

	// [end]
}
