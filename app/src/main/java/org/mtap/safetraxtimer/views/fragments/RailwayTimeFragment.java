package org.mtap.safetraxtimer.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mtap.safetraxtimer.R;
import org.mtap.safetraxtimer.utils.DateUtils;
import org.mtap.safetraxtimer.views.activities.TimerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RailwayTimeFragment extends Fragment {

    @BindView(R.id.current_date_view)
    TextView current_date_view;

    public RailwayTimeFragment() {
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
            if (DateUtils.TIME != 0)
                current_date_view.setText(DateUtils.getDate(DateUtils.TIME, DateUtils.RAILWAY_TIME_FORMAT));
            else
                current_date_view.setText(getContext().getResources().getString(R.string.get_time_error));

    }
}
