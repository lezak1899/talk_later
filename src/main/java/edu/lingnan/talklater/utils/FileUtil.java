package edu.lingnan.talklater.utils;

import jdk.internal.util.xml.impl.Input;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;

import java.io.InputStream;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Component;


/**
 * Description:
 * date: 2021/2/1 17:13
 *
 * @author likunzhu
 * @since
 */
@Component
public class FileUtil {

    //存储到阿里云服务器的文件路径
    @Value("${qrcode.path}")
    String qrCodePaht;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;



    public String uploadOutputStream(String fileName, ByteArrayOutputStream byteArrayOutputStream){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            byte[] buffer =byteArrayOutputStream.toByteArray();
            InputStream sbs = new ByteArrayInputStream(buffer);
            ossClient.putObject(bucketName, qrCodePaht+fileName, sbs);
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

        return qrCodePaht+fileName;//返回保存路径

    }


}
