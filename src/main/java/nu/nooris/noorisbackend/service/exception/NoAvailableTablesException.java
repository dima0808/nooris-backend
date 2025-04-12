package nu.nooris.noorisbackend.service.exception;

public class NoAvailableTablesException extends RuntimeException {

  public NoAvailableTablesException(int guests) {
    super("No available tables for " + guests + " guests");
  }
}
