package ar.com.ovittata.club.integration;

import ar.com.ovittata.club.application.controllers.ClubMemberController;
import ar.com.ovittata.club.domain.clubmembers.ClubMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ClubMemberJPAControllerTest {

  WebTestClient client;

  @LocalServerPort
  int port;

  private String BASE_URL;

  @BeforeEach
  void setBaseUrl() {
    BASE_URL = "http://localhost:" + port;
  }

  @Test
  void getClassMemberById() throws Exception {

    client = WebTestClient.bindToServer().baseUrl(BASE_URL).build();

    client.get()
        .uri(ClubMemberController.BASE + ClubMemberController.MEMBERS + "/1")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectBody()
        .jsonPath("$.name")
        .isEqualTo("Bilbo Baggins");
  }

  @Test
  void memberNotFound() throws Exception {
    client = WebTestClient.bindToServer().baseUrl(BASE_URL).build();

    client.get()
        .uri(ClubMemberController.BASE + ClubMemberController.MEMBERS + "/3")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  void deleteClubMember() {
    client = WebTestClient.bindToServer().baseUrl(BASE_URL).build();

    client.delete()
        .uri(ClubMemberController.BASE + ClubMemberController.MEMBERS + "/2")
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void deleteClubMemberByIdClubMemberNotFound() {
    client = WebTestClient.bindToServer().baseUrl(BASE_URL).build();

    client.delete()
        .uri(ClubMemberController.BASE + ClubMemberController.MEMBERS + "/3")
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  void getAllMembers() {
    client = WebTestClient.bindToServer().baseUrl(BASE_URL).build();

    client.get()
        .uri(ClubMemberController.BASE + ClubMemberController.MEMBERS)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ClubMember.class);
  }

  @Test
  void updateClubMemberJsonRequestExceptionTest() {
    client = WebTestClient.bindToServer().baseUrl(BASE_URL).build();
    String jsonBodyRequest = "{" +
        "\"name\": 2,"+
        "\"lastName\": \"Ferdinand\","+
        "\"memberNumber\":\"2365\""+
        "}";
    client.put()
        .uri(ClubMemberController.BASE + ClubMemberController.MEMBERS + "/2")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(jsonBodyRequest))
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody(String.class)
        .isEqualTo("No valid json in the request body");

  }
}
