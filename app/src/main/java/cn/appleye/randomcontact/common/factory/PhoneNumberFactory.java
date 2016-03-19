package cn.appleye.randomcontact.common.factory;

public class PhoneNumberFactory implements IFactory{
	
	//移动
	private static String[] sMobile = new String[]{"134","135","136","137","138","139","150","151","157","158","159","187","188"};
	
	//联通
	private static String[] sUnicom = new String[]{"130", "131", "132", "152", "155", "156", "185", "186"};
	
	//电信
	private static String[] sTelecom = new String[]{"133" ,"153", "180", "189"};
	
	
	public static String createOneRandomPhoneNumber(){
		int carrieroperator = (int)(Math.random()*3);

		String telPrefix = "";
		int i = 0;
		switch(carrieroperator){
			case 0:
				i = (int)(Math.random()*sMobile.length);
				telPrefix = sMobile[i];
				break;
				
			case 1:
				i = (int)(Math.random()*sUnicom.length);
				telPrefix = sUnicom[i];
				break;
				
			case 2:
				i = (int)(Math.random()*sTelecom.length);
				telPrefix = sTelecom[i];
				break;
		}
		
		StringBuffer tel = new StringBuffer(telPrefix);
		
		for (i = 0; i < 8; i++) {
			tel.append((int) (Math.random() * 10));
		}
		
		return tel.toString();
	}
	
	public String[] createRandomNumbers(int count, boolean allowRepeat) {
		String[] numbers = new String[count];
		
		for(int i=0; i<count; i++) {
			String number= createOneRandomPhoneNumber();
			
			numbers[i] = number;
			boolean willRepeat = false;
			if(allowRepeat && i<count-1) {
				willRepeat = (int)(Math.random()*2)==1;
			}

			if (willRepeat) {
				i++;
				numbers[i] = number;
			}
		}
		
		return numbers;
	}

	@Override
	public String createFirstRandomData() {
		return createOneRandomPhoneNumber();
	}

	@Override
	public String[] createFirstRandomData(int count, boolean repeatAllowed) {
		return createRandomNumbers(count, repeatAllowed);
	}

	@Override
	public String createSecondRandomData() {
		// TODO Auto-generated method stub
		return null;
	}
}
