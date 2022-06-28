package com.nttdata.service.impl;

import com.nttdata.domain.dao.User;
import com.nttdata.repository.AbstractRepository;
import com.nttdata.repository.UserRepository;
import com.nttdata.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    AbstractRepository<User> getRepo() {
        return userRepository;
    }
}
