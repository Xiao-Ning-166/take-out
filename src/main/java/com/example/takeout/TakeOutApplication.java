package com.example.takeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class TakeOutApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(TakeOutApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");

        log.info("\n----------------------------------------------------------\n\t" +
                "仿外卖平台正在运行! Access URLs:\n\t" +
                "后台Local: \t\thttp://localhost:" + port + "/backend/index.html" + "/\n\t" +
                "后台External: \thttp://" + ip + ":" + port + "/backend/index.html" + "/\n\t" +
                "前台Local: \t\thttp://localhost:" + port + "/front/page/login.html" + "/\n\t" +
                "前台External: \thttp://" + ip + ":" + port + "/front/page/login.html" + "/\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + "/doc.html\n" +
                "----------------------------------------------------------");
    }

}
