package org.mtap.safetraxtimer.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.mtap.safetraxtimer.R;
import org.mtap.safetraxtimer.views.activities.TimerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static org.mtap.safetraxtimer.views.activities.TimerActivity.NORMAL_TIME_FORMAT;
import static org.mtap.safetraxtimer.views.activities.TimerActivity.TIME;
import static org.mtap.safetraxtimer.views.activities.TimerActivity.getDate;

public class NormalTimeFragment extends Fragment {

    @BindView(R.id.current_date_view)
    TextView current_date_view;

    public NormalTimeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal_time, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @OnClick(R.id.current_time_button)
    public void onGetCurrentTimetClick(View view) {
        ((TimerActivity) getContext()).onResume();
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (current_date_view != null)
            current_date_view.setText(getDate(TIME, NORMAL_TIME_FORMAT));
    }
}
