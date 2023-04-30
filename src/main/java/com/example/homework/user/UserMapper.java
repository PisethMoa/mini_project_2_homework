package com.example.homework.user;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserMapper{
    @Mapper
    @InsertProvider(type =UserProvider.class,method = "buildInsertSql")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void insert(@Param("u") User user);
    @SelectProvider(type= UserProvider.class,method="buildSelectByIdSql")
    @Results(id="userResultMap",value = {
            @Result(column="student_card_id",property = "studentCardId"),
            @Result(column="is_student",property = "isStudent")})
    Optional<User> selectById(@Param("id") Integer id);
    @ResultMap("userResultMap")
    @SelectProvider(type = UserProvider.class,method = "buildSelectByStudentIdCard")
    Optional<User> selectByStudentIdCard(@Param("studentCardId") String studentCardId);
    @SelectProvider(type= UserProvider.class,method = "buildSelectSql")
    @ResultMap("userResultMap")
    List<User> select();
    @Select("SELECT EXISTS(SELECT * FROM users WHERE id = #{id})")
    boolean existById(@Param("id") Integer id);
    @DeleteProvider(type= UserProvider.class,method = "buildDeleteByIdSql")
    void deleteById(@Param("id") Integer id);
    @UpdateProvider(type = UserProvider.class,method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id,@Param("status") boolean status);
    @UpdateProvider(type=UserProvider.class,method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);
    @ResultMap("userResultMap")
    @SelectProvider(type= UserProvider.class,method = "buildSelectByNameSql")
    List<User> selectByName(@Param("name") String name);
}
