package com.example.homework.user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class User{
    private Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;
}
