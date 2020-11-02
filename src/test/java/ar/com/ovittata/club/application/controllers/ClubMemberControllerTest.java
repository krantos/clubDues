package ar.com.ovittata.club.application.controllers;

import ar.com.ovittata.club.domain.clubmembers.ClubMemberService;
import ar.com.ovittata.club.infraestructure.clubmember.exceptions.DBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockitoSettings
class ClubMemberControllerTest {

  MockMvc mockMvc;

  @Mock
  ClubMemberService service;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ClubMemberController(service)).build();
  }

  @Test
  void dbException() throws Exception {
    when(service.all()).thenThrow(new DBException("message"));
    mockMvc.perform(get("/api/v1/members"))
        .andExpect(status().isInternalServerError())
        .andExpect(content().string("Something went wrong. Try again later"));
  }
}