package com.example.CRUD.Controller;

import com.example.CRUD.DTO.UserDTO;
import com.example.CRUD.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/user")
// final로 선언된 필드에 대해 생성자를 자동 생성해주는 Lombok 어노테이션
@RequiredArgsConstructor
public class UserController {

    // UserService를 주입받아 사용
    private final UserService userService;

    // 회원가입
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        // userDTO를 서비스 계층으로 전달하여 회원을 생성
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // 회원조회
    @GetMapping
    public ResponseEntity<List<UserDTO>> getFirstUser() {
        // 모든 사용자를 조회하고, DTO 리스트로 반환
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 회원수정
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        // 변수로 받은 id와 수정할 정보를 담은 userDTO를 서비스 계층으로 전달하여 사용자 정보 수정
        UserDTO updatedUser = userService.updateUser(userDTO, id);
        return ResponseEntity.ok(updatedUser);
    }

    // 회원삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        // 변수로 받은 id를 이용해 해당 사용자를 삭제
        UserDTO deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
}
