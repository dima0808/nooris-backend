package nu.nooris.noorisbackend.web;

import static java.net.URI.create;
import static nu.nooris.noorisbackend.util.ValidationUtils.getErrorResponseOfFieldErrors;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

import lombok.extern.slf4j.Slf4j;
import nu.nooris.noorisbackend.service.exception.BookingNotFoundException;
import nu.nooris.noorisbackend.service.exception.InvalidBookingTimeException;
import nu.nooris.noorisbackend.service.exception.MenuItemNotFoundException;
import nu.nooris.noorisbackend.service.exception.NoAvailableTablesException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ProblemDetail> handleBadCredentialsException() {
    log.info("Bad credentials exception raised");
    ProblemDetail problemDetail = forStatusAndDetail(UNAUTHORIZED, "Wrong username or password");
    problemDetail.setType(create("bad-credentials"));
    problemDetail.setTitle("Bad Credentials");
    return ResponseEntity.status(UNAUTHORIZED).body(problemDetail);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ProblemDetail handleAccessDenied(AccessDeniedException ex) {
    log.info("Access denied exception raised");
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(FORBIDDEN, ex.getMessage());
    problemDetail.setType(create("access-denied"));
    problemDetail.setTitle("Access Denied");
    return problemDetail;
  }

  @ExceptionHandler(AuthenticationException.class)
  public ProblemDetail handleAuthException(AuthenticationException ex) {
    log.info("Authentication exception raised");
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(UNAUTHORIZED, ex.getMessage());
    problemDetail.setType(create("authentication-error"));
    problemDetail.setTitle("Authentication Error");
    return problemDetail;
  }

  @ExceptionHandler(MenuItemNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleMenuItemNotFoundException(
      MenuItemNotFoundException ex) {
    log.info("Menu item not found exception raised");
    ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, ex.getMessage());
    problemDetail.setType(create("menu-item-not-found"));
    problemDetail.setTitle("Menu Item Not Found");
    return ResponseEntity.status(NOT_FOUND).body(problemDetail);
  }

  @ExceptionHandler(BookingNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleBookingNotFoundException(
      MenuItemNotFoundException ex) {
    log.info("Booking not found exception raised");
    ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, ex.getMessage());
    problemDetail.setType(create("booking-not-found"));
    problemDetail.setTitle("Booking Not Found");
    return ResponseEntity.status(NOT_FOUND).body(problemDetail);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException ex) {
    log.info("Illegal argument exception raised");
    ProblemDetail problemDetail = forStatusAndDetail(BAD_REQUEST, ex.getMessage());
    problemDetail.setType(create("illegal-argument"));
    problemDetail.setTitle("Illegal Argument");
    return ResponseEntity.status(BAD_REQUEST).body(problemDetail);
  }

  @ExceptionHandler({InvalidBookingTimeException.class, NoAvailableTablesException.class})
  public ResponseEntity<ProblemDetail> handleBookingException(RuntimeException ex) {
    log.info("Booking exception raised");
    ProblemDetail problemDetail = forStatusAndDetail(BAD_REQUEST, ex.getMessage());
    problemDetail.setType(create("booking-error"));
    problemDetail.setTitle("Booking Error");
    return ResponseEntity.status(BAD_REQUEST).body(problemDetail);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    log.info("Input params validation failed");
    return ResponseEntity.status(BAD_REQUEST)
        .body(getErrorResponseOfFieldErrors(ex.getBindingResult().getFieldErrors()));
  }
}
