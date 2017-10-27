package cn.soso.common;

import cn.soso.entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CardUtil {

	public static HashMap<String, MobileCard> cards = new HashMap<String, MobileCard>();// 已注册用户列表
	public static HashMap<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>();// 消费记录列表

	/**
	 * 根据卡号密码验证是否注册
	 * 
	 * @param number
	 *            卡号
	 * @param passWord
	 *            密码
	 * @return 注册状态
	 */
	public boolean isExistCard(String cardNumber, String passWord) {
		MobileCard card = cards.get(cardNumber);
		if (card!=null&&cardNumber.equals(card.getCardNumber()) && passWord.equals(card.getPassWord())) {
			return true;
		}
		return false;

	}

	/**
	 * 根据卡号验证是否注册
	 * 
	 * @param number卡号
	 * @return 注册状态
	 */
	public boolean isExsitCard(String cardNumber) {
		MobileCard card = cards.get(cardNumber);
		if (card != null && cardNumber.equals(card.getCardNumber())) {
			return true;
		}

		return false;

	}

	/**
	 * 生成随机卡号
	 * 
	 * @return 卡号
	 */
	public String creatNumber() {
		
		StringBuffer sb = new StringBuffer("139");
		String number=null;
		Random rand = new Random();
		for (int i = 0; i < 8; i++) {
			sb.append(rand.nextInt(10));
		}
		number=sb.toString();
		if(CardUtil.cards.containsKey(number)) {
			number=creatNumber();
		}
		return sb.toString();
	}

	/**
	 * 生成指定个数的卡号列表
	 * 
	 * @param 指定的个数
	 * @return 卡号列表
	 */
	public String[] getNewNumbers(int count) {
		String[] newNumbers = new String[count];
		for (int i = 0; i < count; i++) {
			newNumbers[i] = creatNumber();
		}
		return newNumbers;
	}

	/**
	 * 注册新卡 先根据卡号判断注册状态，未注册写入卡集合
	 * 
	 * @param card
	 */
	public void addCard(MobileCard card) {
		if (!isExsitCard(card.getCardNumber())) {
			cards.put(card.getCardNumber(), card);
		}

	}

	/**
	 * 添加指定卡号的消费记录
	 * 
	 * @param number
	 *            卡号
	 * @param info
	 *            记录
	 */
	public void addConsumInfo(String number, ConsumInfo info) {
		List<ConsumInfo> list = consumInfos.get(number);
		list.add(info);

	}


	/**
	 * 话费充值
	 * 
	 * @param number
	 *            卡号
	 * @param money
	 *            金额
	 */
	public void chargeMoney(String number, double money) {
		MobileCard card = cards.get(number);
		card.setMoney(card.getMoney() + money);

	}

	
	/**
	 * 读取文件
	 * @param fileName 文件名
	 * @return 文件信息
	 */
	public String readFile(String fileName) {
		File file = new File(fileName);
		String string=null;
		try {
			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append("\n"+str);
			}

			string=sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

}
