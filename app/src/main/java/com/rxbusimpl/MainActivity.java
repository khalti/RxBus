package com.rxbusimpl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.rxbus.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.btnAdd1)
    Button btnAdd1;
    @BindView(R.id.btnAdd2)
    Button btnAdd2;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();
        /*compositeDisposable.add(RxBus.getInstance().register(event -> {
            if (event.getTag().equals("add_1")) {
                txt.setText((String) event.getValue());
            } else if (event.getTag().equals("add_2")) {
                txt.setText((String) event.getValue());
            }
        }));*/

        compositeDisposable.add(RxBus.getInstance().register("add_1", event -> {
            Log.i("tag", event.getTag());
//            txt.setText(event.getValue());
        }));
        compositeDisposable.add(RxBus.getInstance().register("add_2", event -> {
            Log.i("tag", event.getTag());
            txt.setText((String) event.getValue());
        }));

        btnAdd1.setOnClickListener(view -> RxBus.getInstance().post("add_1", (Integer.parseInt(txt.getText() + "") + 1) + ""));
        btnAdd2.setOnClickListener(view -> RxBus.getInstance().post("add_2", (Integer.parseInt(txt.getText() + "") + 2) + ""));
    }
}
