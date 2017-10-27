package cn.soso.entity;
import java.util.ArrayList;
import java.util.List;

import cn.soso.abscract.*;
import cn.soso.common.CardUtil;

/**
 * 网虫套餐
 * @author Administrator
 *
 */
public class NetPackage extends ServicePackage implements NetService{


	private int flow;
	
	public NetPackage() {
		super(30);
		this.flow=500;
	}
	public void showInfo() {
		
	}

	//[start] getter && setter
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


	@Override
	public int netPlay(int flow, MobileCard card) {
		try {
			card.setRealFlow(card.getRealFlow()+flow);
		} catch (Exception e) {
			try {
				throw new Exception("本次已用流量"+flow+"G,你的余额不足，请充值后使用！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		CardUtil.cards.put(card.getCardNumber(), card);
		
		ConsumInfo cif=new ConsumInfo(0, 0, flow);
		if(CardUtil.consumInfos.containsKey(card.getCardNumber())) {
		CardUtil.consumInfos.get(card.getCardNumber()).add(cif);
		}else {
			List<ConsumInfo> list=new ArrayList<ConsumInfo>();
			list.add(cif);
			CardUtil.consumInfos.put(card.getCardNumber(), list);
		}
		return flow;
	}


	
	
	
}
