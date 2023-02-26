package pl.employer.assistance.model.mapper;

import org.mapstruct.Mapper;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.UserDto;

@Mapper
public interface UserMapper {

    User mapToUser(UserDto Dto);

    UserDto mapToUserDto(User user);
}