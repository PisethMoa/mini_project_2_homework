package com.example.homework.user.web;
import com.example.homework.user.UserService;
import com.example.homework.user.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserRestController{
    private final UserService userService;
    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id, @RequestBody UpdateUserDto updateUserDto){
        UserDto userDto=userService.updateUserById(id, updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have updated successfully.")
                .timestamp(LocalDateTime.now())
                .date(userDto)
                .build();
    }
    @PutMapping("/{id}/is-deleted")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id, @RequestBody DeletedDto dto ){
        Integer deletedId = userService.updateIsDeletedStatusById(id,dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .date(deletedId)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
        Integer deletedId = userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .date(deletedId)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto = userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been created successfully.")
                .timestamp(LocalDateTime.now())
                .date(userDto)
                .build();
    }
    @GetMapping
    public BaseRest<?> findAllUsers(@RequestParam(name="page",required = false,defaultValue = "1") int page,
                                    @RequestParam(name="limit",required = false,defaultValue = "20") int limit,
                                    @RequestParam(name="name",required = false,defaultValue = "") String name){
        PageInfo<UserDto> userDtoPageInfo = userService.findAllUser(page,limit,name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been created successfully.")
                .timestamp(LocalDateTime.now())
                .date(userDtoPageInfo)
                .build();

    }
    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreatUserDto createUserDto){
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been created successfully.")
                .timestamp(LocalDateTime.now())
                .date(userDto)
                .build();

    }
    @GetMapping("/{studentCardId}/student-card-id")
    public BaseRest<?> findUserByStudentCard(@PathVariable("studentCardId") String studentCardId){
        UserDto userDto = userService.findUserByStudentCard(studentCardId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been find successfully.")
                .timestamp(LocalDateTime.now())
                .date(userDto)
                .build();
    }
}
