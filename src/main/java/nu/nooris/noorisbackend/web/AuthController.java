package nu.nooris.noorisbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.dto.JwtResponseDto;
import nu.nooris.noorisbackend.dto.LoginDto;
import nu.nooris.noorisbackend.service.AuthService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "API for authentication")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "Login to the application")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login successful",
          content = @Content(schema = @Schema(implementation = JwtResponseDto.class))),
      @ApiResponse(responseCode = "401", description = "Invalid credentials",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @PostMapping("/login")
  public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid LoginDto loginDto) {
    return ResponseEntity.ok(authService.login(loginDto));
  }
}
