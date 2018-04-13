package com.github.htdangkhoa.blogmvvmandroid.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.htdangkhoa.blogmvvmandroid.R;
import com.github.htdangkhoa.blogmvvmandroid.util.Utils;
import com.github.htdangkhoa.blogmvvmandroid.view.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.Fragment.addFragment(this, new HomeFragment(), false,
                R.id.frameLayout, Bundle.EMPTY, Utils.Fragment.Anim.NONE);
    }

    @Override
    public void onBackPressed() {
        Utils.Fragment.popBackStack(this);
    }
}
