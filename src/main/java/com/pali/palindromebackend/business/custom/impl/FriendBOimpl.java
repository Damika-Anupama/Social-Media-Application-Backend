package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.FriendBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.business.util.FriendEntityDTOMapper;
import com.pali.palindromebackend.dao.FriendDAO;
import com.pali.palindromebackend.dto.FriendDTO;
import com.pali.palindromebackend.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
@Transactional
@Service
public class FriendBOimpl implements FriendBO {

    @Autowired
    private FriendDAO dao;
    @Autowired
    private FriendEntityDTOMapper mapper;

    public FriendBOimpl() {
    }


    @Override
    public void saveFriend(FriendDTO dto){
        Friend friend = mapper.getFriend(dto);
        // TODO: 7/15/2022 Remove below bad practice 
        friend.setAskedDate(dto.getAskedDate());
        dao.save(friend);
    }

    @Override
    public void updateFriend(FriendDTO dto){
        dao.save(mapper.getFriend(dto));
    }

    @Override
    public void deleteFriend(int friendshipId){
        dao.deleteById(friendshipId);
    }

    @Override
    public List<FriendDTO> getAllFriends(){
        return dao.findAll().stream().
                map(friend ->
                        mapper.getFriendDTO(friend))
                .collect(Collectors.toList()
                );
    }

    @Override
    public FriendDTO getFriend(int friendshipId){
        return dao.findById(
                friendshipId).map(friend -> mapper.getFriendDTO(friend)
        ).get();
    }

    @Override
    public boolean findWhetherFriendshipExist(FriendDTO dto) {
        return dao.findFriendship(
                dto.getFriend1(),
                dto.getFriend2()
        ) != null;
    }

    @Override
    public List<FriendDTO> getAllFriendsByUserId(int userId) {
        List<FriendDTO> collect1 = dao.findAllfriend1(userId).stream().
                map(friend -> mapper.getFriendDTO(friend)).collect(Collectors.toList());
        List<FriendDTO> collect2 = dao.findAllfriend2(userId).stream().
                map(friend -> mapper.getFriendDTO(friend)).collect(Collectors.toList());
        List<FriendDTO> newList = Stream.concat(collect1.stream(), collect2.stream())
                .collect(Collectors.toList());
        return newList;
    }
}
