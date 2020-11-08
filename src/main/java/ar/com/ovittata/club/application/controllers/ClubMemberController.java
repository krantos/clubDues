package ar.com.ovittata.club.application.controllers;

import ar.com.ovittata.club.application.exceptions.ClubMemberNotFoundException;
import ar.com.ovittata.club.application.exceptions.JsonRequestBodyException;
import ar.com.ovittata.club.domain.clubmembers.ClubMember;
import ar.com.ovittata.club.domain.clubmembers.ClubMemberService;
import ar.com.ovittata.club.infraestructure.clubmember.exceptions.DBException;
import ar.com.ovittata.club.utils.MapperFromJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.rmi.ConnectException;
import java.util.List;

@RestController
@RequestMapping(ClubMemberController.BASE)
public class ClubMemberController {

  public static final String BASE = "/api/v1";
  public static final String MEMBERS = "/members";
  public static final String MEMBER_ID = "/members/{id}";
  private final ClubMemberService clubMemberService;
  private Logger logger = LoggerFactory.getLogger(ClubMemberController.class);

  @Autowired
  public ClubMemberController(ClubMemberService service) {
    this.clubMemberService = service;
  }

  @GetMapping(MEMBER_ID)
  public ResponseEntity<ClubMember> one(@PathVariable(name = "id") Long id) {
    logger.info("get one member with id: {}", id);
    return new ResponseEntity<>(clubMemberService.findById(id), HttpStatus.OK);
  }

  @GetMapping(MEMBERS)
  public ResponseEntity<List<ClubMember>> all() {
    logger.info("Get all members");
    return new ResponseEntity<>(clubMemberService.all(), HttpStatus.OK);
  }

  @PostMapping(ClubMemberController.MEMBERS)
  public ResponseEntity<ClubMember> newClubMember(@RequestBody String json) {
    if (!MapperFromJson.isValidJson(json, "/schemas/clubMember.json")) {
      throw new JsonRequestBodyException();
    }
    ClubMember clubMember = MapperFromJson.fromJsonToClazz(json, ClubMember.class);
    if (clubMember == null) {
      return ResponseEntity.badRequest().build();
    }
    return new ResponseEntity<>(clubMemberService.save(clubMember), HttpStatus.OK);
  }


  @PutMapping(ClubMemberController.MEMBER_ID)
  public ResponseEntity<ClubMember> replaceClubMember(@RequestBody String json, @PathVariable Long id) {
    logger.info("Update member with id: {}", id);
    if (!MapperFromJson.isValidJson(json, "/schemas/clubMember.json")) {
      throw new JsonRequestBodyException();
    }
    ClubMember updateData = MapperFromJson.fromJsonToClazz(json, ClubMember.class);
    if (updateData == null) {
      return ResponseEntity.badRequest().build();
    }
    return new ResponseEntity<>(clubMemberService.update(updateData, id), HttpStatus.OK);
  }

  @DeleteMapping(ClubMemberController.MEMBER_ID)
  public ResponseEntity<Void> deleteClubMember(@PathVariable Long id) {
    logger.info("Delete member with id: {}", id);
    clubMemberService.delete(id);
    return ResponseEntity.ok().build();
  }

  @ResponseBody
  @ExceptionHandler(ClubMemberNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handler(ClubMemberNotFoundException exception) {
    logger.info("Not Found");
    return exception.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(JsonRequestBodyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String jsonRequestBodyException(JsonRequestBodyException exception) {
    return exception.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(DBException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String dbException(DBException exception) {
    return "Something went wrong. Try again later";
  }

}
