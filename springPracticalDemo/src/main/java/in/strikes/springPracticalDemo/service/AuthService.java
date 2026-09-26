package in.strikes.springPracticalDemo.service;

import in.strikes.springPracticalDemo.dto.UserRegisterRequestDto;
import in.strikes.springPracticalDemo.dto.UserRegisterResponseDto;
import in.strikes.springPracticalDemo.entity.Role;
import in.strikes.springPracticalDemo.entity.User;
import in.strikes.springPracticalDemo.repository.RoleRepository;
import in.strikes.springPracticalDemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto){

        User user = new User();
        user.setUserName(requestDto.getUserName());
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        user.setPassword(encodedPassword);
        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER").get();
        user.getRoles().add(role);

        userRepository.save(user);

        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setUserName(user.getUserName());
        responseDto.setMessage("User saved Successfully...");

        return responseDto;
    }


}
