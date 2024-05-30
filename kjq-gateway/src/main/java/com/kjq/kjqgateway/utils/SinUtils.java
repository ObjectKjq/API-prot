package com.kjq.kjqgateway.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Map;

/**
 * 生成签名的工具类
 */
public class SinUtils {
    //生成签名
    public static String genSign(Map<String, String> hashMap, String secretKey){
        //拼接字符串
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        String content = hashMap.toString() + "." + secretKey;
        return sha256.digestHex(content);
    }
}
