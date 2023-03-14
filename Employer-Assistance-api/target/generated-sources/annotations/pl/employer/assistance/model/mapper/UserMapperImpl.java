package pl.employer.assistance.model.mapper;

import javax.annotation.processing.Generated;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T13:47:14+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToUser(UserDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( Dto.getId() );
        user.setEmail( Dto.getEmail() );
        user.setPassword( Dto.getPassword() );

        return user;
    }

    @Override
    public UserDto mapToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
