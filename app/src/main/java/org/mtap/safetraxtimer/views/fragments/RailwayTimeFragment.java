package org.mtap.safetraxtimer.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mtap.safetraxtimer.R;

public class RailwayTimeFragment extends Fragment {
    public RailwayTimeFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_railway_time, container, false);
        return view;
    }
}
