package com.briup.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @className: UploadOSSUtils
 * @Description: 七牛云文件上传工具类
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class UploadOSSUtils {
    //AK
//    @Value("${oss.accessKey}")
    private String accessKey ;
    //SK
//    @Value("${oss.secretKey}")
    private String secretKey;
    //桶
//    @Value("${oss.bucket}")
    private String bucket;
    //基础域名
//    @Value("${oss.baseUrl}")
    private String baseUrl;

    /**
     * 文件上传核心方法
     * @param imgFile  需要上传的文件
     * @param filePath 上传文件路径（规定格式）
     * @return
     */
    public String uploadOss(MultipartFile imgFile, String filePath){
        //构造一个带指定 Region 对象的配置类(根据七牛云服务器地区进行设置，这里autoRegion会自动匹配相应地区七牛云服务)
        Configuration configuration = new Configuration(Region.autoRegion());
        //将配置传入UploadManager
        UploadManager uploadManager = new UploadManager(configuration);
        //使用用户传入的文件路径作为key值
        String key = filePath;
        try {
            //选择文件路径进行上传（根据上传的文件转换成输入流）
            InputStream inputStream = imgFile.getInputStream();
            //验证AK与SK
            Auth auth = Auth.create(accessKey, secretKey);
            //指定桶
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                //打印key
                log.info("key: {}",putRet.key);
                //打印hash
                log.info("hash: {}",putRet.hash);
                //返回访问路径
                log.info("图片上传成功，访问地址：{}",baseUrl+putRet.key);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    ex2.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //返回静态资源访问URL
        return baseUrl+key;
    }
}
