package cn.appleye.randomcontact.common.factory;

public class ImFactory implements IFactory{

	private int mMinCount;
	private int mMaxCount;

	public ImFactory() {
		this(1, 1);
	}

	public ImFactory(int minCount, int maxCount) {
		mMinCount = minCount;
		mMaxCount = maxCount;
	}
	
	public static final String createRandomIm() {
		int bitCount = (int)(Math.random()*5) + 6;
		
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
	public String[] createFirstRandomData(boolean repeatAllowed) {
		int count = mMinCount + (int)((mMaxCount - mMinCount + 1)*Math.random());
		return createRandomPostals(count, repeatAllowed);
	}

	@Override
	public String createSecondRandomData() {
		return null;
	}
}
