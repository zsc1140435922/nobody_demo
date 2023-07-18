package com.zsc.example.nobody.dingding;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @program: nobody_demo
 * @description: 钉钉消息
 * @author: zhangsc
 * @create: 2021-06-07 09:49
 **/
public class DingdingMsg {
    public static void main(String[] args) throws IOException {
        // 钉钉的webhook
        String url = "https://oapi.dingtalk.com/robot/send?access_token=8548fb09d6eefc7b2f1fbae5196f3ad49c26897f65f2a7a1a1e8ccef9e0fb8c7";

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"通知 我就是我xxx, 是不一样的烟火\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }

    }
}
