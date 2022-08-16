package com.pali.palindromebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 15/06/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendDTO implements SuperDTO {
    private int friendshipId;
    private int friend1;
    private int friend2;
    private Date askedDate;
    private boolean isConfirmed;
    private Date friendshipDate;
    private boolean isBlocked;
    private String blockedBy;
    private Date blockedDate;

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Date getAskedDate() {
        return askedDate;
    }

    public void setAskedDate(Date askedDate) {
        this.askedDate = askedDate;
    }
}
 /*
    Friend Logic

    * friend1 = first user who asked for the friendship
    * friend2 = second user who needs to confirm the friendship
    * askedDate = the date first user asked for the friendship
    * isConfirmed = True after second user accepts the friendship
    * friendshipDate = second user confirmed date
    * isBlocked = whether the friendship has blocked
    * blockedBy = friend1 or friend2 (requested person)
    * blockedDate
    * */