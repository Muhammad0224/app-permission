package uz.pdp.apppermission.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.pdp.apppermission.domain.User;
import uz.pdp.apppermission.model.resp.UserRespDto;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
@Component
public interface MapstructMapper {

    /**
     * User
     */
    @Mapping(source = "user.role.name", target = "roleName")
    @Mapping(source = "user.createdBy.username", target = "createdBy")
    @Mapping(source = "user.updatedBy.username", target = "updatedBy")
    UserRespDto toUserDto(User user);

    @Mapping(source = "user.role.name", target = "roleName")
    @Mapping(source = "user.createdBy.username", target = "createdBy")
    @Mapping(source = "user.updatedBy.username", target = "updatedBy")
    List<UserRespDto> toUserDto(List<User> user);
}