/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.commons.entities;

import java.util.Objects;

/**
 * Encapsulates all user profile characteristics.
 */

public class UserProfile {

    public SizeProfile opticalSizeProfile;
    public ColorProfile colorProfile;
    public boolean handProfileAuto = true;
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
                opticalSizeProfile == null ? "null" : opticalSizeProfile.name(),
                colorProfile == null ? "null" : colorProfile.name(),
                handProfileAuto,
                isRightHanded,
                notification == null ? "null" : notification.name(),
                age,
                genderInfo);
    }
}
