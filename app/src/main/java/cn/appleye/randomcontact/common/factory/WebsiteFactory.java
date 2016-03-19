package cn.appleye.randomcontact.common.factory;

public class WebsiteFactory implements IFactory{
	public static String[] sSample = {
			"www.baidu.com", "www.google.com", "www.yahoo.com", "www.douban.com", "www.youtube.com",
			"www.qq.com", "www.126.com", "www.163.com", "www.sina.com", "www.139.com", 
			"www.189.com", "www.yahoo.com", "www.souhu.com", "www.hotmail.com",
			"www.msn.com", "www.163.net", "www.ask.com", "www.aol.com", "www.263.net", "www.3721.net",
			"www.yeah.net", "www.live.com.cn"
	};
	
	public static String createRandomWebsite() {
		int index = (int)(Math.random() * sSample.length);
		String website = sSample[index];
		
		return website;
	}

	@Override
	public String createFirstRandomData() {
		return createRandomWebsite();
	}
	
	public String[] createRandomPostals(int count, boolean allowRepeat) {
		String[] websites = new String[count];
		
		for(int i=0; i<count; i++) {
			String website= createRandomWebsite();
			
			websites[i] = website;
			boolean willRepeat = false;
			if(allowRepeat && i<count-1) {
				willRepeat = (int)(Math.random()*2)==1;
			}

			if (willRepeat) {
				i++;
				websites[i] = website;
			}
		}
		
		return websites;
	}

	@Override
	public String[] createFirstRandomData(int count, boolean repeatAllowed) {
		return createRandomPostals(count, repeatAllowed);
	}

	@Override
	public String createSecondRandomData() {
		// TODO Auto-generated method stub
		return null;
	}
}
