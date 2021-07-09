package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        // 1. ThreadA : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // 2. ThreadB : B사용자가 20000원 주문
        statefulService1.order("userB", 20000);
        
        // 3. ThreadA : 사용자A 주문 금액 조회
        // 4. 10000원이 되어야 하는데 중간에 B가 주문하여 20000원이 됨
        int price = statefulService1.getPrice();

        // 5. 따라서, 밑과 같이 바꿔줘야 함
        statefulService1.order2("userA", 10000);
        int price2 = statefulService1.getPrice();

        assertThat(statefulService1.getPrice()).isEqualTo(price2);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
        
    }
    
}
