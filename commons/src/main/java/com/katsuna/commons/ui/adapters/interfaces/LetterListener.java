package com.katsuna.commons.ui.adapters.interfaces;

import com.katsuna.commons.entities.UserProfile;

public interface LetterListener {

    void selectItemByStartingLetter(String letter);

    UserProfile getUserProfile();
}
