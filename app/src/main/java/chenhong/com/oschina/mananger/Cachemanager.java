package chenhong.com.oschina.mananger;

import android.content.Context;

import java.io.File;

/**
 * Created by Administrator on 2016/6/21.
 */
public class Cachemanager {



    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    public static boolean isExistDataCache(Context context, String cachefile) {
        if (context == null)
            return false;
        boolean exist = false;
        File data = context.getFileStreamPath(cachefile);//保存在特定的地址data/data/包名/files
        if (data.exists())
            exist = true;
        return exist;
    }






}
