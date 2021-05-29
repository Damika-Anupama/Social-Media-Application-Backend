package com.pali.palindromebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Data
public class User implements SuperEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    @Column(updatable = false)
    private String username;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password shouldn't be a blank")
    @ColumnDefault("'dubfiskdnhfjsd2fajsfioad7jfkmadrfj9pqeor90ru2jfinfkjgndfoksodkfwa'")
    private String password;
    private boolean isActive;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
