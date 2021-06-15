package com.pali.palindromebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 28/04/2021
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = {"id","username"}))
@Data
public class User implements SuperEntity {
    @Column(name = "username",updatable = false)
    private String username;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private boolean isActive;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password shouldn't be a blank")
    @ColumnDefault("'dubfiskdnhfjsd2fajsfioad7jfkmadrfj9pqeor90ru2jfinfkjgndfoksodkfwa'")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;

    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Launch> launches;

    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
