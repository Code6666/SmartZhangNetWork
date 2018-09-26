package com.base.network.basenetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.base.network.basenetwork.baselib.BasePresenter;
import com.base.network.basenetwork.test.UpdatePresenter;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        new UpdatePresenter().getAppUpData();
    }
}
