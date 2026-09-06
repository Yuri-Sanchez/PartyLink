package com.partylink.user.mapper;

import com.partylink.user.dto.RegisterRequest;
import com.partylink.user.dto.RegisterResponse;
import com.partylink.user.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RegisterMapper {

    public static User toUser(RegisterRequest request){
        return User
                .builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .phone(request.phone())
                .dateOfBirth(request.dateOfBirth())
                .build();
    }

    public static RegisterResponse toRegisterResponse(User user){
        return RegisterResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
