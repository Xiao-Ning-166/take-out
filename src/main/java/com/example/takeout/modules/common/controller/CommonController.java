package com.example.takeout.modules.common.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.takeout.modules.user.entity.User;
import com.example.takeout.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 公共方法
 *
 * @author xiaoning
 * @date 2022/07/11
 */
@Slf4j
@RestController
@RequestMapping("/common")
@Api(tags = "公共接口")
public class CommonController {

    @Value("${take-out.path.upload}")
    private String uploadPath;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
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
     *
     * @param name     文件名
     * @param response
     */
    @GetMapping("/download")
    @ApiOperation(value = "下载文件", notes = "下载文件")
    public void download(String name, HttpServletResponse response) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(uploadPath + name));
            // 定义浏览器响应类型
            response.setContentType("image/*");
            byte[] buffer = new byte[1024];
            int len;
            ServletOutputStream outputStream = response.getOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
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


    /**
     * 获取验证码
     *
     * @param user   要获取验证码的手机号
     * @param request
     * @return
     */
    @PostMapping("/code")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public Result<?> getVerificationCode(@RequestBody User user, HttpServletRequest request) {
        // 1、校验手机号
        if (StrUtil.isBlank(user.getPhone())) {
            return Result.error("手机号不能为空!");
        }
        // 2、生成验证码
        String verificationCode = RandomUtil.randomNumbers(6);
        // request.getSession().setAttribute("verificationCode", verificationCode);

        // 3、将验证码保存到Redis中
        redisTemplate.opsForValue().set("login::" + user.getPhone(), verificationCode, 5, TimeUnit.MINUTES);

        // 4、给用户手机发送验证码
        log.info("验证码为 ---> {}", verificationCode);

        return Result.OK().success("验证码已发送!");
    }

}
