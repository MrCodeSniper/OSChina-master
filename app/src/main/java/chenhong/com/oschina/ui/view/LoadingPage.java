package chenhong.com.oschina.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import chenhong.com.oschina.R;
import chenhong.com.oschina.util.UIUtils;

/**
 * Created by Administrator on 2016/6/14.
 */
public abstract class LoadingPage extends FrameLayout {

    private static final int STATE_LOAD_UNDO=0;//未加载
    private static final int STATE_LOAD_LOADING=1;//加载中
    private static final int STATE_LOAD_ERROR=2;//加载失败
    private static final int STATE_LOAD_EMPTY=3;//数据为空
    private static final int STATE_LOAD_NONET=4;//网络为空
    private static final int STATE_LOAD_SUCCESS=5;//加载成功
    private  int mCurrentState=STATE_LOAD_UNDO;
    private View mLoadingPage;
    private View mErrorPage;
    private View mEmptyPage;
    private View mNonetPage;
    private View mSuccessPage;
    private Context mcontext;

    public LoadingPage(Context context) {
        super(context);
        this.mcontext=context;
        initview();
    }



    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }




    private void initview(){//加载显示的布局
        //正在加载
        if(mLoadingPage==null){//空就加
            mLoadingPage =  UIUtils.inflate(R.layout.page_loading);
            addView(mLoadingPage);//将布局添加给帧布局
        }
        //加载错误
        if(mErrorPage==null){
            mErrorPage = UIUtils.inflate(R.layout.page_error);
            ImageView iv_error= (ImageView) mErrorPage.findViewById(R.id.iv_error);
            iv_error.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //重新加载数据   点击屏幕
                    loadData();
                }
            });
            addView(mErrorPage);
        }
        //加载无数据
        if(mEmptyPage==null){
            mEmptyPage = UIUtils.inflate(R.layout.page_empty);
            addView(mEmptyPage);
        }
        //加载无网络
        if(mNonetPage==null){
            mNonetPage =  UIUtils.inflate(R.layout.page_nonet);
            addView(mNonetPage);
        }
        showCurrentPage();
    }

    public void loadData(){
    if(mCurrentState!=STATE_LOAD_LOADING){//不在加载的情况下才能加载
      mCurrentState=STATE_LOAD_LOADING;
        new Thread(){
            @Override
            public void run() {
                super.run();
                final ResultState resultState = onLoadingData();//加载各自的数据
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (resultState != null) {
                            mCurrentState = resultState.getState();
                            showCurrentPage();
                        }
                    }
                });
            }
        }.start();
    }
    }



    private void showCurrentPage() {
        mLoadingPage.setVisibility(mCurrentState==STATE_LOAD_UNDO||mCurrentState==STATE_LOAD_LOADING?VISIBLE:GONE);
        mErrorPage.setVisibility(mCurrentState == STATE_LOAD_ERROR ? VISIBLE : GONE);
        mEmptyPage.setVisibility(mCurrentState == STATE_LOAD_EMPTY ? VISIBLE : GONE);
        mNonetPage.setVisibility(mCurrentState == STATE_LOAD_NONET ? VISIBLE : GONE);

        if(mCurrentState==STATE_LOAD_SUCCESS&&mSuccessPage==null){
            mSuccessPage=onCreatSuccessView();

            if(mSuccessPage!=null){
                addView(mSuccessPage);
            }
        }
        if(mSuccessPage!=null){
            mSuccessPage.setVisibility(mCurrentState == STATE_LOAD_SUCCESS ? VISIBLE : GONE);
        }
    }

    public abstract View onCreatSuccessView();
    public abstract ResultState onLoadingData();


    //返回数据的结果
    public enum ResultState{
        STATE_NONET(STATE_LOAD_NONET),
        STATE_ERROR(STATE_LOAD_ERROR),
        STATE_EMPTY(STATE_LOAD_EMPTY),
        STATE_SUCCESS(STATE_LOAD_SUCCESS);

        private int state;
        private ResultState(int state) {
            this.state = state;
        }
        public int getState() {
            return state;
        }
    }

}
