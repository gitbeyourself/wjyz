package com.script.fairy;

import android.graphics.Bitmap;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.script.opencvapi.ScreenInfo;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Administrator on 2019-03-07.
 */


public class Answer {

    AtFairyImpl mFairy;
    TessBaseAPI tessBaseAPI;
    private TensorFlowInferenceInterface tensorFlowInferenceInterface;

    public Answer(AtFairyImpl ypFairy)throws Exception {
        mFairy = ypFairy;
        tensorFlowInferenceInterface = new TensorFlowInferenceInterface(mFairy.getContext().getAssets(), "tf_cnn_lstm_ctc.pb");
       tessBaseAPI = new TessBaseAPI();
        tessBaseAPI.setDebug(true);
        tessBaseAPI.init("/sdcard/yunpai_files/", "eng"); //eng为识别语言*/
        //ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
       tessBaseAPI.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "0123456789,/"); // 识别白名单
        tessBaseAPI.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_+=-[]}{;:'\"\\|~`.<>?"); // 识别黑名单
        tessBaseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_AUTO_OSD);
    }

    public  void Laojun() throws Exception {
        Thread.sleep(2000);
        String host, token1;
        String answerhui = null;
        String answer = null;
        try {
            answer = getAnswer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LtLog.i(mFairy.getLineInfo("") + "识别题目answer:" + answer);
        //判断A答案
        String answerA = getNumber(363, 393, 167, 35, new Scalar(0, 0, 100), new Scalar(100, 100, 150));
        String answerB = getNumber(702, 393, 156, 36, new Scalar(0, 0, 100), new Scalar(100, 100, 150));
        String answerC = getNumber(362, 469, 148, 38, new Scalar(0, 0, 100), new Scalar(100, 100, 150));
        String answerD = getNumber(702, 471, 149, 33, new Scalar(0, 0, 100), new Scalar(100, 100, 150));
        answerA = answerA.replace(" ", "");
        answerB = answerB.replace(" ", "");
        answerC = answerC.replace(" ", "");
        answerD = answerD.replace(" ", "");
        LtLog.i(mFairy.getLineInfo("") + "识别题目answerA:" + answerA);
        LtLog.i(mFairy.getLineInfo("") + "识别题目answerB:" + answerB);
        LtLog.i(mFairy.getLineInfo("") + "识别题目answerC:" + answerC);
        LtLog.i(mFairy.getLineInfo("") + "识别题目answerD:" + answerD);
        if (answer.equals(answerA)) {
            LtLog.i(mFairy.getLineInfo("") + "识别题目answerA:" + answerA);
            mFairy.onTap(410,402,452,419, "识别题目A", 1000);
            return;
        }
        if (answer.equals(answerB)) {
            //答案选择B
            LtLog.i(mFairy.getLineInfo("") + "识别题目answerB:" + answerB);
            mFairy.onTap(750,404,802,418, "识别题目B", 1000);
            return;
        }
        if (answer.equals(answerC)) {
            //答案选择C
            LtLog.i(mFairy.getLineInfo("") + "识别题目answerC:" + answerC);
            mFairy.onTap(432,477,490,493, "识别题目C", 1000);
            return;
        }
        if (answer.equals(answerD)) {
            //答案选择D
            LtLog.i(mFairy.getLineInfo("") + "识别题目answerD:" + answerD);
            mFairy.onTap(759,480,803,497, "识别题目D", 1000);
            return;
        }

        Mat mat3, mat2;
        mat3 = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1);
        //将图片存入路径
        //Mat转byte[]
        Imgcodecs.imwrite("/sdcard/screen.png", mat3);
        token1 = httpGet("http://api.padyun.com/ws/serverTools.php?act=getUpToken");

        //开始截图
        mat2 = mFairy.getScreenMat(314, 257, 657, 262, 1, 0, 0, 1);
        //将图片存入路径
        //Mat转byte[]
        Imgcodecs.imwrite("/sdcard/yunpai_files/111.png", mat2);
//            byte[] grayData = new byte[mat2.cols() * mat2.rows()];
//            mat2.get(0, 0, grayData);
//            LtLog.i(publicFunction.getLineInfo() + "-------" + grayData[1] + "-------------response....." + grayData.length);
//            String byte64 = Bypass(grayData);
//            LtLog.i(publicFunction.getLineInfo() + "-------"+ byte64+ "-------------response.....");
        //System.out.println("字节是" + byte64);
        //这里获取好爱HOST
        LtLog.i(mFairy.getLineInfo("开始获取好爱的HOST"));
        try {
            host = getHtml("http://3.haoi23.net/svlist.html");
            if (host == null) {
                host = getHtml("http://3.haoi23.net/svlist.html");
            }
            String str = host;
            host = str.substring(3, 23);
            System.out.println(host);
            LtLog.i(mFairy.getLineInfo("获取完成"));
            LtLog.i(mFairy.getLineInfo("请求数据"));
            String a = String.valueOf((int) (1 + Math.random() * 9));
            String b = String.valueOf((int) (1 + Math.random() * 9));
            String c = String.valueOf((int) (1 + Math.random() * 9));
            String d = String.valueOf((int) (1 + Math.random() * 9));
            String e = String.valueOf((int) (1 + Math.random() * 9));
            String f = String.valueOf((int) (1 + Math.random() * 9));
            String g = String.valueOf((int) (1 + Math.random() * 9));
            String h = String.valueOf((int) (1 + Math.random() * 9));
            String ii = String.valueOf((int) (1 + Math.random() * 9));
            String j = String.valueOf((int) (1 + Math.random() * 9));
            String suiji = a + b + c + d + e + f + g + h + ii + j;
            LtLog.i(mFairy.getLineInfo("得到的随机数" + suiji));
            String fanhui = httpPost(host, suiji);
            LtLog.i(mFairy.getLineInfo("请求完成,开始请求TID,TID为" + fanhui));

            a = String.valueOf((int) (1 + Math.random() * 9));
            b = String.valueOf((int) (1 + Math.random() * 9));
            c = String.valueOf((int) (1 + Math.random() * 9));
            d = String.valueOf((int) (1 + Math.random() * 9));
            e = String.valueOf((int) (1 + Math.random() * 9));
            f = String.valueOf((int) (1 + Math.random() * 9));
            g = String.valueOf((int) (1 + Math.random() * 9));
            h = String.valueOf((int) (1 + Math.random() * 9));
            ii = String.valueOf((int) (1 + Math.random() * 9));
            j = String.valueOf((int) (1 + Math.random() * 9));
            suiji = a + b + c + d + e + f + g + h + ii + j;
            for (int i = 0; i < 15; i++) {
                answerhui = TIDhttpPost(host, fanhui, suiji);
                Thread.sleep(100);
                if (answerhui == "#编号不存在" || answerhui == "#超时") {
                    return;
                }
                LtLog.i(mFairy.getLineInfo("") + "答案是" + answerhui);
                if (answerhui.equals("1")) {
                    LtLog.i(mFairy.getLineInfo("") + "答案是A");
                    mFairy.onTap(467, 412, 468, 413, "A", 1000);
                    sendTongTask(token1, "1");
                    break;
                }
                if (answerhui.equals("2")) {
                    LtLog.i(mFairy.getLineInfo("") + "答案是B");
                    mFairy.onTap(827, 417, 828, 418, "B", 1000);
                    sendTongTask(token1, "2");
                    break;
                }
                LtLog.i(mFairy.getLineInfo("") + "答案是=" + answerhui);
                if (answerhui.equals("3")) {
                    LtLog.i(mFairy.getLineInfo("") + "答案是c");
                    mFairy.onTap(488, 492, 489, 493, "C", 1000);
                    sendTongTask(token1, "3");
                    break;
                }
                if (answerhui.equals("4")) {
                    LtLog.i(mFairy.getLineInfo("") + "答案是d");
                    mFairy.onTap(827, 492, 828, 493, "d", 1000);
                    sendTongTask(token1, "4");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            String html = new String(data, "UTF-8");
            return html;
        }
        return null;
    }

    public void sendTongTask(String mToken, String answer) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd|HHmmss");
        String date = sDateFormat.format(new java.util.Date());
        LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------....." + date.split("\\|"));
        String[] dataTime = date.split("\\|");
        String keyStr = dataTime[0] + "_"+ AtFairyConfig.getGameID()+"_" + answer + "_" + dataTime[1] + ".png";
        LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------....." + keyStr);
        String filePath = "/sdcard/screen.png";
        httpPost(filePath, keyStr, mToken);
        fileDelete(filePath);
        return;
    }

    public boolean fileDelete(String strFile) {
        try {
            File f = new File(strFile);
            if (f.exists()) {
                f.delete();
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public String httpPost(String filePath, String key, String mToken)throws Exception {

        LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------httpPost....." + mToken);
        try {
            JSONObject UrlJson = new JSONObject(mToken);
            LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------httpPost....." + UrlJson.optString("data"));
            OkHttpClient client = new OkHttpClient();
            File file = new File(filePath);
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", "head_image", body)
                    .addFormDataPart("token", UrlJson.optString("data"))
                    .addFormDataPart("key", key)
                    .build();
            Request request = new Request.Builder()
                    .url("http://up-z2.qiniu.com/")
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------response....." + response.body().string());
                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //请求TID
    public String TIDhttpPost(String host, String TID, String suiji) {
        System.out.println(TID);
        String answer1 = null;
        int js = 0;
        js++;
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", TID)
                .addFormDataPart("r", suiji)
                .build();
        Request request = new Request.Builder()
                .url("http://" + host + "/GetAnswer.aspx")
                .post(requestBody)
                .build();
        try {
            //for (int i = 0; i <10 ; i++) {
            Response response = client.newCall(request).execute();
            //LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------response=" + response.body().string() );
            String result = response.body().string();

            answer1 = response.toString();
            try {
                //  LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------response=" + result);
                Thread.sleep(3000);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            //}
//            return response.toString();

        } catch (IOException e) {
            // LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------response.....");
            e.printStackTrace();
        }
        return "error";

    }


    public String getAnswer() throws Exception {
        int[] inputs1 = new int[]{189};
        float[] inputs3 = new float[189 * 34];
        int[] output = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int k = 0;
        String mStr = "";
        Mat mat = mFairy.getScreenMat(546, 280, 189, 34, 1, 0, 0, 1);
        Core.inRange(mat, new Scalar(209, 209, 209), new Scalar(255, 255, 255), mat);
        for (int i = 0; i < 189; i++) {
            for (int j = 0; j < 34; j++) {
                inputs3[k] = (float) mat.get(j, i)[0];
                k = k + 1;
            }
        }
        tensorFlowInferenceInterface.feed("inputs", inputs3, 1, 189, 34);
        tensorFlowInferenceInterface.feed("seq_len", inputs1, 1);
        tensorFlowInferenceInterface.run(new String[]{"output"});
        tensorFlowInferenceInterface.fetch("output", output);
        for (int i = 0; i < output.length; i++) {
//            System.out.println("==========" + output[i]);
            if (output[i] != -1) {
                mStr = mStr + output[i];
            } else {
                break;
            }
        }
        mat.release();
        System.out.println("==========" + mStr);
        return mStr;
    }

    public String getNumber(int x, int y, int width, int height, Scalar minValue, Scalar maxValue)throws Exception {
        ScreenInfo screenInfo = mFairy.captureInterval();
        if(screenInfo.height>720){
            System.out.println("error Screen height >720");
            return null;
        }
        Mat mat=mFairy.getScreenMat(x,y,width,height,1, 0, 0, 1);
//        mat= Imgcodecs.imread("/sdcard/20181010_16_1_000742.png");

        Scalar minValues = minValue;
        Scalar maxValues = maxValue;
        Core.inRange(mat, minValues, maxValues, mat);
//        Imgcodecs.imwrite("/sdcard/test.png", mat);
        Bitmap bitmap = Bitmap.createBitmap(mat.width(), mat.height(), Bitmap.Config.ARGB_8888);
        org.opencv.android.Utils.matToBitmap(mat, bitmap);
        mat.release();


        tessBaseAPI.setImage(bitmap);
        String number = tessBaseAPI.getUTF8Text();
        System.out.println("number=" + number);
        tessBaseAPI.clear();
        return number;
    }

    public static String httpGet(String url) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String httpPost(String host, String suiji)throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userstr", "yunpai|ACMXGAHOAZNDCEED")
                .addFormDataPart("gameid", "5001")
                .addFormDataPart("timeout", "60")
                .addFormDataPart("rebate", "3739|6A1962CC9E02B5B9")
                .addFormDataPart("daiLi", "haoi")
                .addFormDataPart("kou", "0")
                .addFormDataPart("beizhu", "2222")
                .addFormDataPart("ver", "web2")
                .addFormDataPart("key", suiji)
                .addFormDataPart("img", GetImageStr("/sdcard/yunpai_files/111.png"))
                .build();
        Request request = new Request.Builder()
                .url("http://" + host + "/UploadBase64.aspx")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
//            LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------response....." + response.body().string() + "---" + response.toString());
            String str = response.body().string();

            return str;
        } catch (IOException e) {
            LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------response.....");
            e.printStackTrace();
        }
        return "error";
    }

    public String GetImageStr(String imgpath) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgpath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码

        // LtLog.i(publicFunction.getLineInfo() + "-------" + new String(android.util.Base64.encode(data, android.util.Base64.DEFAULT)) + "-------------response.....");
        return new String(android.util.Base64.encode(data, android.util.Base64.DEFAULT));//返回Base64编码过的字节数组字符串
    }
}
