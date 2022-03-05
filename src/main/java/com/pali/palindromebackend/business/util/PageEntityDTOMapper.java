package com.pali.palindromebackend.business.util;


import com.pali.palindromebackend.dao.UserDAO;
import com.pali.palindromebackend.dto.PageDTO;
import com.pali.palindromebackend.entity.Page;
import com.pali.palindromebackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 19/06/2021
 **/
@Mapper(componentModel = "spring", uses = {EntityDTOMapper.class})
public abstract class PageEntityDTOMapper{
    @Autowired
    private UserDAO userDAO;

    @Mapping(source = ".", target = "user")
    public abstract Page getPage(PageDTO dto);

    @Mapping(source = "user", target = "user", qualifiedByName = "User")
    public abstract PageDTO getPageDTO(Page page);

    public User getUser(PageDTO dto) {
        return userDAO.getOne(dto.getUser());
    }

    @Named("User")
    public int getUserName(User user) {
        return user.getId();
    }
}
