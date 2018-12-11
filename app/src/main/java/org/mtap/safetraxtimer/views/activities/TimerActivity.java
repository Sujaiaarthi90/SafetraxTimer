package org.mtap.safetraxtimer.views.activities;

import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.net.time.TimeTCPClient;
import org.mtap.safetraxtimer.R;
import org.mtap.safetraxtimer.views.fragments.NormalTimeFragment;
import org.mtap.safetraxtimer.views.fragments.RailwayTimeFragment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimerActivity extends AppCompatActivity {

    public static final String RAILWAY_TIME_FORMAT = "EEEE, dd MMM, yyyy HH:mm:ss";

    public static final String NORMAL_TIME_FORMAT = "EEEE, dd MMM, yyyy hh:mm aa";

    public static long TIME;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_clock_select,
            R.drawable.ic_clock_select};
    private int[] tabUnSelectedIcons = {
            R.drawable.ic_clock_unselect,
            R.drawable.ic_clock_unselect};

    private int[] tabTitles = {R.string.normal_time, R.string.railway_time};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            if (i == 0) {
                final LinearLayout tabLinearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                TextView tabContent = (TextView) tabLinearLayout.findViewById(R.id.tabContent);
                tabContent.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                tabContent.setText(getApplicationContext().getResources().getString(tabTitles[i]));
                tabContent.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[i], 0, 0);
                tabLayout.getTabAt(i).setCustomView(tabContent);
            } else {
                final LinearLayout tabLinearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                TextView tabContent = (TextView) tabLinearLayout.findViewById(R.id.tabContent);
                tabContent.setTextColor(getResources().getColorStateList(R.color.tab_text_grey));
                tabContent.setText(getApplicationContext().getResources().getString(tabTitles[i]));
                tabContent.setCompoundDrawablesWithIntrinsicBounds(0, tabUnSelectedIcons[i], 0, 0);
                tabLayout.getTabAt(i).setCustomView(tabContent);
            }
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tabContent = (TextView) tab.getCustomView();
                tabContent.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                tabContent.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[tab.getPosition()], 0, 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tabContent = (TextView) tab.getCustomView();
                tabContent.setTextColor(getResources().getColorStateList(R.color.tab_text_grey));
                tabContent.setCompoundDrawablesWithIntrinsicBounds(0, tabUnSelectedIcons[tab.getPosition()], 0, 0);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new NormalTimeFragment(), getResources().getString(R.string.normal_time));
        adapter.addFrag(new RailwayTimeFragment(), getResources().getString(R.string.railway_time));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static String getDate(long time, String timeFormat) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        SimpleDateFormat month_date = new SimpleDateFormat(timeFormat);
        String date = month_date.format(cal.getTime()).toString();
        System.out.println(date);
        return date;
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            TimeTCPClient client = new TimeTCPClient();
            try {
                client.setDefaultTimeout(60000);
                client.connect("time.nist.gov");
                TIME = client.getDate().getTime();
            } finally {
                client.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
