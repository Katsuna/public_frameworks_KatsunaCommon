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
    public ProfileType getOpticalSizeProfile() {
        if (profileFromAppSettings.opticalSizeProfile == ProfileType.AUTO) {
            if (profileFromKatsunaServices != null) {
                // katsuna services value
                return profileFromKatsunaServices.opticalSizeProfile;
            } else {
                // Default value
                return ProfileType.INTERMEDIATE;
            }
        } else {
            // app setting value
            return profileFromAppSettings.opticalSizeProfile;
        }
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


}
