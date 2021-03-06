package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService ;
    @Autowired
    MemberRepository repository;

    @Test
    void join() {

        Member member = new Member();
        member.setName("hello");

        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();


        assertThat(findMember.getName()).isEqualTo(member.getName());
    }


    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");
        //when
        memberService.join(member1);
//        try{
//            memberService.join(member2);
//            fail("테스트 예외가 발생해야함");
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //then


    }

}