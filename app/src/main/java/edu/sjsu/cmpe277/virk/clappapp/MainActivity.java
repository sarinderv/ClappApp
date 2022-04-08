package edu.sjsu.cmpe277.virk.clappapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView proxtv;
    TextView acctv;
    SensorManager sm;
    Sensor proxsensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proxtv = (TextView) findViewById(R.id.prox_text_view);
        acctv = (TextView) findViewById(R.id.acc_text_view);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        proxsensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sm.registerListener(this, proxsensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        proxtv.setText(String.valueOf(sensorEvent.values[0]));
        playSound();
    }

    private void playSound() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        acctv.setText(sensor.getName() +": "+ String.valueOf(i));
    }
}