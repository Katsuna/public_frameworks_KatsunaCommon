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
package com.katsuna.commons.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katsuna.commons.R;
import com.katsuna.commons.ui.adapters.holders.LetterViewHolder;
import com.katsuna.commons.ui.adapters.interfaces.LetterListener;

import java.util.List;

public class LettersAdapter extends RecyclerView.Adapter<LetterViewHolder> {

    private List<String> mLetters;
    private LetterListener mLetterListener;

    public LettersAdapter(List<String> letters, LetterListener letterListener) {
        mLetters = letters;
        mLetterListener = letterListener;
    }

    @Override
    public LetterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_letter, parent, false);

        return new LetterViewHolder(itemView, mLetterListener);
    }

    @Override
    public void onBindViewHolder(LetterViewHolder holder, int position) {
        String letter = mLetters.get(position);
        holder.bind(letter, position);
    }

    @Override
    public int getItemCount() {
        return mLetters.size();
    }

}
