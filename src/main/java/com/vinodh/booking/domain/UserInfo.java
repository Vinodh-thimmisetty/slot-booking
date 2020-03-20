package com.vinodh.booking.domain;

import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thimmv
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    private Long userId;
    private String loginId, firstName, lastName, email, phone;
    public Slot slot;
    private boolean isAdmin;

    public UserInfo(final User user) {
        if (user != null) {
            this.isAdmin = user.isAdmin();
            this.loginId = user.getLoginId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.slot = user.getSlot();
        }
    }
}
