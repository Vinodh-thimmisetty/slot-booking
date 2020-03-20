package com.vinodh.booking.entity;

import com.vinodh.booking.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    private String loginId, firstName, lastName, email, phone;

    private boolean isAdmin; // Only Managers and above are allowed to book the available SLOT
//    private String sectionName;
//    private String slotId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    public Slot slot;

    @Version
    private long version;

    public User(final UserInfo userInfo) {
        if (userInfo != null) {
            this.userId = userInfo.getUserId();
            this.loginId = userInfo.getLoginId();
            this.firstName = userInfo.getFirstName();
            this.lastName = userInfo.getLastName();
            this.email = userInfo.getEmail();
            this.phone = userInfo.getPhone();
            this.slot = userInfo.getSlot();
        }
    }
}