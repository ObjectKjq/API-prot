package com.kjq.client.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.kjq.client.utils.SinUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过API开放平台调用第三方接口的api
 */
public class KApiClient {

    /**
     * 表示API接口的地址
     */
    private static final String GATEWAY_HOST = "http://localhost:8090";

    private final String accessKey;

    private final String secretKey;

    public KApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    //调用get接口
    public String getNameByGet(String name, String url){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + url, hashMap);
        System.out.println(result);
        return result;
    }

    //调用post接口
    public String getNameByPost(String name, String url){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST+url, hashMap);
        System.out.println(result);
        return result;
    }

    //通过restful调用post
    public String getUserNameByPost(String params, String url){
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+url)
                .addHeaders(getHeaderMap(params))
                .body(params)
                .execute();
        //返回响应参数
        return httpResponse.body();
    }

    //添加请求头
    private Map<String, String> getHeaderMap(String body){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        //密钥不能发送给后端接口，防止被别人劫获
        //hashMap.put("secretKey", secretKey);
        //后端需要存储随机数
        hashMap.put("nonce", RandomUtil.randomNumbers(3));
        //传入参数
        hashMap.put("body", body);
        //传入时间戳
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        //对参数加密方便验证
        hashMap.put("sign", SinUtils.genSign(hashMap, secretKey));
        return hashMap;
    }
}
