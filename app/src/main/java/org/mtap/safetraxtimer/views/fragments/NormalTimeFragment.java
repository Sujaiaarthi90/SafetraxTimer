package org.mtap.safetraxtimer.views.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.net.time.TimeTCPClient;
import org.mtap.safetraxtimer.R;

import java.io.IOException;

import static org.mtap.safetraxtimer.views.activities.TimerActivity.NORMAL_TIME_FORMAT;
import static org.mtap.safetraxtimer.views.activities.TimerActivity.getDate;

public class NormalTimeFragment extends Fragment {
    TextView current_date_view;

    public NormalTimeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal_time, container, false);
        current_date_view = view.findViewById(R.id.current_date_view);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            TimeTCPClient client = new TimeTCPClient();
            try {
                client.setDefaultTimeout(60000);
                client.connect("time.nist.gov");
                long time = client.getDate().getTime();

                current_date_view.setText(getDate(time, NORMAL_TIME_FORMAT));
            } finally {
                client.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
