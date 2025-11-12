package com.briup.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: vanse(lc)
 * @Date: 2025/10/14-10-14-14:20
 * @Description：com.briup.utils
 */
public class UploadUtils {
    // tomcat: localhost:9999 [static]
    // nginx：localhost:80 [html]
    public static String upload(MultipartFile file) {
        String fileName = generateName(file);
//        String parentPath = "/Users/vanse/md/2025-602/code/BookStore/file/images/";
        // nginx：localhost:80 [html]
        String parentPath = "/opt/homebrew/var/www";
        File f = new File(parentPath, "/file/images/" + fileName);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:80/file/images/" + fileName;
    }

    public static String generateName(MultipartFile file) {
        // 2025/10/14/1.jpg
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy/MM/dd/");
        String directory = sdf.format(new Date());
        String name = UUID.randomUUID().toString();
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        return directory + name + ext;

    }
}
