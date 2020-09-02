package com.zsc.example.nobody.service;

import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import ch.qos.logback.core.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.*;

/**
 * @program: markting-exchangecenter
 * @description:
 * @author: zhangsc
 * @create: 2020-01-03 18:51
 **/
@Component
public class InitService implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initGeoLiteCity();
    }

    private void initGeoLiteCity() {
        FileOutputStream out = null;
        InputStream in = ClassLoader.getSystemResourceAsStream("GeoLite2-City.mmdb");
        String filePath = "/Users/zhangshichuang/logs/GeoLite2-City.mmdb";
        File newFile = new File(filePath);
        try {
            if (!newFile.exists()) {

                //循环存放临时数据
                byte[] buff = new byte[1024];
                out = new FileOutputStream(newFile);
                int length = 0;
                while ((length = in.read(buff)) != -1) {
                    out.write(buff, 0, length);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
