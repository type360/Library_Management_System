package com.briup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @description 上传文件路径工具类
 **/
public class PathUtils {
    /**
     * 该方法用于生成有效文件路径
     *  路径格式统一为: yyyy/MM/dd/随机UUID.文件格式
     */
    public static String generateFilePath(String fileName){
        //1.根据日期生成路径字符串   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());

        //2.获取有效 uuid
        //	注意：uuid默认为 xxx-xxx-xxx...，此处我们要把-去掉
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //3.获取文件的后缀名：test.png -> .png
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);

        //4.拼接得到有效文件名，然后返回
        String filePath = new StringBuilder().append(datePath).append(uuid).append(fileType).toString();

        return filePath;
    }
}