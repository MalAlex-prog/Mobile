package ru.mirea.malyshev.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApparatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApparatFragment extends Fragment implements SensorEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView pressureTextView;
    private TextView proximityTextView;
    private TextView ambientTextView;
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Sensor proximitySensor;
    private Sensor ambientSensor;

    public ApparatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApparatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApparatFragment newInstance(String param1, String param2) {
        ApparatFragment fragment = new ApparatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        SensorManager sensorManager =
                (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        pressureSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_PRESSURE);
        proximitySensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_PROXIMITY);
        ambientSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        pressureTextView = findViewById(R.id.Pressure);
        proximityTextView = findViewById(R.id.Proximity);
        ambientTextView = findViewById(R.id.Ambient);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apparat, container, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, pressureSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, ambientSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float value = event.values[0];
            pressureTextView.setText(value);
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float value = event.values[0];
            proximityTextView.setText(value);
        }

        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float value = event.values[0];
            ambientTextView.setText(value);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}