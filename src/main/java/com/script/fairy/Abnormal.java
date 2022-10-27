package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal  {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    public Abnormal(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
    }
    int js_1=0,js_2=0;
    //全局处理
    public void erro() throws Exception {
        Thread.sleep(5000);
        LtLog.e(mFairy.getLineInfo("异常运行中"));

    }


}
