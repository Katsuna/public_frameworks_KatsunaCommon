package com.katsuna.commons.entities;

import java.util.Objects;

/**
 * Encapsulates all user profile characteristics.
 */

public class UserProfile {

    public SizeProfile opticalSizeProfile;
    public ColorProfile colorProfile;
    public boolean isRightHanded;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof UserProfile)) {
            return false;
        }
        UserProfile userProfile = (UserProfile) obj;
        return opticalSizeProfile == userProfile.opticalSizeProfile &&
                colorProfile == userProfile.colorProfile &&
                isRightHanded == userProfile.isRightHanded;
    }

    @Override
    public int hashCode() {
        return Objects.hash(opticalSizeProfile, colorProfile, isRightHanded);
    }
}
