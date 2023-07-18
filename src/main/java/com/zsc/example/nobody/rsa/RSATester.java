package com.zsc.example.nobody.rsa;


import java.util.Map;

public class RSATester {

//    static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv+VGsIXUjXm16VgIhPZ6iT0Id16vJTn6aRvTEgVj+s04v5w8CNvaNYC97E5BGPfHhU/iaBNyfw6kl6KszYHJ1OvkJXgprNt40TkfKKeUNUCsujq8dQH/h6t2Fhm9dmklyro7ZFsmmS4ecR1fIpS1HpHAOD8z4hkwAhHwn5CBoWicH4f5c9oHzqFZLnS6kxPQKLZzTzWdoSLFGYHMMM++mZ4s5Y2oTVdFxo4vLepN1s259S9PDHmGO2mkoRE/PJ6k8rI5AKYstiqnnBXkWuqeZLds/jOkW866Qp1wWkiBMouan639HWwqQQT4SFf5uVpMYiRyRKcdHzyaqUsiPxQMYQIDAQAB";
//    static String privateKey = "MIIEpAIBAAKCAQEAv+VGsIXUjXm16VgIhPZ6iT0Id16vJTn6aRvTEgVj+s04v5w8CNvaNYC97E5BGPfHhU/iaBNyfw6kl6KszYHJ1OvkJXgprNt40TkfKKeUNUCsujq8dQH/h6t2Fhm9dmklyro7ZFsmmS4ecR1fIpS1HpHAOD8z4hkwAhHwn5CBoWicH4f5c9oHzqFZLnS6kxPQKLZzTzWdoSLFGYHMMM++mZ4s5Y2oTVdFxo4vLepN1s259S9PDHmGO2mkoRE/PJ6k8rI5AKYstiqnnBXkWuqeZLds/jOkW866Qp1wWkiBMouan639HWwqQQT4SFf5uVpMYiRyRKcdHzyaqUsiPxQMYQIDAQABAoIBAQCdBQT0yG2GXkMl7uz5r53Q0motEwMJ2Qk4HJZ3toV64ZY/452UOiGxiejehaHqxkUypo1CUDduFBNGKMxxVHRMj8Edj9e6I9QWj0EZyRYTBRrRP+KZc8sdvoVfzCwlyiAsZHrYwWUyDnTGZhxEdZ5Gp0i8Pas8pRONfhSA3IPWCBBqRkV7VP+J5FlZtkOWBOETLtb00BzT+Wu/kwIU5dfapVEAFhV0BJGvZmlJJIBLVF423+YKvx917TPOsi1mecZ7ZYrhcy/vf1gRcfs7kbWwUrGO8TTZenLe9oGaooS1cVklnebznimf8ZBroQNMO8G67KNvtoeOiJ1GszyWGxuBAoGBAPcRXh9RgfkKH6NwEYo9SlKYeNGiGMVdc/tc6PnLIyp0gUpCWAPU4ciPTwuehcV3xyLBFPcmgW3XFUQPPJiLIsjbVi5yEU/iby6AxCXXipKas6ec756/DXid/8Jgd0F+sIcTL2YZ2hBVMWdbuj607aofEN4eOzcnSvgU0RJGPoZZAoGBAMbVSPztcqwZL1ip7cQhdsY64EFaIRrPkEZzzWlnDxPoofiZkiHgvODE0+C4Lly9S7BYkwBriNro7HF4KrGX8qpj7mUrJwLBpw/29jez/yQxUWmoZrp9DPefjJO+PV579rujrj1CWbd5rdnwTeEk/Hf902+oTqrCPN8Ck85foAVJAoGAK6TkxJGIbS7Nofh1EL989pQ6m4WP7KVyjI/AFAg/YQuNn0mS9LJRZDV0mC/bn6NmCQykoHgr2vRorEoowSWq8mJUmgXKaxsr2sIiZDDsKLOtUsmVRqynO4xllreluWsg0eugrt+YNtRl0sVQZC1f4nJ0b1hVy4tkYeiDej/wYZkCgYEAhqdk0Z2A/lnj/Zi/JAdBp8exnbCLINwWqg+8ubgItwPhxTlDhNvtLg8+Kj6xby7nNlsV2r/4o8AAeGvYBgOQXKJmjW8m8S/yktY+MsUccgdQSky4nW0Yvg34Jwo8B2POWY7k7imkFqQ5/5FFPMdOj3h/m2uRMwVuMd1N+gI6nbkCgYB8V1IAfD6qh8yJOWYKNPLY5fD3897Za9kiajjbwa6dF7Tqa7a2uVtYxI+zlkAwskUHiKnw/+52UQ9P89hgQ+r+WIAZlLg1AYgAp07ynyNvcn9FuLNpYXOL5RldJUlcVvwU1+GS8wPmb8JIbmK74ZbGvLv2SWoCBMMv/DUNWWfDcg==";
static String publicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA50cZ2JL4DuAS3UXbRUe2\n" +
        "zn3EVdnEE2yjEwvEI1WPeQ9MlEL62VoX61Oldy5R1xZ+wp5JAIgF2PCVhUCNIQR7\n" +
        "qZB5dRjtWIaNRsyrRctf3dOyZTGc3ezmwZUpARBROumay/Wm62/opw00/uRBrE3+\n" +
        "TTF5YqtaFyEopkU3w3P7xd6C1f6k2LRQ/r12mil1ypSIh9LHiLEDx27B/EcOeKE/\n" +
        "wuLs5EN/c+Yo8mzleB0g4K0Sj/wpO17JAakMUqzTDZYhQaGPx3BXzXz+7xc5/v3w\n" +
        "C/ABUTsMHsJrJkLLjAXuLOvbgYOawvpfQ2KcztvoMQk2Dy21Npx1AEPbZ8UWujrA\n" +
        "KQIDAQAB";
    static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDnRxnYkvgO4BLd\n" +
            "RdtFR7bOfcRV2cQTbKMTC8QjVY95D0yUQvrZWhfrU6V3LlHXFn7CnkkAiAXY8JWF\n" +
            "QI0hBHupkHl1GO1Yho1GzKtFy1/d07JlMZzd7ObBlSkBEFE66ZrL9abrb+inDTT+\n" +
            "5EGsTf5NMXliq1oXISimRTfDc/vF3oLV/qTYtFD+vXaaKXXKlIiH0seIsQPHbsH8\n" +
            "Rw54oT/C4uzkQ39z5ijybOV4HSDgrRKP/Ck7XskBqQxSrNMNliFBoY/HcFfNfP7v\n" +
            "Fzn+/fAL8AFROwwewmsmQsuMBe4s69uBg5rC+l9DYpzO2+gxCTYPLbU2nHUAQ9tn\n" +
            "xRa6OsApAgMBAAECggEAC/WsS3vGIhbKQvEC1sFPnbv5NCtPrWV3xVtdaCpbgfod\n" +
            "jqksCaNUC1JHAzO8/a9nHK9MvR9Llh1SzhI5ehSeRHV5ImoIlARZN+kLjk9nBLSW\n" +
            "zA7VutAuWqJY/XoXiUFdRODOIeymjkTrkabCEqPx25IMYeLUhM+tJ3P63zuXMHMp\n" +
            "BU+J0DgiKcsBvGDMuzzpFQw1Uzo2+CA3UxO4i7FoIAJymRhHaFwHNWFYBN8VhMUd\n" +
            "AQJSub3Fg174wBFK/7yDbkrxNbKoTTTzPdxRfFyn4r112SgrFJX2oYT3SCev6u6p\n" +
            "txswwZ2qkS5FyB0cuCJuUIh0NgOuBlSOjgVm5bKyEQKBgQD/EgBv9GWe/olO4GYN\n" +
            "ac0DT0mHy3zdQs1GOUlmltFn7ni2g5i3dqIMVhk2h234SpWJFKCqeacgx4t0eiUU\n" +
            "aFGicQ1tNc23T4dan03NsE1w2waBHtNgPma4OccbBIR69GHRPTAMwpEJCv52n2zD\n" +
            "LwXPzoI5WKFUXn2OxGID/K3IhQKBgQDoHuYtDduLtVUMzgrvNiCOXTnUcQUmkWH/\n" +
            "QIhMJaYo2eLNL+DnhCUZGuNAfJLgr0YpT+Xm2AoM6LlYKfUek8eKw7V8L0tN8E5K\n" +
            "SLgsk7iPjZJcRPx23fAKSQxcXsrxjMvcW19stClELZnvd1rWWoh237ImjHuw6ONQ\n" +
            "mR32Leg8VQKBgQDJ8ce026tlQSMW9uT1c7GkEC8oOzxltZDuK0bo2Tl77rtVzUSo\n" +
            "5F0SJUya9ohjjzFrMZCux9hBRJstodMfoCQZ1yYJNBLmMin43q1rz0zQsCgrLGuw\n" +
            "BFexPdVcl6wH6NpVHfuf7p+DqFelNT2YHu+cAmTp4DkCojkIQoVToMc6PQKBgGRh\n" +
            "6H0XfxieJNfSy1vD9rRakMXU0rtpKA4xJEB6D6+e+rxttj+pIDoJLLoYpZRReHAp\n" +
            "Q21fhIkso+sCyiSdGDBkjrnT4ickg1cQt8VfZPN/8AnDM8NcG5D3IkdYHz/y+b6k\n" +
            "LVJfX3FCn022Oix1Hor6FpshKeTJ8nRKo3BxTfu5AoGBALF3RNB3DpFukPz88iQd\n" +
            "TkPGyZdEFTYr4ab4hze8GgRgslQYfdIQiqZsP7/uTf2fo48YUaI1OO93sxlMC8oc\n" +
            "5lmHJSCBbPWVVw/rFg2jiTG7aXHhdqh0KYN9WNIjJYA/2nMff9YfcbOWtWrFpQVl\n" +
            "r9ixcq9aiFyg5paV5ioyMPhc";
    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
//        test();
//        testSign();
        testHttpSign();
    }

    static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        System.out.println("加密后文字：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }

    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }


    static void testHttpSign() throws Exception {
        String param = "id=1&name=张三";
        byte[] encodedData = RSAUtils.encryptByPrivateKey(param.getBytes(), privateKey);
        System.out.println("加密后：" + encodedData);
        
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        System.out.println("解密后：" + new String(decodedData));
        
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名：" + sign);
        
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("签名验证结果：" + status);
    }
    
    
}