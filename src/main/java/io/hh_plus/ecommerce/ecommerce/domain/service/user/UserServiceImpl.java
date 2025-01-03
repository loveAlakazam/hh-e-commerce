package io.hh_plus.ecommerce.ecommerce.domain.service.user;

import io.hh_plus.ecommerce.ecommerce.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addPoint(long userId, int point) {

    }

    @Override
    public void getPoint(long userId) {

    }
}
