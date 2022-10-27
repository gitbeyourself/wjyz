package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/25 0025.
 */

public class SingleTask {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    List<String> list = new ArrayList<>();
    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);

    }


    public void novice() throws Exception {
        while (mFairy.condit()){
            LtLog.e(mFairy.getLineInfo("新手引导中"));
            Thread.sleep(2000);
            result = mFairy.findPic(45,184,229,494,  new String[]{"main.png","main1.png"});
            mFairy.onTap(0.8f, result, "左侧主线", 1000);

            result = mFairy.findPic("want_upgrade.png");
            if (result.sim>0.8f){
                LtLog.e(mFairy.getLineInfo("我要升级"));
                break;
            }

            result = mFairy.findPic("skip.png");
            mFairy.onTap(0.8f, result, "跳过剧情", 1000);


            result = mFairy.findPic("skip.png");
            mFairy.onTap(0.8f, result, "跳过剧情", 1000);

            result = mFairy.findPic("equipment.png");
            mFairy.onTap(0.8f, result, "装备", 1000);

            result = mFairy.findPic(1048,4,1277,65,"automatic.png");
            mFairy.onTap(0.96f, result, "开启自动", 1000);

            result = mFairy.findPic("mountSure.png");
            mFairy.onTap(0.8f, result, "坐骑确定", 2000);
            mFairy.onTap(0.8f, result, 1001,658,1015,671,"打开坐骑栏", 2000);
            mFairy.onTap(0.8f, result, 1025,597,1053,617,"打开坐骑栏", 2000);
            mFairy.onTap(0.8f, result,  1123,61,1138,75,"打开坐骑栏", 2000);

            result = mFairy.findPic("rewardSure.png");
            mFairy.onTap(0.8f, result, "奖励确定", 1000);

            result = mFairy.findPic("bindingSure.png");
            mFairy.onTap(0.8f, result, "绑定确定", 1000);

            result = mFairy.findPic("failSure.png");
            mFairy.onTap(0.8f, result, "失败确定", 1000);
            if (result.sim>0.8f){
                break;
            }

        }
    }

}
