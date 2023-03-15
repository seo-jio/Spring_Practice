package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;
    @BeforeEach //각 테스트를[ 실행하기전 수행되는 메소드
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
   void join() {
       // given
        Member member = new Member(1L, "memberA", Grade.VIP);
       // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
       // then
        Assertions.assertThat(member).isEqualTo(findMember);
   }
}
