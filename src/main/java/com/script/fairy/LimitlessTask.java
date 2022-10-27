package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;


/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class LimitlessTask {
    AtFairyImpl mFairy;
    FindResult result;
    TimingActivity timingActivity;
    Util util;

    public LimitlessTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);
        timingActivity = new TimingActivity(mFairy);
    }


}
