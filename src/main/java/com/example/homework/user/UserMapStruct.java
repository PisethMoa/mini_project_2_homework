package com.example.homework.user;
import com.example.homework.user.web.CreatUserDto;
import com.example.homework.user.web.UpdateUserDto;
import com.example.homework.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserMapStruct{
    User createUserDtoToUser(CreatUserDto createUserDto);
    User updateUserDtoToUser(UpdateUserDto updateUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto user);
    PageInfo<UserDto> userPageInforToUserDtoPageInfo(PageInfo<User> userPageInfo);
}
