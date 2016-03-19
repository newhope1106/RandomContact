package cn.appleye.randomcontact.common.factory;

import android.content.Context;
import android.graphics.Bitmap;
import cn.appleye.randomcontact.R;
import cn.appleye.randomcontact.utils.BitmapUtil;

public class PhotoFactory implements IFactory{
	public static final int[] sDrawableRes = new int[]{
			R.drawable.default_avator_1,
			R.drawable.default_avator_2,
			R.drawable.default_avator_3,
			R.drawable.default_avator_4,
			R.drawable.default_avator_5,
			R.drawable.default_avator_6,
			R.drawable.default_avator_7,
			R.drawable.default_avator_8
	};

	@Override
	public String createFirstRandomData() {
		
		return null;
	}

	@Override
	public String[] createFirstRandomData(int count, boolean repeatAllowed) {
		
		return null;
	}

	@Override
	public String createSecondRandomData() {

		return null;
	}
	
	public byte[] createRandomPhoto(Context context) {
		int index = (int)(Math.random() * sDrawableRes.length);
		int resId = sDrawableRes[index];
		
		Bitmap tmp = BitmapUtil.readBitMap(context, resId);
		
		return BitmapUtil.Bitmap2Bytes(tmp);
	}
}
