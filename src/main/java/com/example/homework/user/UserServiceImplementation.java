package com.example.homework.user;
import com.example.homework.user.web.CreatUserDto;
import com.example.homework.user.web.UpdateUserDto;
import com.example.homework.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{
    private final UserMapper userMapper;
    public final UserMapStruct userMapStruct;
    @Override
    public UserDto createNewUser(CreatUserDto creatUserDto) {
        User user = userMapStruct.createUserDtoToUser(creatUserDto);
        userMapper.insert(user);
        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found",id)));

        return userMapStruct.userToUserDto(user);
    }
    //pageInfo page and limit
    @Override
    public PageInfo<UserDto> findAllUser(int page, int limit,String name ) {
        if(name.equals("")){
            PageInfo<User> userPageInfo = PageHelper.startPage(page,limit)
                    .doSelectPageInfo(userMapper::select);
            return userMapStruct.userPageInforToUserDtoPageInfo(userPageInfo);
        }
        else{
            PageInfo<User> userPageInfo = PageHelper.startPage(page,limit)
                    .doSelectPageInfo(() -> userMapper.selectByName(name));
            return userMapStruct.userPageInforToUserDtoPageInfo(userPageInfo);
        }

    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isExisted = userMapper.existById(id);
        if(isExisted){
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }

    @Override
    public Integer updateIsDeletedStatusById(Integer id,boolean status) {
        boolean isExisted  = userMapper.existById(id);
        if(isExisted){
            userMapper.updateIsDeletedById(id,status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }
    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if(userMapper.existById(id)){
            User user=userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));

    }

    @Override
    public UserDto findUserByStudentCard(String studentCardId) {
        User user = userMapper.selectByStudentIdCard(studentCardId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %s is not found",studentCardId)));

        return userMapStruct.userToUserDto(user);
    }
}
