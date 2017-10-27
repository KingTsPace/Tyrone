package cn.soso.profes;

import java.util.Random;

import cn.soso.abscract.ServicePackage;
import cn.soso.common.CardUtil;
import cn.soso.entity.*;

public class SosoMgr {

	CardUtil cu = new CardUtil();

	/**
	 * 显示主菜单
	 */
	void mainMenu() {
		System.out.println();
		System.out.println("********欢迎使用嗖嗖移动大厅************");
		System.out.println("1.用户登录\t2.用户注册\t3.使用嗖嗖\t4.话费充值\t5.资费说明\t6.退出系统");
		System.out.print("请选择：");
	}

	/**
	 * 选择卡号菜单
	 * 
	 * @return 卡号数组
	 */
	String[] cardMenu() {
		System.out.println("**********可选择的卡号*************");
		String[] numbers = cu.getNewNumbers(9);
		for (int i = 0; i < numbers.length; i++) {
			System.out.print((i + 1) + "." + numbers[i]+"\t");
			if ((i + 1) % 3 == 0) {
				System.out.println();
			}
		}
		System.out.print("请选择卡号：");
		return numbers;
	}


	/**
	 * 注册卡号
	 * 
	 * @param serPackage
	 *            套餐
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param money
	 *            充值金额
	 * @param cardNumber
	 *            卡号
	 */
	void registCard(int packNum, String userName, String passWord, String cardNumber,double money) {
		ServicePackage serPackage=null;
		String packName=null;
		switch (packNum) {
		case 1:
			serPackage=new TalkPackage();
			packName="话痨套餐";
			break;

		case 2:
			serPackage=new SuperPackage();
			packName="超级套餐";
			break;
		case 3:
			serPackage=new NetPackage();
			packName="网虫套餐";
			break;

		}
		if(money<serPackage.price) {
			System.out.println("您支付的费用不足以套餐的资费！");
			return;
		}
		MobileCard card = new MobileCard(cardNumber, userName, passWord, serPackage, 0, money, 0, 0, 0);
		cu.addCard(card);
		System.out.println("注册成功！\t卡号:"+cardNumber+"\t\t套餐:"+packName+"\t余额:"+card.getMoney()+"元");
	}

	
	
	/**
	 * 登录功能
	 * 
	 * @param cardNumber
	 *            卡号
	 * @param passWord
	 *            密码
	 * @return 能否登录
	 */
	boolean login(String cardNumber, String passWord) {

		
		if(!cu.isExsitCard(cardNumber)) {
			System.out.println("卡号不存在！");
		} else {
			if (CardUtil.cards.get(cardNumber).getPassWord().equals(passWord)) {

				return true;
			} else {
				System.out.println("密码错误！");
			}
		}
		return false;
		
	}

	/**
	 * 充值
	 * @param number 卡号
	 * @param chargeMoney 充值金额
	 */
	void charge(String number,double chargeMoney) {
		if (!CardUtil.cards.containsKey(number)) {

			System.out.println("卡号不存在！");
		} else {
			
			MobileCard card=CardUtil.cards.get(number);
			if(card.getSerPackage().price>(card.getMoney()+chargeMoney)) {
				System.out.println("充值后金额不足本月月费！");
			}else {
			card.setMoney(card.getMoney()+chargeMoney);
			System.out.println("充值成功,当前话费余额为:"+(card.getMoney()));
			}
		}
	}
	
	
	/**
	 * 初始化场景
	 */
	public void initScence(MobileCard card) {
		Random random=new Random();
		int initNum=-1;

		if(card.getSerPackage() instanceof TalkPackage) {
			initNum=random.nextInt(5);
		}else if(card.getSerPackage() instanceof NetPackage) {
			initNum=random.nextInt(2)+4;
		}else {
			initNum=random.nextInt(6);
		}
		
		switch (initNum) {
		case 0:
			System.out.println("问候客户，谁知其如此难缠，通话90分钟");
			if(card.getSerPackage() instanceof TalkPackage) {
				TalkPackage tp=(TalkPackage) card.getSerPackage();
				tp.call(90, card);
			}else if(card.getSerPackage() instanceof SuperPackage) {
				SuperPackage sp=(SuperPackage)card.getSerPackage();
				sp.call(90, card);
			}
			
			break;

		case 1:
			System.out.println("询问妈妈身体情况,本地通话30分钟");
			if(card.getSerPackage() instanceof TalkPackage) {
				TalkPackage tp=(TalkPackage) card.getSerPackage();
				tp.call(30, card);
			}else if(card.getSerPackage() instanceof SuperPackage) {
				SuperPackage sp=(SuperPackage)card.getSerPackage();
				sp.call(30, card);
			}
			break;
		case 2:
			System.out.println("参与环境保护实施方案问卷调查，发送短信5条");
			if(card.getSerPackage() instanceof TalkPackage) {
				TalkPackage tp=(TalkPackage) card.getSerPackage();
				tp.send(5, card);
			}else if(card.getSerPackage() instanceof SuperPackage) {
				SuperPackage sp=(SuperPackage)card.getSerPackage();
				sp.send(5, card);
			}
			break;
		case 3:
			System.out.println("通知朋友手机换号，发送短信50条");
			if(card.getSerPackage() instanceof TalkPackage) {
				TalkPackage tp=(TalkPackage) card.getSerPackage();
				tp.send(50, card);
			}else if(card.getSerPackage() instanceof SuperPackage) {
				SuperPackage sp=(SuperPackage)card.getSerPackage();
				sp.send(50, card);
			}
			break;
		case 4:
			System.out.println("和女朋友用微信视频聊天,使用流量1G");
			if(card.getSerPackage() instanceof SuperPackage) {
				SuperPackage tp=(SuperPackage)card.getSerPackage();
				tp.netPlay(1, card);
			}else if(card.getSerPackage() instanceof NetPackage) {
				NetPackage sp=(NetPackage)card.getSerPackage();
				sp.netPlay(1, card);
			}
			break;
		case 5:
			System.out.println("晚上手机在线看韩剧,不留神睡着啦！使用流量2G");
			if(card.getSerPackage() instanceof SuperPackage) {
				SuperPackage tp=(SuperPackage)card.getSerPackage();
				tp.netPlay(2, card);
			}else if(card.getSerPackage() instanceof NetPackage) {
				NetPackage sp=(NetPackage)card.getSerPackage();
				sp.netPlay(2, card);
			}
			break;
		}
	}
	
	
	/**
	 * 资费说明
	 */
	public void showDescription(String fileName) {
		System.out.println(cu.readFile(fileName));
	}
	
	
	
	
}
