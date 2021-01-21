//package com.zsc.example.nobody.rsa;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//
//import org.junit.Test;
//
//import com.yunzong.util.RSAUtil;
//
//import sun.security.rsa.RSAPublicKeyImpl;
//
///**
// * 生成公私钥
// * @author Administrator
// *
// */
//public class RSA {
//	/*public static void main(String[] args) throws Exception {
//		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        keyPairGen.initialize(2048);
//        KeyPair keyPair = keyPairGen.generateKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//        String publicKeyStr = RSAUtil.getKeyString(publicKey);
//        String privateKeyStr = RSAUtil.getKeyString(privateKey);
//        System.out.println("公钥："+publicKeyStr);
//        System.out.println("私钥："+privateKeyStr);
//	}*/
//	@Test
//	public void getDefaultRSA2() throws Exception{
//		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        keyPairGen.initialize(2048);
//        KeyPair keyPair = keyPairGen.generateKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//        String publicKeyStr = RSAUtil.getKeyString(publicKey);
//        String privateKeyStr = RSAUtil.getKeyString(privateKey);
//        System.out.println("公钥："+publicKeyStr);
//        System.out.println("私钥："+privateKeyStr);
//	}
//	@Test
//	public void checkRSA() throws Exception{
//		String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv+VGsIXUjXm16VgIhPZ6iT0Id16vJTn6aRvTEgVj+s04v5w8CNvaNYC97E5BGPfHhU/iaBNyfw6kl6KszYHJ1OvkJXgprNt40TkfKKeUNUCsujq8dQH/h6t2Fhm9dmklyro7ZFsmmS4ecR1fIpS1HpHAOD8z4hkwAhHwn5CBoWicH4f5c9oHzqFZLnS6kxPQKLZzTzWdoSLFGYHMMM++mZ4s5Y2oTVdFxo4vLepN1s259S9PDHmGO2mkoRE/PJ6k8rI5AKYstiqnnBXkWuqeZLds/jOkW866Qp1wWkiBMouan639HWwqQQT4SFf5uVpMYiRyRKcdHzyaqUsiPxQMYQIDAQAB";
//		String privateKeyStr = "MIIEpAIBAAKCAQEAv+VGsIXUjXm16VgIhPZ6iT0Id16vJTn6aRvTEgVj+s04v5w8CNvaNYC97E5BGPfHhU/iaBNyfw6kl6KszYHJ1OvkJXgprNt40TkfKKeUNUCsujq8dQH/h6t2Fhm9dmklyro7ZFsmmS4ecR1fIpS1HpHAOD8z4hkwAhHwn5CBoWicH4f5c9oHzqFZLnS6kxPQKLZzTzWdoSLFGYHMMM++mZ4s5Y2oTVdFxo4vLepN1s259S9PDHmGO2mkoRE/PJ6k8rI5AKYstiqnnBXkWuqeZLds/jOkW866Qp1wWkiBMouan639HWwqQQT4SFf5uVpMYiRyRKcdHzyaqUsiPxQMYQIDAQABAoIBAQCdBQT0yG2GXkMl7uz5r53Q0motEwMJ2Qk4HJZ3toV64ZY/452UOiGxiejehaHqxkUypo1CUDduFBNGKMxxVHRMj8Edj9e6I9QWj0EZyRYTBRrRP+KZc8sdvoVfzCwlyiAsZHrYwWUyDnTGZhxEdZ5Gp0i8Pas8pRONfhSA3IPWCBBqRkV7VP+J5FlZtkOWBOETLtb00BzT+Wu/kwIU5dfapVEAFhV0BJGvZmlJJIBLVF423+YKvx917TPOsi1mecZ7ZYrhcy/vf1gRcfs7kbWwUrGO8TTZenLe9oGaooS1cVklnebznimf8ZBroQNMO8G67KNvtoeOiJ1GszyWGxuBAoGBAPcRXh9RgfkKH6NwEYo9SlKYeNGiGMVdc/tc6PnLIyp0gUpCWAPU4ciPTwuehcV3xyLBFPcmgW3XFUQPPJiLIsjbVi5yEU/iby6AxCXXipKas6ec756/DXid/8Jgd0F+sIcTL2YZ2hBVMWdbuj607aofEN4eOzcnSvgU0RJGPoZZAoGBAMbVSPztcqwZL1ip7cQhdsY64EFaIRrPkEZzzWlnDxPoofiZkiHgvODE0+C4Lly9S7BYkwBriNro7HF4KrGX8qpj7mUrJwLBpw/29jez/yQxUWmoZrp9DPefjJO+PV579rujrj1CWbd5rdnwTeEk/Hf902+oTqrCPN8Ck85foAVJAoGAK6TkxJGIbS7Nofh1EL989pQ6m4WP7KVyjI/AFAg/YQuNn0mS9LJRZDV0mC/bn6NmCQykoHgr2vRorEoowSWq8mJUmgXKaxsr2sIiZDDsKLOtUsmVRqynO4xllreluWsg0eugrt+YNtRl0sVQZC1f4nJ0b1hVy4tkYeiDej/wYZkCgYEAhqdk0Z2A/lnj/Zi/JAdBp8exnbCLINwWqg+8ubgItwPhxTlDhNvtLg8+Kj6xby7nNlsV2r/4o8AAeGvYBgOQXKJmjW8m8S/yktY+MsUccgdQSky4nW0Yvg34Jwo8B2POWY7k7imkFqQ5/5FFPMdOj3h/m2uRMwVuMd1N+gI6nbkCgYB8V1IAfD6qh8yJOWYKNPLY5fD3897Za9kiajjbwa6dF7Tqa7a2uVtYxI+zlkAwskUHiKnw/+52UQ9P89hgQ+r+WIAZlLg1AYgAp07ynyNvcn9FuLNpYXOL5RldJUlcVvwU1+GS8wPmb8JIbmK74ZbGvLv2SWoCBMMv/DUNWWfDcg==";
//
//		PublicKey publicKey = RSAUtil.getPublicKey(publicKeyStr);
//		int bitLength = ((RSAPublicKeyImpl) publicKey).getModulus().bitLength();
//        if (bitLength != 2048) {
//           //目前仅支持RSA2签名方式
//        }
//        //  签名测试
//        String signForSHA256 = RSAUtil.sign("SIGN_TEXT", privateKeyStr);
//        //  验签测试
//        boolean verifyForSHA256 = RSAUtil.verify("SIGN_TEXT", signForSHA256, publicKeyStr);
//        if (!verifyForSHA256) {
//        	//应用公私钥不匹配
//        }
//	}
//
//}
