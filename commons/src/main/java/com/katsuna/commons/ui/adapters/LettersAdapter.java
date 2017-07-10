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
        holder.bind(letter);
    }

    @Override
    public int getItemCount() {
        return mLetters.size();
    }

}
