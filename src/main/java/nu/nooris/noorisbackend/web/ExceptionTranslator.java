package nu.nooris.noorisbackend.web;

import static java.net.URI.create;
import static nu.nooris.noorisbackend.util.ValidationUtils.getErrorResponseOfFieldErrors;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
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

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    log.info("Input params validation failed");
    return ResponseEntity.status(BAD_REQUEST)
        .body(getErrorResponseOfFieldErrors(ex.getBindingResult().getFieldErrors()));
  }
}
