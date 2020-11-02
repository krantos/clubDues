package ar.com.ovittata.club.application.exceptions;

public class ClubMemberNotFoundException extends RuntimeException {
  public ClubMemberNotFoundException(Long id) {
    super("Could not find club member " + id);
  }
}
