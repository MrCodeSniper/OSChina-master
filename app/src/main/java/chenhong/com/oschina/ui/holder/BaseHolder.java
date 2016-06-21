package chenhong.com.oschina.ui.holder;

import android.view.View;

/**
 * Created by Administrator on 2016/5/30.
 */
public abstract class BaseHolder<T> {

    private final View mRootView;
    private  T data;
    //初始布局文件
    //绑定控件
      public  abstract View initview();
      public BaseHolder(){
          mRootView = initview();//就是convertview
          mRootView.setTag(this);
    }
    //返回item布局
    public View getmRootView(){
        return  mRootView;
    }

    public  void  setData(T data){
         this.data=data;
        refreashView(data);
    }

    public  T  getData(){
        return  data;
    }


    public abstract void refreashView(T data);



}
