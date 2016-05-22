package cn.appleye.randomcontact.common.factory;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 随机生成中文名
 * */
public class ChinseNameFactory implements IFactory{
	private static final String[] sSurname = {
			"王","李","张","刘","陈","杨","黄","赵","吴","周", "徐","孙","马","朱","胡","林","郭",
			"何","高","罗","郑","梁","谢","宋","唐","许","邓","冯","韩","曹","曾","彭","萧","蔡",
			"潘","田","董","袁","于","余","叶","蒋","杜","苏","魏","程","吕","丁","沈","任","姚",
			"卢","傅","钟","姜","崔","谭","廖","范","汪","陆","金","石","戴","贾","韦","夏","邱",
			"方","侯","邹","熊","孟","秦","白","江","阎","薛","尹","段","雷","黎","史","龙","陶",
			"贺","顾","毛","郝","龚","邵","万","钱","严","赖","覃","洪","武","莫","孔","司马","上官",
			"欧阳","夏侯","诸葛","东方","赫连","皇甫","尉迟","公羊","澹台","公冶","淳于","单于","太叔",
			"申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空",
			"司寇","南门","呼延","子车","颛孙","端木","公西","公良","拓跋","宰父","谷梁","百里","东郭",  
	        "梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","东乡","相里","胡母","司城",
	        "张廖","雍门","独孤","北宫"
	};
	
	private static String sGirl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环"
			+ "雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓"
			+ "纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢"
			+ "霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";  
    private static String sBoy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥"
    		+ "才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松"
    		+ "善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德"
    		+ "行时泰盛雄琛钧冠策腾楠榕风航弘";

	private int mMinLength;
	private int mMaxLength;

	public ChinseNameFactory() {
		this(2, 3);
	}

	public ChinseNameFactory(int minLength, int maxLength) {
		mMinLength = minLength;
		mMaxLength = maxLength;
	}
	
	public static String createRandomName() {
		return getChineseSurname() + getChineseName();
	}
	
	/**
	 * 获取随机姓
	 * */
	private static String getChineseSurname(){
		int index = (int)(Math.random() * sSurname.length);
		
		return sSurname[index];
	}
	
	public static int getNum(int start,int end) {  
        return (int)(Math.random()*(end-start+1)+start);  
    }
	
	/**
	 * 获取随机名
	 * */
	private static String getChineseName() {  
        int sex=getNum(0,1);  
        String str=sBoy;  
        int length=sBoy.length();  
        if(sex==0){  
            str=sGirl;  
            length=sGirl.length();  
        }
        int index=getNum(0,length-1);  
        String second=str.substring(index, index+1);  
        int hasThird=getNum(0,1);  
        String third="";  
        if(hasThird==1){  
            index=getNum(0,length-1);  
            third=str.substring(index, index+1);  
        }  
        return second+third;  
    }  
	
	/**
	 * 获取随机名
	 * @param min 最小长度
	 * @param max 最大长度
	 * */
	private static String getChineseName(int min, int max) {
		if (max < 1) {
			return "";
		}

		if (min<1) {
			min =1;
		}

		if(min>=2 && max <=4) {
			return getChineseSurname() + getChineseName();
		}

		int nameLength = min + (int)((max-min+1)*Math.random());

		StringBuffer sb = new StringBuffer();
		for(int i=0; i<nameLength; i++) {
			String str = null;
			int highPos, lowPos;
			Random random = new Random();
			highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
			random = new Random();
			lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列

			byte[] bArr = new byte[2];
			bArr[0] = (new Integer(highPos)).byteValue();
			bArr[1] = (new Integer(lowPos)).byteValue();
			try {
				str = new String(bArr, "GB2312");   //区位码组合成汉字

				sb.append(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
        return sb.toString();
    }

	@Override
	public String createFirstRandomData() {
		return getChineseName(mMinLength, mMaxLength);
	}

	@Override
	public String[] createFirstRandomData(boolean repeatAllowed) {
		return new String[] {createFirstRandomData()};
	}

	@Override
	public String createSecondRandomData() {
		return null;
	}  
}
