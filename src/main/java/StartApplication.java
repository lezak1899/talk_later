import edu.lingnan.talklater.netty.WebSocketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Description:
 * date: 2020/12/19 22:09
 * author likunzhu
 * version
 * since JDK 1.8
 */

@SpringBootApplication
@ComponentScan(basePackages ="edu.lingnan.talklater")//spring默认是会扫描主启动类所在的包及其里面，由于该项目主启动类不在任何包下，故需要指定扫描的包
@EnableJpaRepositories(basePackages = "edu.lingnan.talklater")
@EntityScan("edu.lingnan.talklater")
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);



        try {
            //启动websocket
            WebSocketService.getInstance().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}

