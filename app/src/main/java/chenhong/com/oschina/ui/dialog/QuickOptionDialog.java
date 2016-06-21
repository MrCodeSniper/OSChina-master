package chenhong.com.oschina.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import chenhong.com.oschina.R;

/**
 * 自定义对话框
 * Created by Administrator on 2016/6/19.
 */
public class QuickOptionDialog extends Dialog implements View.OnClickListener{


    private ImageView mClose;

    public QuickOptionDialog(Context context) {
        this(context,R.style.quick_option_dialog);
    }

    public QuickOptionDialog(Context context, int themeResId) {
        super(context, themeResId);
        View contentView=getLayoutInflater().inflate(R.layout.dialog_quick_option,null);
        contentView.findViewById(R.id.ly_quick_option_text).setOnClickListener(
                this);
        contentView.findViewById(R.id.ly_quick_option_album)
                .setOnClickListener(this);
        contentView.findViewById(R.id.ly_quick_option_photo)
                .setOnClickListener(this);
        mClose = (ImageView) contentView.findViewById(R.id.iv_close);

        //得到加载的animation对象，让图标旋转
        Animation operatingAnim= AnimationUtils.loadAnimation(getContext(),R.anim.quick_option_close);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        mClose.startAnimation(operatingAnim);
        mClose.setOnClickListener(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置当点击在dialog不是子view的地方也隐藏
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //子view已经setOnClickListener了点击就拦截事件不会传到onTouch里
                QuickOptionDialog.this.dismiss();
                return true;
            }
        });
        //设置dialog的view
        super.setContentView(contentView);
    }

    /**
     * 在oncreat里设置对话框的位置
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);
        //得到窗口的管理者
        WindowManager m=getWindow().getWindowManager();
        Display d=m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();//得到对话框的窗口的参数对象
        p.width=d.getWidth();//将屏幕的宽传到参数再设置到属性里
        getWindow().setAttributes(p);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.iv_close:
                dismiss();
                break;
            default:
                break;
        }
    }
}
