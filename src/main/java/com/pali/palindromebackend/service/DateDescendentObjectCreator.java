package com.pali.palindromebackend.service;

import com.pali.palindromebackend.model.DashboardLaunchDetail;
import com.pali.palindromebackend.model.DateDescendObject;
import com.pali.palindromebackend.model.RequiredFriendDetailObject;
import com.pali.palindromebackend.model.ResponseCommunityBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Mr.Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 7/17/2022
 **/
@Service
public class DateDescendentObjectCreator {
    public List<DateDescendObject> giveDateDescendentObjet(
            ArrayList<DashboardLaunchDetail> launches,
            ArrayList<ResponseCommunityBody> communities,
            ArrayList<RequiredFriendDetailObject> friends
    ){
        List<DateDescendObject> dateDescendObjects = new ArrayList<>();
        return dateDescendObjects;
    }
}
