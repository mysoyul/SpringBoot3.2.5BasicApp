package com.basic.myspringboot.users;

import com.basic.myspringboot.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {
    private final UserRepository userRepository;

//    public UserRestController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping
    public UserEntity create(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        return getUserEntity(id);
    }

    private UserEntity getUserEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                            String errMsg = String.format("ID = %d User Not Found", id);
                            return new BusinessException(errMsg, HttpStatus.NOT_FOUND);
                        }
                );
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @PatchMapping("/{email}/")
    public UserEntity updateUser(@PathVariable String email, @RequestBody UserEntity userDetail) {
        UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> {
                            String errMsg = String.format("Email = %s User Not Found", email);
                            return new BusinessException(errMsg, HttpStatus.NOT_FOUND);
                    });
        user.setName(userDetail.getName());
        UserEntity updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserEntity existUser = getUserEntity(id);
        userRepository.delete(existUser);
        return ResponseEntity.ok(String.format("ID = %d User가 삭제 되었습니다!", id));
    }

}
