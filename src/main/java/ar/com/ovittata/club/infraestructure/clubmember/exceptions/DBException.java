package ar.com.ovittata.club.infraestructure.clubmember.exceptions;

public class DBException extends RuntimeException {

  public DBException(String message) {
    super(message);
  }

}
