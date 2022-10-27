package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class TimingActivity {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    int xshd_1 = 0, xshd_2 = 0, xshd_3 = 0;

    public TimingActivity(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);
    }


}
