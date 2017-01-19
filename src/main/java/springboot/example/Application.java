package springboot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xy on 2017/1/18.
 */
@SpringBootApplication
@RestController
public class Application {
    @RequestMapping("/")
    public String home(){
        return "hello";
    }
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
