package nu.nooris.noorisbackend.service.impl;

import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.dto.JwtResponseDto;
import nu.nooris.noorisbackend.dto.LoginDto;
import nu.nooris.noorisbackend.service.AuthService;
import nu.nooris.noorisbackend.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;

  @Override
  public JwtResponseDto login(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    return JwtResponseDto.builder()
        .token(jwtUtils.generateToken(authentication))
        .build();
  }
}
