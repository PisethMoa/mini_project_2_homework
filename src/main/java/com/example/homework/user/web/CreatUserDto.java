package com.example.homework.user.web;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record CreatUserDto(@NotBlank(message = "Name is required!")
                           String username,
                           @NotBlank(message = "Gender is required!")
                           String gender,
                           String oneSignalId,
                           String studentCardId,
                           @NotNull(message = "Your have to confirm , are you a student?")
                           Boolean isStudent){

}
