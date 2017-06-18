package com.katsuna.commons.entities;

import java.util.Objects;

/**
 * Encapsulates all user profile characteristics.
 */

public class UserProfile {

    public SizeProfile opticalSizeProfile;
    public ColorProfile colorProfile;
    public boolean handProfileAuto;
    public boolean isRightHanded;
    public Notification notification;
    public String age;
    public GenderInfo genderInfo;

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
                handProfileAuto == userProfile.handProfileAuto &&
                isRightHanded == userProfile.isRightHanded;
    }

    @Override
    public int hashCode() {
        return Objects.hash(opticalSizeProfile, colorProfile, isRightHanded);
    }

    @Override
    public String toString() {
        return String.format("UserProfile[opticalSizeProfile=%s, colorProfile=%s, " +
                        " handProfileAuto=%s, isRightHanded=%s, notification=%s, age=%s," +
                        " genderInfo=%s]",
                opticalSizeProfile.name(),
                colorProfile.name(),
                handProfileAuto,
                isRightHanded,
                notification.name(),
                age,
                genderInfo);
    }
}
