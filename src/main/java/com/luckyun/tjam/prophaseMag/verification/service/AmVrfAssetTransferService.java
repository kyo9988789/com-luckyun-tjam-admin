package com.luckyun.tjam.prophaseMag.verification.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtFiassetMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtSpareMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlan;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanParam;

@Service
public class AmVrfAssetTransferService {

    @Autowired
    private AmVrfPlanMapper amVrfPlanMapper;

    @Autowired
    private AmVrfPlanDtFiassetMapper amVrfPlanDtFiassetMapper;

    @Autowired
    private AmVrfPlanService amVrfPlanService;

    @Autowired
    private AmVrfPlanDtSpareMapper amVrfPlanDtSpareMapper;



    public  Integer amvrfAssetTransferSpare(AmVrfPlanParam condition) {
        JSONArray jsonArray = new JSONArray();
        List<AmVrfPlan> amVrfPlanList = amVrfPlanMapper.findAlls();
        for(AmVrfPlan amVrfPlan : amVrfPlanList) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(amVrfPlan);
            List<AmVrfPlanDtSpare> amVrfPlanDtSparetList = amVrfPlanDtSpareMapper.findAllByIbillid(amVrfPlan.getIndocno());//根据这条plan的条件查询对应的子表信息
            System.out.println("看明细表数据======================"+amVrfPlanDtSparetList);
            JSONArray JsonArrayAmVrfPlanDtSpare = new JSONArray();
            for(AmVrfPlanDtSpare amVrfPlanDtSpare :amVrfPlanDtSparetList) {
                System.out.println("看---------"+amVrfPlanDtSpare);
                JSONObject jsonObjectAmVrfPlanDtSpare = (JSONObject) JSONObject.toJSON(amVrfPlanDtSpare);
                System.out.println("看----------======"+jsonObjectAmVrfPlanDtSpare);
                JsonArrayAmVrfPlanDtSpare.add(jsonObjectAmVrfPlanDtSpare);
            }
            jsonObject.put("FamVrfPlanDtSpare", JsonArrayAmVrfPlanDtSpare);
            jsonArray.add(jsonObject);
       }
        sendPost("http://z302b13343.wicp.vip:46823/http/downloadSpare",jsonArray.toJSONString());
        amVrfPlanService.alreadyissued(condition);

        return 1;
    }



    public  Integer amvrfAssetTransfer(AmVrfPlanParam condition) {
        JSONArray jsonArray = new JSONArray();
        List<AmVrfPlan> amVrfPlanList = amVrfPlanMapper.findAlls();
        for(AmVrfPlan amVrfPlan : amVrfPlanList) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(amVrfPlan);
            List<AmVrfPlanDtFiasset> amVrfPlanDtFiassetList = amVrfPlanDtFiassetMapper.findAllByIbillid(amVrfPlan.getIndocno());//根据这条plan的条件查询对应的子表信息
            System.out.println("看明细表数据======================"+amVrfPlanDtFiassetList);
            JSONArray JsonArrayAmVrfPlanDtFiasset = new JSONArray();
            for(AmVrfPlanDtFiasset amVrfPlanDtFiasset :amVrfPlanDtFiassetList) {
                System.out.println("看---------"+amVrfPlanDtFiasset);
                JSONObject jsonObjectAmVrfPlanDtFiasset = (JSONObject) JSONObject.toJSON(amVrfPlanDtFiasset);
                System.out.println("看----------======"+jsonObjectAmVrfPlanDtFiasset);
                JsonArrayAmVrfPlanDtFiasset.add(jsonObjectAmVrfPlanDtFiasset);
            }
            jsonObject.put("FamVrfPlanDtFiasset", JsonArrayAmVrfPlanDtFiasset);
            jsonArray.add(jsonObject);
        }
        sendPost("http://2d7212d525.qicp.vip:16603/http/download",jsonArray.toJSONString());
        amVrfPlanService.alreadyissued(condition);

        return 1;
    }
    /**
     * 调用对方接口方法
     * @param url 对方或第三方提供的路径
     * @param param 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static String sendPost(String url,String param){
        OutputStreamWriter out =null;
        BufferedReader reader = null;
        String response = "";

        //创建连接
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(param);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return response;
    }

}
