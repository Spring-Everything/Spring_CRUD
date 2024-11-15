package com.example.CRUD.Service;

import com.example.CRUD.Entity.UserEntity;
import com.example.CRUD.DTO.UserDTO;
import com.example.CRUD.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
// final로 선언된 필드에 대해 생성자를 자동 생성해주는 Lombok 어노테이션
// public UserServiceImpl(UserRepository userRepository) {
//     this.userRepository = userRepository;
// }
// 이 어노테이션 사용 시 위와 같은 문법 필요없음
@RequiredArgsConstructor
public class UserServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    // 회원가입
    public UserDTO createUser(UserDTO userDTO) {
        // UserEntity 클래스에서 userEntity 객체를 생성하여 DTO를 엔티티로 변환하여 저장 준비
        UserEntity userEntity = userDTO.dtoToEntity();
        // 변환된 엔티티를 저장소에 저장
        UserEntity savedUser = userRepository.save(userEntity);
        logger.info("회원가입 완료!");
        // 저장된 엔티티를 DTO로 변환하여 반환
        return UserDTO.entityToDto(savedUser);
    }

    // 회원조회
    public List<UserDTO> getAllUsers() {
        // 저장소에서 모든 사용자 엔티티 조회 후 DTO로 변환하여 리스트로 수집
        List<UserDTO> userList = userRepository.findAll().stream()
                // UserEntity를 UserDTO로 변환
                .map(UserDTO::entityToDto)
                .collect(Collectors.toList());
        logger.info("사용자 전체 조회 완료! 총 " + userList.size() + "명의 사용자가 조회되었습니다.");
        // 변환된 사용자 리스트 반환
        return userList;
    }

    // uid로 회원조회
    public UserDTO getUserByUid(String uid){
        UserEntity userEntity = userRepository.findByUid(uid).orElseThrow();
        logger.info(uid + " 유저 조회 완료");
        return UserDTO.entityToDto(userEntity);
    }

    // 회원수정
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        // ID로 사용자 엔티티 조회, 없으면 예외 발생
        UserEntity userEntity = userRepository.findById(id).orElseThrow();

        // 유저 정보 수정
        userEntity.setUid(userDTO.getUid());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setAge(userDTO.getAge());
        userEntity.setGender(userDTO.getGender());

        // 변경된 엔티티를 updatedUser에 저장
        UserEntity updatedUser = userRepository.save(userEntity);
        logger.info("사용자 정보 업데이트 완료!");

        // 변경된 내용을 DTO로 변환하여 반환
        return UserDTO.entityToDto(updatedUser);
    }

    // 회원삭제
    public UserDTO deleteUser(Long id){
        // ID로 사용자 엔티티 조회, 없으면 예외 발생
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        // delete 함수로 해당 엔티티 삭제
        userRepository.delete(userEntity);
        logger.info("사용자 탈퇴 완료! " + userEntity);
        // 삭제된 사용자 정보를 DTO로 변환하여 반환
        return UserDTO.entityToDto(userEntity);
    }

//    // 회원조회 방법2
//    @Override
//    public List<UserDTO> getAllUsers() {
//        return userRepository.findAll().stream()
//                // UserEntity를 UserDTO로 변환
//                .map(UserDTO::entityToDto)
//                .collect(Collectors.toList());
//    }
}
