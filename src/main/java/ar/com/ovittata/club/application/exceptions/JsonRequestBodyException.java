package ar.com.ovittata.club.application.exceptions;

public class JsonRequestBodyException extends RuntimeException {
  public JsonRequestBodyException() {
    super("No valid json in the request body");
  }

}
