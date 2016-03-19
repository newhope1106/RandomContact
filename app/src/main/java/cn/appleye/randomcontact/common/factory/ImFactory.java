package cn.appleye.randomcontact.common.factory;

public class ImFactory implements IFactory{
	
	public static final String createRandomIm() {
		int bitCount = (int)(Math.random()*5) + 5;
		
		String Im = "";
		for (int i=0; i<bitCount; i++) {
			if (i==0) {
				Im += (int)(Math.random()*9) + 1;
			} else{
				Im += (int)(Math.random()*10);
			}
		}
		
		return Im;
	}
	
	public String[] createRandomPostals(int count, boolean allowRepeat) {
		String[] ims = new String[count];
		
		for(int i=0; i<count; i++) {
			String im= createRandomIm();
			
			ims[i] = im;
			boolean willRepeat = false;
			if(allowRepeat && i<count-1) {
				willRepeat = (int)(Math.random()*2)==1;
			}
			
			if (willRepeat) {
				i++;
				ims[i] = im;
			}
		}
		
		return ims;
	}

	@Override
	public String createFirstRandomData() {
		return createRandomIm();
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
