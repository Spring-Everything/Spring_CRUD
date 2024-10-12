package com.example.CRUD.Service;

import com.example.CRUD.DTO.UserDTO;

import java.util.List;

// 굳이 클래스와 인터페이스를 나누어 사용하는 이유?
// 유지보수성, 확장성, 유연성, 테스트 용이성, 다형성 등을 향상시키기 위한 중요한 설계 원칙을 따르기 때문
// 구현 클래스 (UserServiceImpl)의 역할 : 비즈니스 로직을 처리하는 곳
// 여기서는 UserService 인터페이스에서 정의된 메서드들은 어떤 작업을 해야 하는지에 대해서만 정의되어 있고, 그 작업을 어떻게 할지는 UserServiceImpl 클래스에서 구현되는 것
public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO userDTO, Long id);
    UserDTO deleteUser(Long id);
}
