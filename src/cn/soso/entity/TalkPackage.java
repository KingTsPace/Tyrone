package cn.soso.entity;
import cn.soso.abscract.*;
import cn.soso.common.CardUtil;
/**
 * 话痨套餐
 * @author Administrator
 *
 */
public class TalkPackage extends ServicePackage implements CallService,SendService{

	private int talkTime;//每月可用通话时长
	private int smsCount;//可用短信
	private double price;//套餐资费
	
	
	
	public TalkPackage() {
		super(50);
		this.talkTime=500;
		this.smsCount=100;
	
	}

	
	
	public void showInfo() {
		
		System.out.println("话痨套餐：\n"
				+ "通话时长为:"+this.talkTime+"分钟/月\n"
				+ "短信条数为:"+this.smsCount+"条/月\n"
				+ "套餐资费为:"+this.price+"元/月");
	}

	//[start] getter && setter
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	//[end]




	@Override
	public int send(int count, MobileCard card){
		try {
			card.setRealSMSCount(card.getRealFlow()+count);
		} catch (Exception e) {
			try {
				throw new Exception("本次已发送"+count+"G,你的余额不足，请充值后使用！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		CardUtil.cards.put(card.getCardNumber(), card);
		
		ConsumInfo cif=new ConsumInfo(0, count, 0);
		CardUtil.consumInfos.get(card.getCardNumber()).add(cif);
		return count;
	}

	@Override
	public int call(int minCount, MobileCard card) {
		try {
			card.setRealTalkTime(card.getRealTalkTime()+minCount);
		} catch (Exception e) {
			try {
				throw new Exception("本次已发送"+minCount+"G,你的余额不足，请充值后使用！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		CardUtil.cards.put(card.getCardNumber(), card);
		
		ConsumInfo cif=new ConsumInfo(minCount,0, 0);
		CardUtil.consumInfos.get(card.getCardNumber()).add(cif);
		return minCount;
	}
	
	
	
	
}
