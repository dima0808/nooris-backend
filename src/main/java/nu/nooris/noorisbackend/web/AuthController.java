package nu.nooris.noorisbackend.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.dto.JwtResponseDto;
import nu.nooris.noorisbackend.dto.LoginDto;
import nu.nooris.noorisbackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid LoginDto loginDto) {
    return ResponseEntity.ok(authService.login(loginDto));
  }
}
