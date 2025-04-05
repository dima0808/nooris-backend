package nu.nooris.noorisbackend.service;

import nu.nooris.noorisbackend.dto.JwtResponseDto;
import nu.nooris.noorisbackend.dto.LoginDto;

public interface AuthService {

  JwtResponseDto login(LoginDto loginDto);
}
