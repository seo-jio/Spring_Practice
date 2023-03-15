package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach //각 테스트를[ 실행하기전 수행되는 메소드
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder() {
        // given
        Long memberId = 1L; //wrapper class를 쓰는 이유는 null이 들어갈 수도 있기 때문이다.
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        // when

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
