package com.rxbusimpl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.rxbus.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.btnAdd1)
    Button btnAdd1;
    @BindView(R.id.btnAdd2)
    Button btnAdd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RxBus.getInstance().register(event -> {
            if (event.getTag().equals("add_1")) {
                txt.setText((String) event.getEvent());
            } else if (event.getTag().equals("add_2")) {
                txt.setText((String) event.getEvent());
            }
        });

        btnAdd1.setOnClickListener(view -> RxBus.getInstance().post("add_1", (Integer.parseInt(txt.getText() + "") + 1) + ""));
        btnAdd2.setOnClickListener(view -> RxBus.getInstance().post("add_2", (Integer.parseInt(txt.getText() + "") + 2) + ""));
    }
}
