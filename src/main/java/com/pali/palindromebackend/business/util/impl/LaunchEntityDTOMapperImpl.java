package com.pali.palindromebackend.business.util.impl;

import com.pali.palindromebackend.business.util.LaunchEntityDTOMapper;
import com.pali.palindromebackend.dto.LaunchDTO;
import com.pali.palindromebackend.entity.Launch;

import javax.annotation.Generated;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 11/06/2021
 **/
@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-06-06T18:10:44+0530",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
//@Component
public class LaunchEntityDTOMapperImpl extends LaunchEntityDTOMapper {
    @Override
    public Launch getLaunch(LaunchDTO dto) {
        if (dto == null){
            return null;
        }

        Launch launch = new Launch();
        try{
            launch.setUser(getUser(dto));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        launch.setId(dto.getId());
        launch.setDescription(dto.getDescription());
        launch.setFeeling(dto.getFeeling());
        return launch;
    }

    @Override
    public LaunchDTO getLaunchDTO(Launch launch) {
        if(launch == null){
            return null;
        }

        LaunchDTO dto = new LaunchDTO();

        dto.setUser(getUserName(launch.getUser()));
        dto.setId(launch.getId());
        dto.setDescription(launch.getDescription());
        dto.setMedia(launch.getMedia());
        dto.setFeeling(launch.getFeeling());
        return dto;
    }
}
