package chenhong.com.oschina.network;

import android.text.TextUtils;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import chenhong.com.oschina.global.AppContext;
import chenhong.com.oschina.mananger.Cachemanager;
import chenhong.com.oschina.network.api.NetWorkApi;
import chenhong.com.oschina.util.IOUtils;
import chenhong.com.oschina.util.StringUtils;
import chenhong.com.oschina.util.TDevice;
import chenhong.com.oschina.util.UIUtils;
import chenhong.com.oschina.util.XmlUtils;

/**
 * Created by Administrator on 2016/6/20.
 */
public abstract class BaseNet<T> {

    protected int mCurrentPage = 0;

    public T getData(String path){
        //如果有缓存就加载缓存，没有就请求网络
//        byte[] result=getCache(path);


//        String key=getCacheKey();
        byte[] result=null;
        if(result==null){
            result=getDataFromServer(path);
        }
        if(result!=null){
            T bean=parseData(result);
            return  bean;
        }
        return null;
    }


    private  boolean isNeedReadCache(boolean refresh){
        String key=getCacheKey();
        if(!TDevice.hasInternet()){
            return  true;
        }
        if(Cachemanager.isExistDataCache(AppContext.getmcontext(),key)&&!refresh){
            return true;
        }






      return false;


    }










    public abstract T parseData(byte[] result) ;


    private byte[] getCache(String path) {
        File cacheDir= UIUtils.getContext().getCacheDir();//得到本应用的缓存文件夹
        Log.d("cache",cacheDir.getAbsolutePath());
        File cacheFile=new File(cacheDir,path);
        FileInputStream in=null;
        if(cacheFile.exists()){
            try {
                ByteArrayOutputStream out=new ByteArrayOutputStream(1024);
                byte[] temp=new byte[1024];
                int size=0;
                while((size=in.read(temp))!=-1)
                {
                    out.write(temp,0,size);
                }
                in.close();
                byte[] bytes=out.toByteArray();
                return bytes;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                IOUtils.close(in);
            }
        }
        return null;
    }


    public byte[] getDataFromServer(String path) {
        byte[] result = HttpRequest.get(NetWorkApi.URL_HOME+path).bytes();
        if(result!=null){
//            setcache(path,result);
            return result;
        }
        return null;
    }

    private void setcache(String path, byte[] result) {
        File cacheDir= UIUtils.getContext().getCacheDir();//得到本应用的缓存文件夹
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        //以path为文件名
        //如果path复杂有特殊符号，用MD5加密
        File cacheFile=new File(cacheDir,path);
        FileOutputStream writer = null;
        try {
            writer=new FileOutputStream(cacheFile);
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(writer);
        }
    }




    public  String getCacheKey(){
        return null;
    }




}
