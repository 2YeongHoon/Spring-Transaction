package hello.springtx;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV1Test {

  @Autowired CallService callService;

  @Test
  void printProxy() {
    log.info("callService class={}", callService.getClass());
  }

  @Test
  void externalTest() {
    callService.external();
  }

  @Test
  void internalTest() {
    callService.internal();
  }

  @TestConfiguration
  static class InternalCallV1Config {
    @Bean
    CallService callService() {
      return new CallService();
    }
  }

  @Slf4j
  static class CallService {

    public void external() {
      log.info("external start");
      internal();
      printTxInfo();
    }

    @Transactional
    public void internal() {
      log.info("internal start");
      printTxInfo();
    }

    private void printTxInfo() {
      boolean txActive =
          TransactionSynchronizationManager.isActualTransactionActive();
      log.info("tx active={}", txActive);
    }
  }

}
