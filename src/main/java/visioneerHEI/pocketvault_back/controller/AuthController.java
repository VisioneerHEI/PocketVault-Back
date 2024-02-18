package visioneerHEI.pocketvault_back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import visioneerHEI.pocketvault_back.service.TokenService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
  private final TokenService tokenService;

  @PostMapping("/token")
  public String token(Authentication authentication) {
    return tokenService.generateToken(authentication);
  }
}
