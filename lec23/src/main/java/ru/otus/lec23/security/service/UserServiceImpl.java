package ru.otus.lec23.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.lec23.security.exception.UserNotFoundException;
import ru.otus.lec23.security.repositorie.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException(String.format("Пользователь %s не найден", userName)));
    }
}
