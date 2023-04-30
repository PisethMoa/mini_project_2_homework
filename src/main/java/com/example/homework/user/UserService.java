package com.example.homework.user;
import com.example.homework.user.web.CreatUserDto;
import com.example.homework.user.web.UpdateUserDto;
import com.example.homework.user.web.UserDto;
import com.github.pagehelper.PageInfo;
public interface UserService{
    UserDto createNewUser(CreatUserDto createUserDto);
    UserDto findUserById(Integer id);
    PageInfo<UserDto> findAllUser(int page, int limit, String name);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatusById(Integer id, boolean status);
    UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);
    UserDto findUserByStudentCard(String studentCardId);
}
