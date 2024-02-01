package hello.springtx.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Log {

  @Id
  @GeneratedValue
  private Long id;

  private String message;

  public Log() {
  }

  public Log(String message) {
    this.message = message;
  }

}
