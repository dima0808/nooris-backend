package nu.nooris.noorisbackend;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoorisApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Stockholm"));
    SpringApplication.run(NoorisApplication.class, args);
  }

}
