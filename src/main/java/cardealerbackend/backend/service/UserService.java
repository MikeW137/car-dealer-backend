package cardealerbackend.backend.service;

import cardealerbackend.backend.exception.InformationExistException;
import cardealerbackend.backend.exception.InformationNotFoundException;
import cardealerbackend.backend.model.LoginRequest.LoginRequest;
import cardealerbackend.backend.model.User;
import cardealerbackend.backend.model.response.LoginResponse;
import cardealerbackend.backend.repository.UserRepository;
import cardealerbackend.backend.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void setUserDetailService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    public User createUser(User userObject) {
        System.out.println("service calling createUser ==>");
        if (!userRepository.existsByUserName(userObject.getUserName())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with username " + userObject.getUserName() +
                    " already exists");
        }
    }

    //User login business logic
    public ResponseEntity<Object> loginUser(LoginRequest loginRequest){
        System.out.println("service calling loginUser ==>");
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername((loginRequest.getUserName()));
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        }catch(NullPointerException e){
            throw new InformationNotFoundException("user with that username " + loginRequest.getUserName() + " not found");
        }
    }

    public User findUserByUserName(String username){
        return userRepository.findUserByUserName(username);
    }
}

