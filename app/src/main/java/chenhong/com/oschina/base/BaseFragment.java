package chenhong.com.oschina.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import chenhong.com.oschina.ui.view.LoadingPage;

/**
 * Created by Administrator on 2016/6/14.
 */
public abstract class BaseFragment extends Fragment{

   private LoadingPage mloadingPage;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mloadingPage = new LoadingPage(getActivity()) {
            @Override
            public View onCreatSuccessView() {
                //调用自己的抽象方法不然会栈溢出，程序崩溃
                return   BaseFragment.this.onCreatSuccessView();//继续由子类实现各自的特色界面
            }
            @Override
            public ResultState onLoadingData() {
                return BaseFragment.this.onLoadingData();
            }
        };
       LoadData();
        return mloadingPage;
    }

    public void LoadData(){
        //如果有视图了就加载数据
        if(mloadingPage!=null){
            mloadingPage.loadData();
        }
    }

    //对网络返回数据的合法性进行校验
    public LoadingPage.ResultState check(Object object){
        if(object!=null){
            if(object instanceof List){
                List list= (List) object;
                if(list.size()==0){
                    return LoadingPage.ResultState.STATE_EMPTY;
                }else {
                    return LoadingPage.ResultState.STATE_SUCCESS;
                }
            }
        }
        //暂时显示不出无网络
        return LoadingPage.ResultState.STATE_ERROR;
    }

    public abstract LoadingPage.ResultState onLoadingData();
    public abstract View onCreatSuccessView();
}
