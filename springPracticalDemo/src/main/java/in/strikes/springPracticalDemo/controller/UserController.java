package in.strikes.springPracticalDemo.controller;

import in.strikes.springPracticalDemo.dto.UserRegisterRequestDto;
import in.strikes.springPracticalDemo.dto.UserRegisterResponseDto;
import in.strikes.springPracticalDemo.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping("/hello")
    public String sayHello(Authentication authentication){
        return "Hello, you are logged in as : "+authentication.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto requestDto){
        UserRegisterResponseDto responseDto = authService.registerUser(requestDto);

        return ResponseEntity.ok(responseDto);
    }

}
