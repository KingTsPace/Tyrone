package cn.soso.profes;

import java.util.List;

import cn.soso.entity.ConsumInfo;
import cn.soso.entity.MobileCard;
import cn.soso.entity.NetPackage;
import cn.soso.entity.SuperPackage;
import cn.soso.entity.TalkPackage;
import cn.soso.common.CardUtil;

public class UserMenu {

	MobileCard card;
	// CardUtil cu=new CardUtil();

	public UserMenu(MobileCard card) {
		super();
		this.card = card;
	}

	/**
	 * 显示用户菜单
	 */
	void showMenu() {
		System.out.println();
		System.out.println("****嗖嗖移动用户菜单****");
		System.out.println("1.本月账单查询");
		System.out.println("2.套餐余量查询");
		System.out.println("3.打印消费详单");
		System.out.println("4.套餐变更");
		System.out.println("5.办理退网");
		System.out.print("请选择(输入1~5选择功能,其他键返回上一级):");
	}

	/**
	 * 本月账单查询
	 */
	public void showAmountDetail() {
		List<ConsumInfo> list = CardUtil.consumInfos.get(card.getCardNumber());
		if (list != null) {
			System.out.println();
			System.out.println("******本月账单查询*******");
			System.out.println("你的卡号:" + card.getCardNumber());
			System.out.println("套餐资费:" + card.getSerPackage().price + "元");
			System.out.println("合计:" + card.getConsumAmount() + "元");
			System.out.println("账户余额:" + card.getMoney() + "元");
		}else {
			System.out.println("该卡无通话记录！");
		}
	}

	/**
	 * 套餐余量查询
	 */
	public void showRemainDetiail() {
		int remainTalktime = 0;
		int remainSmsCount = 0;
		int remainFlow = 0;
		if(card.getSerPackage() instanceof TalkPackage) {
			TalkPackage tp=(TalkPackage)card.getSerPackage();
			remainTalktime=card.getRealTalkTime()<tp.getTalkTime()?tp.getTalkTime()-card.getRealTalkTime():0;
			remainSmsCount=card.getRealSMSCount()<tp.getSmsCount()?tp.getSmsCount()-card.getRealSMSCount():0;
			remainFlow=0;
			
		}else if(card.getSerPackage() instanceof SuperPackage) {
			SuperPackage sp=(SuperPackage)card.getSerPackage();
			remainTalktime=card.getRealTalkTime()<sp.getTalkTime()?sp.getTalkTime()-card.getRealTalkTime():0;
			remainSmsCount=card.getRealSMSCount()<sp.getSmsCount()?sp.getSmsCount()-card.getRealSMSCount():0;
			remainFlow=card.getRealFlow()<sp.getFlow()?sp.getFlow()-card.getRealFlow():0;
			
			
			
		}else if(card.getSerPackage() instanceof NetPackage) {
			NetPackage np=(NetPackage)card.getSerPackage();
			remainTalktime=0;
			remainSmsCount=0;
			remainFlow=card.getRealFlow()<np.getFlow()?np.getFlow()-card.getRealFlow():0;
			
			
		}
		System.out.println();
		System.out.println("******套餐余量查询*******");
		System.out.println("你的卡号是:"+card.getCardNumber()+",套餐余量:");
		System.out.println("通话时长:" + remainTalktime+ "分钟");
		System.out.println("短信条数:" + remainSmsCount+ "条");
		System.out.println("上网流量:" + remainFlow+ "GB");
	}

	/**
	 * 打印消费详单
	 */
	public void printConsumInfo() {
		List<ConsumInfo> list = CardUtil.consumInfos.get(card.getCardNumber());
		if (list == null) {
			System.out.println("对不起，不存在此号码消费记录，不能打印");
		} else {
			System.out.println();
			System.out.println("*******" + card.getCardNumber() + "消费记录******");
			System.out.println("序号\t类型\t数据(通话(条)/上网(MB)/短信(条))");
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getTalkTime()!=0) {
					System.out.println((i+1)+"\t通话\t"+list.get(i).getTalkTime());
				}else if(list.get(i).getSmsCount()!=0) {
					System.out.println((i+1)+"\t短信\t"+list.get(i).getSmsCount());
				}else if(list.get(i).getFlow()!=0) {
					System.out.println((i+1)+"\t上网\t"+list.get(i).getFlow());
				}
			}
		}

	}

	/**
	 * 套餐变更 1.话痨 2.超级 3.网虫
	 * 
	 * @param packNum
	 *            用户输入的数字
	 */

	public void changingPack(int packNum) {

		switch (packNum) {
		case 1:
			if(!(card.getSerPackage() instanceof TalkPackage)) {
				card.setSerPackage(new TalkPackage());
				System.out.println("变更为话痨套餐！");
			}else {
				System.out.println("本卡原套餐为话痨套餐，不可变更!");
			}
			break;

		case 2:
			if(!(card.getSerPackage() instanceof SuperPackage)) {
				card.setSerPackage(new SuperPackage());
				System.out.println("变更为超级套餐！");
			}else {
				System.out.println("本卡原套餐为超级套餐，不可变更!");
			}
			break;
		case 3:
			if(!(card.getSerPackage() instanceof NetPackage)) {
				card.setSerPackage(new NetPackage());
				System.out.println("变更为网虫套餐！");
			}else {
				System.out.println("本卡原套餐为网虫套餐，不可变更!");
			}
			break;

		}

		CardUtil.cards.put(card.getCardNumber(), card);
	}

	/**
	 * 办理退网 删除卡集合，通话信息集合内记录
	 * 
	 * @param number
	 *            卡号
	 */
	public void delCard() {
		CardUtil.cards.remove(card.getCardNumber());
		CardUtil.consumInfos.remove(card.getCardNumber());
		System.out.println("退网成功，退出登录！");
	}


	
	
	
}
