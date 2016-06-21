package chenhong.com.oschina.widget;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/**
 *设置自己的tabhost因为我们当中有不跳转的tab
 * 
 */

public class MyFragmentTabHost extends FragmentTabHost {
	
	private String mCurrentTag;
	
	private String mNoTabChangedTag;
	
	public MyFragmentTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public void onTabChanged(String tag) {//跳转到的tab在new tab时设置的tag
		if (tag.equals(mNoTabChangedTag)) {//如果跳转到tag名与设置了的不跳转fragment的tab的tag名相同则
			setCurrentTabByTag(mCurrentTag);//设置当前tab也就是上次选中的tab，效果就是不跳转到设置了notab的地方
		} else {
			super.onTabChanged(tag);//如果不是按父类来不影响
			mCurrentTag = tag;//设置当前的tab
		}
	}
	
	public void setNoTabChangedTag(String tag) {
		this.mNoTabChangedTag = tag;
	}
}
