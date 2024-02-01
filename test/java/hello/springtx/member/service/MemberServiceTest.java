package hello.springtx.member.service;

import static org.junit.jupiter.api.Assertions.*;

import hello.springtx.member.repository.LogRepository;
import hello.springtx.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MemberServiceTest {

  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  LogRepository logRepository;

  /**
   * MemberService @Transactional:OFF MemberRepository @Transactional:ON LogRepository
   * @Transactional:ON
   */
  @Test
  void outerTxOff_success() {

    //given
    String username = "outerTxOff_success";

    //when
    memberService.joinV1(username);

    //then: 모든 데이터가 정상 저장된다.
    assertTrue(memberRepository.find(username).isPresent());
    assertTrue(logRepository.find(username).isPresent());
  }

}