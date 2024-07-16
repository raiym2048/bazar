package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.AuthMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.LoginRequest;
import com.example.bazar.model.dto.auth.RegisterRequest;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.AuthService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("User with this email is already exist", HttpStatus.FOUND);
        }
        User user = userRepository.save(authMapper.toUserDto(request));
        return authMapper.toDto(user);
    }
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        return authMapper.toDto(userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new CustomException("User with this email not found", HttpStatus.NOT_FOUND)));
    }
    @Override
    public User getUserFromToken(String token) {

        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        if (chunks.length != 3)
            throw new BadCredentialsException("Wrong token!");
        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try {
            byte[] decodedBytes = decoder.decode(chunks[1]);
            object = (JSONObject) jsonParser.parse(decodedBytes);
        } catch (ParseException e) {
            throw new BadCredentialsException("Wrong token!!");
        }
        return userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() ->
                new BadCredentialsException("Wrong token!!!"));
    }


}
