package sg.edu.rp.webservices.problemstatement_knowyourfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    ArrayList <Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;

    Button btnReadLater;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vPager = findViewById(R.id.viewpager1);
        btnReadLater = findViewById(R.id.btn_ReadLater);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new MyFragmentPagerAdapter(fm, al);
        vPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent i = new Intent(MainActivity.this, ScheduledNotificationReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 1, i, 0);
                Calendar now = Calendar.getInstance();
                now.add(Calendar.SECOND, 10);
                alarm.set(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), pi);
                finish();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_previous){
            if(vPager.getCurrentItem() > 0){
                vPager.setCurrentItem(vPager.getCurrentItem() - 1, true);
            }
        }
        else if(item.getItemId() == R.id.action_next){
            if (vPager.getCurrentItem() < adapter.getCount() - 1){
                vPager.setCurrentItem(vPager.getCurrentItem() + 1, false);
            }
        }
        else if(item.getItemId() == R.id.action_random){
            Random random = new Random();
            int page = random.nextInt(vPager.getChildCount());
            vPager.setCurrentItem(page, true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("page", vPager.getCurrentItem());
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        int page = sp.getInt("page", 1);
        vPager.setCurrentItem(page);
        super.onPostResume();
    }
}