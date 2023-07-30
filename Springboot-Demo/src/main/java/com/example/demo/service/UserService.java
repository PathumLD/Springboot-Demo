package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    public UserDTO saveUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }
    public List<UserDTO> getAllUsers(){
        List<User>userList=userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO updateUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO,User.class));
        return userDTO;
    }

    public boolean deleteUser(UserDTO userDTO){
        userRepo.delete(modelMapper.map(userDTO,User.class));
        return true;
    }

    //Step 1:
    //user id > user details
    //select * from user where id = 1

    public UserDTO getUserByID(String userID){
        User user = userRepo.getUserByID(userID);
        return modelMapper.map(user, UserDTO.class);
    }

    //Step 2:
    //user id, address > user details
    //select * from user where id = 1 and address = 'colombo'

    public UserDTO getUserByIDAndAddress(String userID, String address){
        User user = userRepo.getUserByIDAndAddress(userID,address);
        return modelMapper.map(user,UserDTO.class);
    }

    //Step 3:
    //user id, address > user details update
    //update address from user where id = 1 and address = 'colombo'

//    @Modifying
//    public UserDTO putUserByID(String userID){
//        User user = userRepo.putUserByID(userID);
//        return modelMapper.map(user, UserDTO.class);
//    }

}
