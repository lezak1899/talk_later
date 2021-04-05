package edu.lingnan.talklater.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Description:
 * date: 2021/4/4 14:35
 *
 * @author likunzhu
 * @since
 */
@Component
public class Base64Util {



    public static ByteArrayInputStream base64ToIo(String strBase64) throws IOException {

        //去取base64的头部格式
         String str =  strBase64.replace("data:image/jpeg;base64,","");
         // 解码，然后将字节转换为文件
         byte[] bytes = new BASE64Decoder().decodeBuffer(str); //将字符串转换为byte数组

         ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

         return byteArrayInputStream;
 }




}
