package com.example.Galeria2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

/**
 * Fragment containing {@link DrawingView}.
 * @author Ala
 * @since 09-05-2015
 * */

 public class DrawingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drawingfragment, container, false);
    }
}