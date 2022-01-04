package com.example.study.repository;


import com.example.study.component.LoginUserAuditorAware;
import com.example.study.config.JpaConfig;
import com.example.study.model.entity.User;
import com.example.study.model.repository.UserRepository;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Optional;


@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("ItemRepositoryTest 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class UserRepositoryTest {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";


        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        
        //@Builder로 만드는 생성자 없이 객체 생성하는 방법
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    @Test
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        // Builder로 객체 생서
        // Accessor로 업데이트


        //@Accessors(chain = true)
        //을 설정 시 아래와 같이 (builder와 비슷하게) 생성자를 만들 필요 없이 값을 바꿀 수 있다.
//        user
//                .setEmail("")
//                .setPhoneNumber("")
//                .setStatus("");
//        User u = new User("").setAccount().setEmail("").setPassword("");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-----------------------------");
                System.out.println(orderGroup.getRevName());
                System.out.println(orderGroup.getRevAddress());
                System.out.println(orderGroup.getTotalPrice());
                System.out.println(orderGroup.getTotalQuantity());

                System.out.println("-----------------------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println(orderDetail.getItem().getName());
                    System.out.println(orderDetail.getStatus());
                    System.out.println(orderDetail.getArrivalDate());

                });
            });
        }
        Assertions.assertNotNull(user);
    }

    @Test
    public void update(){

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent());    // false


        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent()); // false
    }

}