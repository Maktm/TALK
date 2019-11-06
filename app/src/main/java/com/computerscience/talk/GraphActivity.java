package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // Get the line chart object
        LineChart chart = (LineChart)findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0)
                entries.add(new Entry(i, -i));
            else
                entries.add(new Entry(i, i));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Mood");
        dataSet.setCircleColor(50);
        dataSet.setValueTextColor(100);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }
}
