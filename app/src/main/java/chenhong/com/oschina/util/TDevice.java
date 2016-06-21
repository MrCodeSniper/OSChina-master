package chenhong.com.oschina.util;

import android.content.Context;
import android.net.ConnectivityManager;

import chenhong.com.oschina.global.AppContext;

/**
 * Created by Administrator on 2016/6/21.
 */
public class TDevice {

    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) AppContext.getmcontext().getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }
}
