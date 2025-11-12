package com.briup.web.controller;

import com.briup.response.Result;
import com.briup.utils.PathUtils;
import com.briup.utils.UploadOSSUtils;
import com.briup.utils.UploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Auther: vanse(lc)
 * @Date: 2025/10/14-10-14-09:40
 * @Description：com.briup.web.controller
 */
@Slf4j
@RestController
//@RequestMapping("file")
public class FileController {
    // @RequestPart swagger扫描
//    @PostMapping("upload")
    public Result upload(String username,
                         String password,
                         @RequestPart MultipartFile image) throws IOException {
        // 临时存储到本地文件的临时目录
        log.info("用户名{},密码{},文件{}",username,password,image);
        // 将文件转存到本地磁盘
        // 签名.jpg
//        String originalFilename = image.getOriginalFilename();
//        String fileName = UUID.randomUUID().toString();
//        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
//        File file = new File("/Users/vanse/md/2025-602/code/BookStore/file/images"
//                ,fileName+ext);
//        if(!file.getParentFile().exists()){
//            file.getParentFile().mkdirs();
//        }
//        image.transferTo(file);
        String path = UploadUtils.upload(image);
        return Result.success(path);
    }

    @Autowired
    private UploadOSSUtils uploadOSSUtils;
    @PostMapping("upload")
    public Result uploadQiu(String username,
                         String password,
                         @RequestPart MultipartFile img) throws IOException {
        // 临时存储到本地文件的临时目录
        log.info("用户名{},密码{},文件{}",username,password,img);
        String path = uploadOSSUtils.uploadOss(img,
                PathUtils.generateFilePath(img.getOriginalFilename()));
        return Result.success(path);
    }
}
