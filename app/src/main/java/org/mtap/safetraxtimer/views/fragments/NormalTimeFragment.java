package org.mtap.safetraxtimer.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mtap.safetraxtimer.R;

import static org.mtap.safetraxtimer.views.activities.TimerActivity.NORMAL_TIME_FORMAT;
import static org.mtap.safetraxtimer.views.activities.TimerActivity.TIME;
import static org.mtap.safetraxtimer.views.activities.TimerActivity.getDate;

public class NormalTimeFragment extends Fragment {
    TextView current_date_view;

    public NormalTimeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal_time, container, false);
        current_date_view = view.findViewById(R.id.current_date_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (current_date_view != null)
            current_date_view.setText(getDate(TIME, NORMAL_TIME_FORMAT));
    }
}
