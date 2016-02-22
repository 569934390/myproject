package com.wx.controller;

import com.alibaba.fastjson.JSON;
import com.wx.utils.WeixinUtils;
import com.wx.utils.XmlUtils;
import com.wx.vo.ReceiveXmlEntity;
import me.chanjar.weixin.common.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by lifei on 2016/2/18.
 */
@Controller
public class WeixinMessage {
    private String token="lifei";
    private String appID="wxea9e5793edab6d0a";
    private String appsecret="12b5eb4eb189f7ce36eb9e2c89de26b2";
    private String ngforkUrl="lifei.tunnel.qydev.com";
    @ResponseBody
    @RequestMapping(value = "message",method = RequestMethod.GET)
    public String valid(String signature,String timestamp,String nonce,String echostr) throws NoSuchAlgorithmException {
        String tmpStr=  getSHA1(token, timestamp, nonce);
        if(tmpStr.equals(signature)){
            return echostr;
        }else{
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.GET, produces = {"text/plain;charset=UTF-8"})
    public String valid() throws NoSuchAlgorithmException {
        return "测试";
    }

    @ResponseBody
    @RequestMapping(value = "message",method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    public String message(HttpServletRequest request) throws NoSuchAlgorithmException, IOException {

        String requestXml=IOUtils.toString(request.getInputStream());
        ReceiveXmlEntity xmlEntity= XmlUtils.getMsgEntity(requestXml);
        System.out.println(JSON.toJSONString(xmlEntity));
        if(xmlEntity.getEvent().equals("LOCATION")){
            WeixinUtils.addLocation(xmlEntity.getFromUserName(),xmlEntity.getLatitude()+","+xmlEntity.getLongitude());
            return "";
        }else if(StringUtils.isEmpty(xmlEntity.getEvent())){
            String from=xmlEntity.getFromUserName();
            String to=xmlEntity.getToUserName();
            //回复消息时将from和to反转
            return XmlUtils.formatXmlAnswer(to,from,"测试");
        }else{
            return "";
        }

    }

    /**
     * 用SHA1算法生成安全签名
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @return 安全签名
     * @throws NoSuchAlgorithmException
     */
    public  String getSHA1(String token, String timestamp, String nonce) throws NoSuchAlgorithmException {
        String[] array = new String[] { token, timestamp, nonce };
        StringBuffer sb = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for (int i = 0; i < 3; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }
}
