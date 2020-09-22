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
 * Contains UserProfiles from multiple sources and provides the proper one based on their values.
 */

public class UserProfileContainer {

    private final UserProfile profileFromKatsunaServices;
    private final UserProfile profileFromAppSettings;

    public UserProfileContainer(UserProfile profileFromKatsunaServices,
                                UserProfile profileFromAppPreferences) {
        this.profileFromKatsunaServices = profileFromKatsunaServices;
        this.profileFromAppSettings = profileFromAppPreferences;
    }

    /**
     * App settings override the one from katsuna services if not set to auto.
     * Default profile setting set to Intermediate.
     * @return profile type for optical size
     */
    public SizeProfile getOpticalSizeProfile() {
        if (profileFromAppSettings.opticalSizeProfile == SizeProfile.AUTO) {
            if (profileFromKatsunaServices != null) {
                // katsuna services value
                return profileFromKatsunaServices.opticalSizeProfile;
            } else {
                // Default value
                return SizeProfile.INTERMEDIATE;
            }
        } else {
            // app setting value
            return profileFromAppSettings.opticalSizeProfile;
        }
    }

    /**
     * App settings override the one from katsuna services if not set to auto.
     * Default profile setting set to Main.
     * @return profile type for optical size
     */
    public ColorProfile getColorProfile() {
        if (profileFromAppSettings.colorProfile == ColorProfile.AUTO) {
            if (profileFromKatsunaServices != null) {
                // katsuna services value
                return profileFromKatsunaServices.colorProfile;
            } else {
                // Default value
                return ColorProfile.COLOR_IMPAIREMENT;
            }
        } else {
            // app setting value
            return profileFromAppSettings.colorProfile;
        }
    }

    /**
     * Katsuna services setting override app setting.
     * @return right hand setting.
     */
    public boolean isRightHanded() {
        if (profileFromKatsunaServices != null) {
            return profileFromAppSettings.handProfileAuto ? profileFromKatsunaServices.isRightHanded
                    : profileFromAppSettings.isRightHanded;

        } else {
            return profileFromAppSettings.isRightHanded;
        }
    }

    public boolean hasKatsunaServices() {
        return profileFromKatsunaServices != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof UserProfileContainer)) {
            return false;
        }
        UserProfileContainer userProfileContainer = (UserProfileContainer) obj;

        if (profileFromKatsunaServices != null) {
            return profileFromKatsunaServices.equals(userProfileContainer.profileFromKatsunaServices) &&
                    profileFromAppSettings.equals(userProfileContainer.profileFromAppSettings);
        } else {
            return profileFromAppSettings.equals(userProfileContainer.profileFromAppSettings);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileFromKatsunaServices, profileFromAppSettings);
    }


    public UserProfile getProfileFromKatsunaServices() {
        return profileFromKatsunaServices;
    }

    public UserProfile getActiveUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.opticalSizeProfile = getOpticalSizeProfile();
        userProfile.colorProfile = getColorProfile();
        userProfile.isRightHanded = isRightHanded();
        return userProfile;
    }

}
