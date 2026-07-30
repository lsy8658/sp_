package com.example.security_jwt.api;

import com.example.security_jwt.domain.user.dto.UserRequestDTO;
import com.example.security_jwt.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class JoinController {
    private final UserService userService;

    @PostMapping("/")
    public String join(@RequestBody UserRequestDTO dto) {
        userService.join(dto);
        return "success";
    }
}
