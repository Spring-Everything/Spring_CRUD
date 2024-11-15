package com.example.CRUD.DTO;

import com.example.CRUD.Entity.UserEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
// 수정하기
// 왜 굳이 DTO로 변환해서 쓸까?
// DTO를 사용하면 엔티티를 클라이언트와 직접 주고받는 것을 방지하고, 데이터 보호, 성능 최적화, 계층 분리, 유효성 검사 등의 다양한 장점을 얻을 수 있음.
// 시스템의 안정성과 유지보수를 개선하고 데이터를 보다 안전하고 효율적으로 처리하기 위함
// 요약 : 데이터 보호 및 캡슐화, 계층 간 의존성 분리 등등
public class UserDTO {
    private Long id;
    private String uid;
    private String password;
    private String name;
    private String email;
    private String phone;
    private int age;
    private Boolean gender;

    public static UserDTO entityToDto(UserEntity userEntity){
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUid(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getAge(),
                userEntity.getGender()
        );
    }

    public UserEntity dtoToEntity(){
        return new UserEntity(id, uid, password, name, email, phone, age, gender);
    }
}
