package com.example.activity_trackerweek8.serviceImpl;

import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Mapper.DtoToModel;
import com.example.activity_trackerweek8.Repositories.UserRepo;
import com.example.activity_trackerweek8.Service.UserService;
import com.example.activity_trackerweek8.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean validateEmail(User user) {
        for (User uk : userRepo.findAll()) {
            if (uk.getEmail().equals(user)) {
                return false;
            }
        }
        return true;
    }
@Override
    public User getUserByEmail(String email) {
        User user = userRepo.findByEmail(email);
        return user;
    }

    @Override
    public User loginUser(UserDto userdto) {
        User user = DtoToModel.userDtoToUser(userdto);
       User mainUser = userRepo.findEmailAndPassWord(user.getEmail(), user.getPassword());
       if(mainUser != null){
           return mainUser;
       }
        return null;
    }

    @Override
    public User registerUsers(UserDto userdto) {
        User user = DtoToModel.userDtoToUser(userdto);
        return userRepo.save(user);
    }
}
