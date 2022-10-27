package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class TeamTask {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    TimingActivity timingActivity;

    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        util = new Util(mFairy);

    }
    //地下城
    public void dungeons() throws Exception {
        int bj=0,js_1=0;
        while (mFairy.condit()){
            LtLog.e(mFairy.getLineInfo("地下城中bj="+bj));
            Thread.sleep(2000);
         if (bj==0){
             result = mFairy.findPic("entourage.png");
             mFairy.onTap(0.8f, result, 1227,234,1239,245,"随从点击地下城", 1000);

             result = mFairy.findPic("Interface_under_city.png");
             mFairy.onTap(0.8f, result, 192,332,232,369,"地下城界面点击小队模式", 1000);

             result = mFairy.findPic("organize_team.png");
             if (result.sim>0.8f){
                 result1 = mFairy.findPic(62,79,321,688,"retract.png");
                 mFairy.onTap(0.8f, result1, "收起", 3000);
                 if (AtFairyConfig.getOption("fb").equals("1")){
                     mFairy.onTap(0.8f, result, 133,198,159,215,"钢牙洞穴", 2000);
                 }
                 if (AtFairyConfig.getOption("fb").equals("2")){
                     mFairy.onTap(0.8f, result, 148,267,173,285,"暗月洞穴", 2000);
                 }
                 if (AtFairyConfig.getOption("fb").equals("3")){
                     mFairy.onTap(0.8f, result, 161,330,190,347,"幽魂城堡", 2000);
                 }
                 if (AtFairyConfig.getOption("fb").equals("4")){
                     mFairy.onTap(0.8f, result, 167,400,193,413,"疯狂实验室", 2000);
                 }
                 if (AtFairyConfig.getOption("fb").equals("5")){
                     mFairy.onTap(0.8f, result, 169,466,198,483,"残阳废墟", 2000);
                 }
                 if (AtFairyConfig.getOption("fb").equals("6")){
                     mFairy.onTap(0.8f, result, 176,530,206,547,"石锤矿洞", 2000);
                 }
                 if (AtFairyConfig.getOption("fb").equals("7")){
                     mFairy.onTap(0.8f, result, 176,593,205,612,"腐化圣殿", 2000);
                 }
                 if (AtFairyConfig.getOption("fb").equals("8")){
                     mFairy.onTap(0.8f, result, 180,652,198,662,"堕落之船", 2000);
                 }
                 if (AtFairyConfig.getOption("nd").equals("1")){
                     result1 = mFairy.findPic(62,79,321,688,"ordinary.png");
                     mFairy.onTap(0.8f, result1, "普通", 2000);

                 }else {
                     result1 = mFairy.findPic(62,79,321,688,"difficulty.png");
                     mFairy.onTap(0.8f, result1, "困难", 2000);
                 }

                 result = mFairy.findPic("apply.png");
                 if (result.sim<0.8f){
                     LtLog.e(mFairy.getLineInfo("此副本没有解锁"));
                     return;
                 }else {
                     bj=1;
                 }
             }
         }
        if (bj==1){
            result = mFairy.findPic("apply.png");
            mFairy.onTap(0.8f, result, "自动招募或申请", 1000);

            result = mFairy.findPic("recruitment.png");
            if (result.sim>0.8f){
                LtLog.e(mFairy.getLineInfo("招募中"));
                js_1++;
                if (js_1>100){
                    js_1=0;
                    mFairy.onTap(0.8f, result, "招募中", 1000);
                }
            }

            result = mFairy.findPic("crowd_full.png");
            mFairy.onTap(0.8f, result, 1036,614,1120,631,"人满了开始挑战", 1000);

            result = mFairy.findPic("get_into.png");
            if (result.sim>0.8f){
                Thread.sleep(10000);
            }
            mFairy.onTap(0.8f, result, "进入", 1000);

            result = mFairy.findPic("get_ready.png");
            mFairy.onTap(0.8f, result, "准备", 1000);

            result = mFairy.findPic("not_agree.png");
            mFairy.onTap(0.8f, result,715,467,754,487, "未确认同意", 1000);

            result = mFairy.findPic("rewardSure.png");
            mFairy.onTap(0.8f, result, "奖励确定", 1000);



            result = mFairy.findPic("failSure.png");
            mFairy.onTap(0.8f, result, "失败确定", 1000);
            mFairy.onTap(0.8f, result, 1215,43,1237,63,"离开副本", 1000);
            mFairy.onTap(0.8f, result,  704,468,752,487,"离开确定", 1000);
            if (result.sim>0.8f){
                bj=0;
            }

            result = mFairy.findPic("replica.png");
            if (result.sim>0.8f){
                LtLog.e(mFairy.getLineInfo("副本中了往前走"));
                mFairy.touchDown(2,269,571);
                Thread.sleep(5000);
                mFairy.touchUp(2);
            }
            result = mFairy.findPic(1048,4,1277,65,"automatic.png");
            mFairy.onTap(0.96f, result, "开启自动", 1000);

            result = mFairy.findPic("cancellation_departure.png");
            mFairy.onTap(0.8f, result, "离队取消", 1000);

            result = mFairy.findPic("dungeons.png");
            if (result.sim>0.8f){
                LtLog.e(mFairy.getLineInfo("回到主界面"));
                bj=0;
            }

        }


        }
    }




}
