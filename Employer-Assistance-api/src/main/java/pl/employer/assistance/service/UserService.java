package pl.employer.assistance.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.UserDto;

import pl.employer.assistance.model.mapper.UserMapper;
import pl.employer.assistance.repository.UserRepository;
import pl.employer.assistance.service.exception.ResourceNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addUser(UserDto userDto) {
        if (!existsByEmail(userDto.getEmail())) {
            User user = mapper.mapToUser(userDto);
            userRepository.save(user);
            return userDto;
        }else {
            throw new ResourceNotFoundException("A user with this email exists");
        }
    }


    public boolean existsByEmail(String email){
      return userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean deleteUser(long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
