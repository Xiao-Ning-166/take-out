package com.example.takeout.modules.common.controller;

import com.example.takeout.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 公共方法
 *
 * @author xiaoning
 * @date 2022/07/11
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${take-out.path.upload}")
    private String uploadPath;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) {

        // 得到文件源名称和后缀名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 得到一个新名字
        String newName = UUID.randomUUID().toString() + suffix;

        // 判断文件目录是否存在
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            // 如果不存在，则创建目录
            uploadDir.mkdirs();
        }

        try {
            // 保存文件
            file.transferTo(new File(uploadPath + newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.OK(newName).success("文件上传成功!");
    }


    /**
     * 下载文件
     * @param name 文件名
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(uploadPath + name));
            // 定义浏览器响应类型
            response.setContentType("image/*");
            byte[] buffer = new byte[1024];
            int len;
            ServletOutputStream outputStream = response.getOutputStream();
            while((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流，释放资源
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
