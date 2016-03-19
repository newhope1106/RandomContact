package cn.appleye.randomcontact.common.factory;

public class EmailFactory implements IFactory{
	private static String[] sEmailDomain= {
			"qq.com", "126.com", "163.com", "sina.com", "139.com", 
			"189.com", "gmail.com", "yahoo.com", "souhu.com", "hotmail.com",
			"msn.com", "163.net", "ask.com", "aol.com", "263.net", "3721.net",
			"yeah.net", "live.com"
	};
	
	public static String createOneRandomEmail() {
		int domainIndex = (int)(Math.random()*sEmailDomain.length);
		String emailSuffix = "@" + sEmailDomain[domainIndex];
		
		String prefix = EnglishNameFactory.createRandomSecondName() + "." + EnglishNameFactory.createRandomFamilyName();
		
		return prefix + emailSuffix;
	}
	
	public String[] createRandomEmails(int count, boolean allowRepeat) {
		String[] emails = new String[count];
		
		for(int i=0; i<count; i++) {
			String number= createOneRandomEmail();
			
			emails[i] = number;
			boolean willRepeat = false;
			if(allowRepeat && i<count-1) {
				willRepeat = (int)(Math.random()*2)==1;
			}

			if (willRepeat) {
				i++;
				emails[i] = number;
			}
		}
		
		return emails;
	}

	@Override
	public String createFirstRandomData() {
		
		return createOneRandomEmail();
	}

	@Override
	public String[] createFirstRandomData(int count, boolean repeatAllowed) {
		return createRandomEmails(count, repeatAllowed);
	}

	@Override
	public String createSecondRandomData() {
		// TODO Auto-generated method stub
		return null;
	}
}
