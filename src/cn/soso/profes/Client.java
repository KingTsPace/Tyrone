package cn.soso.profes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.soso.common.CardUtil;
import cn.soso.entity.*;

public class Client {

	public static void main(String[] args) {
		// [start]初始化三张卡
		MobileCard c1 = new MobileCard("13911111111", "老王", "wty", new NetPackage(), 89, 90, 10, 1, 0);
		MobileCard c2 = new MobileCard("13922222222", "老王", "wty", new TalkPackage(), 89, 90, 90, 7, 1);
		MobileCard c3 = new MobileCard("13933333333", "老王", "wty", new SuperPackage(), 89, 10, 10, 10, 1);
		
		CardUtil.cards.put(c1.getCardNumber(), c1);
		CardUtil.cards.put(c2.getCardNumber(), c2);
		CardUtil.cards.put(c3.getCardNumber(), c3);
		
		
		List<ConsumInfo> lis=new ArrayList<ConsumInfo>() ;
		lis.add(new ConsumInfo(0,0,4));
		lis.add(new ConsumInfo(0,7,0));
		lis.add(new ConsumInfo(2,0,0));
		CardUtil.consumInfos.put(c3.getCardNumber(), lis );
		// [end]

		SosoMgr sosoMgr = new SosoMgr();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choseNum = 0;
		while (true) {
			String number = null;
			String passWord = null;
			String userName = null;
			double money = 0;
			sosoMgr.mainMenu();
			switch (sc.nextInt()) {
			case 1:// 用户登录
				System.out.print("请输入手机卡号:");
				number = sc.next();
				System.out.print("请输入密码:");
				passWord = sc.next();
				boolean islogin = sosoMgr.login(number, passWord);
				if (islogin) {//登录成功进入二级菜单
					MobileCard card = CardUtil.cards.get(number);
					UserMenu uMenu = new UserMenu(card);// 二级菜单对象
					boolean flag=true;
					do {
						uMenu.showMenu();// 显示二级 用户菜单
						switch (sc.nextInt()) {
						case 1:// 账单查询
							uMenu.showAmountDetail();
							break;

						case 2:// 余量查询
							uMenu.showRemainDetiail();
							break;
						case 3:// 打印消费详单
							uMenu.printConsumInfo();
							break;
						case 4:// 套餐变更
							System.out.print("请选择套餐(1.话痨套餐\t2.超级套餐\t3.网虫套餐):");
							uMenu.changingPack(sc.nextInt());
							break;
						case 5:// 办理退网
							uMenu.delCard();
							flag=false;
							break;
							
						default:
							flag=false;
							break;
						}
					}while (flag);
				}
				break;
			case 2:// 用户注册
				System.out.print("1.话痨套餐\t2.超级套餐\t3.网虫套餐,\t请选择套餐(输入序号)：");
				choseNum = sc.nextInt();
				String[] numbers = sosoMgr.cardMenu();// 获得号码数组
				number = numbers[sc.nextInt() - 1];
				System.out.print("请输入姓名:");
				userName = sc.next();
				System.out.print("请输入密码:");
				passWord = sc.next();
				System.out.print("请输入预存话费金额:");
				money = sc.nextDouble();
				
				sosoMgr.registCard(choseNum, userName, passWord, number, money);
				break;

			case 3:// 使用嗖嗖
				System.out.print("输入手机卡号:");
				number=sc.next();
				MobileCard card=CardUtil.cards.get(number);
				sosoMgr.initScence(card);
				
				break;

			case 4:// 话费充值
				System.out.print("请输入充值卡号：");
				number=sc.next();
				System.out.print("请输入充值金额:");
				money=sc.nextDouble();
				
				sosoMgr.charge(number, money);
				break;

			case 5:// 资费说明
				sosoMgr.showDescription("description.txt");
				break;

			case 6:// 退出系统
				System.out.println("谢谢使用");
				return;
			}

		}
	}
}
