package ar.com.ovittata.club;

import ar.com.ovittata.club.domain.clubmembers.ClubMember;
import ar.com.ovittata.club.infraestructure.clubmember.repository.ClubMemberRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClubApplication {

  Logger logger = LoggerFactory.getLogger(ClubApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ClubApplication.class, args);
  }


  @Bean
  CommandLineRunner initDatabase(ClubMemberRepositoryJPA repository) {
    return args -> {
      logger.info("Preloading {}", repository.save(new ClubMember("Bilbo Baggins", "burglar", "4587")));
      logger.info("Preloading {}", repository.save(new ClubMember("Frodo Baggins", "thief", "5566")));
    };
  }
}
