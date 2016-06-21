package chenhong.com.oschina.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import chenhong.com.oschina.global.AppContext;


/**
 *封装：用起来更加简单
 * Created by Administrator on 2016/5/29.
 */
public class UIUtils {
    //////////////////加载全局文件///////////////////////
    public static Context getContext(){
        return AppContext.getmcontext();
    }
    public static Handler getHandler(){
        return AppContext.getmhandler();
    }
    public static int getMainThreadId(){
        return AppContext.getmTid();
    }
  ///////////////////加载资源文件///////////////////////

  //获取字符串
  public static String getString(int id){
      return  getContext().getResources().getString(id);
  }
  //获取字符串数组
    public static String[] getStringArray(int id){
        return  getContext().getResources().getStringArray(id);
    }
 //获取图片
    public static Drawable getDrawable(int id){
        return  getContext().getResources().getDrawable(id);
    }
    //获取颜色
    public static int getColor(int id){
        return  getContext().getResources().getColor(id);
    }
    //根据id得到颜色状态选择器
    public static ColorStateList getColorStateList(int mTabTextColorResId) {
       return getContext().getResources().getColorStateList(mTabTextColorResId);
    }
   //获取尺寸
   public static int getDimen(int id){
       //返回具体的像素值
       return  getContext().getResources().getDimensionPixelSize(id);
   }
  ////////////////////////dp和px的转化//////////////////////
  public  static int dptopx(float dp){
      //density 密度 是每英寸像素数量
      //getDisplayMetrics()得到显示的度量
      //px=dp*像素密度
      float density=getContext().getResources().getDisplayMetrics().density;
      return (int) (dp*density+0.5f);
  }
    public  static float pxtodp(float px){
        float density=getContext().getResources().getDisplayMetrics().density;
        return px/density;
    }
 ///////////////////////////加载布局文件///////////////////////////////////
    public static View inflate(int id){
        return View.inflate(getContext(),id,null);
    }
    /////////////////////判断是否运行在主线程///////////////////////////////////
    public static Boolean isRunOnUIThread(){
        int currentTid=android.os.Process.myTid();
        return currentTid==getMainThreadId();
    }
    //运行到主线程
    public static void  RunOnUIThread(Runnable runnable){
      if(isRunOnUIThread()){
          //如果已经在主线程了
          runnable.run();
      }else {
        //用Handler把操作发送到主线程运行
          getHandler().post(runnable);
      }
    }

















}
