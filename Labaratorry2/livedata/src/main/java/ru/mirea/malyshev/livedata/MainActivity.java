package ru.mirea.malyshev.livedata;

import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.malyshev.labaratory2.TimeLiveData
import android.os.Bundle;
import android.util.Log;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkNameTextView = findViewById(R.id.textView);
        TimeLiveData.getTime().observe(this, this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TimeLiveData.setTime();
            }
        }, 5000);
    }
    @Override
    public void onChanged(@Nullable Long s) {
        Log.d(MainActivity.class.getSimpleName(), s + "");
        networkNameTextView.setText("" + s);
    }
}