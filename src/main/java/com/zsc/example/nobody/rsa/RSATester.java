package com.zsc.example.nobody.rsa;


import java.util.Map;

public class RSATester {

    static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv+VGsIXUjXm16VgIhPZ6iT0Id16vJTn6aRvTEgVj+s04v5w8CNvaNYC97E5BGPfHhU/iaBNyfw6kl6KszYHJ1OvkJXgprNt40TkfKKeUNUCsujq8dQH/h6t2Fhm9dmklyro7ZFsmmS4ecR1fIpS1HpHAOD8z4hkwAhHwn5CBoWicH4f5c9oHzqFZLnS6kxPQKLZzTzWdoSLFGYHMMM++mZ4s5Y2oTVdFxo4vLepN1s259S9PDHmGO2mkoRE/PJ6k8rI5AKYstiqnnBXkWuqeZLds/jOkW866Qp1wWkiBMouan639HWwqQQT4SFf5uVpMYiRyRKcdHzyaqUsiPxQMYQIDAQAB";
    static String privateKey = "MIIEpAIBAAKCAQEAv+VGsIXUjXm16VgIhPZ6iT0Id16vJTn6aRvTEgVj+s04v5w8CNvaNYC97E5BGPfHhU/iaBNyfw6kl6KszYHJ1OvkJXgprNt40TkfKKeUNUCsujq8dQH/h6t2Fhm9dmklyro7ZFsmmS4ecR1fIpS1HpHAOD8z4hkwAhHwn5CBoWicH4f5c9oHzqFZLnS6kxPQKLZzTzWdoSLFGYHMMM++mZ4s5Y2oTVdFxo4vLepN1s259S9PDHmGO2mkoRE/PJ6k8rI5AKYstiqnnBXkWuqeZLds/jOkW866Qp1wWkiBMouan639HWwqQQT4SFf5uVpMYiRyRKcdHzyaqUsiPxQMYQIDAQABAoIBAQCdBQT0yG2GXkMl7uz5r53Q0motEwMJ2Qk4HJZ3toV64ZY/452UOiGxiejehaHqxkUypo1CUDduFBNGKMxxVHRMj8Edj9e6I9QWj0EZyRYTBRrRP+KZc8sdvoVfzCwlyiAsZHrYwWUyDnTGZhxEdZ5Gp0i8Pas8pRONfhSA3IPWCBBqRkV7VP+J5FlZtkOWBOETLtb00BzT+Wu/kwIU5dfapVEAFhV0BJGvZmlJJIBLVF423+YKvx917TPOsi1mecZ7ZYrhcy/vf1gRcfs7kbWwUrGO8TTZenLe9oGaooS1cVklnebznimf8ZBroQNMO8G67KNvtoeOiJ1GszyWGxuBAoGBAPcRXh9RgfkKH6NwEYo9SlKYeNGiGMVdc/tc6PnLIyp0gUpCWAPU4ciPTwuehcV3xyLBFPcmgW3XFUQPPJiLIsjbVi5yEU/iby6AxCXXipKas6ec756/DXid/8Jgd0F+sIcTL2YZ2hBVMWdbuj607aofEN4eOzcnSvgU0RJGPoZZAoGBAMbVSPztcqwZL1ip7cQhdsY64EFaIRrPkEZzzWlnDxPoofiZkiHgvODE0+C4Lly9S7BYkwBriNro7HF4KrGX8qpj7mUrJwLBpw/29jez/yQxUWmoZrp9DPefjJO+PV579rujrj1CWbd5rdnwTeEk/Hf902+oTqrCPN8Ck85foAVJAoGAK6TkxJGIbS7Nofh1EL989pQ6m4WP7KVyjI/AFAg/YQuNn0mS9LJRZDV0mC/bn6NmCQykoHgr2vRorEoowSWq8mJUmgXKaxsr2sIiZDDsKLOtUsmVRqynO4xllreluWsg0eugrt+YNtRl0sVQZC1f4nJ0b1hVy4tkYeiDej/wYZkCgYEAhqdk0Z2A/lnj/Zi/JAdBp8exnbCLINwWqg+8ubgItwPhxTlDhNvtLg8+Kj6xby7nNlsV2r/4o8AAeGvYBgOQXKJmjW8m8S/yktY+MsUccgdQSky4nW0Yvg34Jwo8B2POWY7k7imkFqQ5/5FFPMdOj3h/m2uRMwVuMd1N+gI6nbkCgYB8V1IAfD6qh8yJOWYKNPLY5fD3897Za9kiajjbwa6dF7Tqa7a2uVtYxI+zlkAwskUHiKnw/+52UQ9P89hgQ+r+WIAZlLg1AYgAp07ynyNvcn9FuLNpYXOL5RldJUlcVvwU1+GS8wPmb8JIbmK74ZbGvLv2SWoCBMMv/DUNWWfDcg==";

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
        test();
//        testSign();
//        testHttpSign();
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