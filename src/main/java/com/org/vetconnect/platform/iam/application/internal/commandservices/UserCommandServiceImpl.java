package com.org.vetconnect.platform.iam.application.internal.commandservices;


import com.org.vetconnect.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.org.vetconnect.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.org.vetconnect.platform.iam.domain.model.aggregates.User;
import com.org.vetconnect.platform.iam.domain.model.commands.SignInCommand;
import com.org.vetconnect.platform.iam.domain.model.commands.SignUpCommand;
import com.org.vetconnect.platform.iam.domain.model.entities.Role;
import com.org.vetconnect.platform.iam.domain.model.valueobjects.Roles;
import com.org.vetconnect.platform.iam.domain.services.UserCommandService;
import com.org.vetconnect.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.org.vetconnect.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterPhone;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterRUC;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final VetCenterRepository vetCenterRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository,VetCenterRepository vetCenterRepository, PetOwnerRepository petOwnerRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.vetCenterRepository = vetCenterRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    @Transactional
    public Optional<User> handle(SignUpCommand command) {
        if (!isValidEmail(command.username())) {
            throw new RuntimeException("Invalid email format");
        }
        if (userRepository.existsByEmail(command.username()))
            throw new RuntimeException("Email already exists");
        var stringRoles = command.roles();
        var roles = new ArrayList<Role>();
        if (stringRoles == null || stringRoles.isEmpty()) {
            var storedRole = roleRepository.findByName(Roles.ROLE_USER);
            storedRole.ifPresent(roles::add);
        } else {
            stringRoles.forEach(role -> {
                var storedRole = roleRepository.findByName(Roles.valueOf(role));
                storedRole.ifPresent(roles::add);
            });
        }
        var user = new User(command.username(), hashingService.encode(command.password()), roles);

        if (hasRole(roles, Roles.VETERINARY)) {
            var vetCenter = new VetCenter();
            vetCenter.setVetCenterRUC(new VetCenterRUC(command.vetCenterRuc()));
            vetCenter.setVetCenterName(new VetCenterName(command.vetCenterClinicName()));
            vetCenter.setLicense(command.vetCenterLicense());
            vetCenter.setAddress(command.vetCenterAddress());
            vetCenter.setEmail(user.getEmail());
            vetCenter.setVetCenterPhone(new VetCenterPhone(command.vetCenterPhone()));
            vetCenter.setUser(user);
            vetCenterRepository.save(vetCenter);
        }

        if (hasRole(roles, Roles.CLIENT)) {
            var petOwner= new PetOwner();
            petOwner.setName(command.clientName());
            petOwner.setEmail(user.getEmail());
            petOwner.setDNI(command.clientDni());
            petOwner.setPhone(command.clientPhone());
            petOwner.setUser(user);
            petOwnerRepository.save(petOwner);
        }

        userRepository.save(user);

        System.out.println(command.roles().toString());
        return userRepository.findByEmail(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.username());
        if (user.isEmpty()) throw new RuntimeException("Email not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(regex);
    }

    private boolean hasRole(List<Role> roles, Roles targetRole) {
        return roles.stream().anyMatch(role -> role.getName().equals(targetRole));
    }

    @Override
    @Transactional
    public Optional<User> changePassword(Long userId, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();


        if (!hashingService.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }


        user.setPassword(hashingService.encode(newPassword));

        userRepository.save(user);

        return Optional.of(user);
    }

}
