package com.wx.utils;


import com.alibaba.fastjson.JSON;
import com.lf.http.HttpClientUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifei on 2016/2/19.
 */
public class WeixinUtils {
    private static String appID="wxea9e5793edab6d0a";
    private static String appsecret="12b5eb4eb189f7ce36eb9e2c89de26b2";
    private static String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret;
    private static String access_token;
    private static Integer expires_in;
    private static long current=System.currentTimeMillis();
    private static String createMenuUrl="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=${ACCESS_TOKEN}";
    private static Map<String,String> locationMap=new HashMap<String,String>();
    private WeixinUtils(){

    }
    private static WeixinUtils instance;
    private static WeixinUtils getInstance() throws IOException {
        if(instance==null||System.currentTimeMillis()-current>expires_in-60){
            String result=HttpClientUtils.get(url);
            HashMap<String,Object> params=JSON.parseObject(result, HashMap.class);
            access_token=params.get("access_token").toString();
            expires_in=Integer.parseInt(params.get("expires_in").toString());
            return new WeixinUtils();
        }
        return instance;
    }

    public static String getAccessToken(){
        if(instance==null){
            try {
                instance=getInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return access_token;
    }
    public static Integer getExpireIn(){
        if(instance==null){
            try {
                instance=getInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return expires_in;
    }

    public static String createMenu(String params){
        String url=createMenuUrl.replace("${ACCESS_TOKEN}",getAccessToken());
        return HttpClientUtils.post(url,params);
    }
    public static String addLocation(String userName,String location){
        locationMap.put(userName,location);
        return "success";
    };

    public static void main(String[] args) throws IOException {
        String params= FileUtils.readFileToString(new File("D:\\myproject\\weixin\\src\\main\\webapp\\menu\\school.json"));
        String result=createMenu(params);
        System.out.println(result);
    }
}
