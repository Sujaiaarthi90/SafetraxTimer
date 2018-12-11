package org.mtap.safetraxtimer.utils;

import android.os.StrictMode;

import org.apache.commons.net.time.TimeTCPClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

    public static final String RAILWAY_TIME_FORMAT = "EEEE, dd MMM, yyyy HH:mm:ss";

    public static final String NORMAL_TIME_FORMAT = "EEEE, dd MMM, yyyy hh:mm aa";

    public static long sTime;

    public static String getDate(long time, String timeFormat) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        SimpleDateFormat month_date = new SimpleDateFormat(timeFormat);
        String date = month_date.format(cal.getTime()).toString();
        System.out.println(date);
        return date;
    }

    public static long getsTime() {
        long time = 0;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            TimeTCPClient client = new TimeTCPClient();
            try {
                client.setDefaultTimeout(60000);
                client.connect("time.nist.gov");
                time = client.getDate().getTime();
            } finally {
                client.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }
}
