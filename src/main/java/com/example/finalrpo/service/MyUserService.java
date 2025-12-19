package com.example.finalrpo.service;

import com.example.finalrpo.model.Permission;
import com.example.finalrpo.model.UserModel;
import com.example.finalrpo.repository.PermissionRep;
import com.example.finalrpo.repository.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final UserRep userRepository;
    private final PermissionRep permissionRep;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void registr(UserModel model) {
        UserModel check = userRepository.findByEmail(model.getEmail());
        if (check == null) {
            model.setPassword(passwordEncoder.encode(model.getPassword()));

            Permission permission = permissionRep.findByName("ROLE_USER");
            model.setPermissions(List.of(permission));

            userRepository.save(model);
        }
    }
}

