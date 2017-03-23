package me.yezhou.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void basic(View view) {
        Intent intent = new Intent(this, RecyclerViewBasicActivity.class);
        startActivity(intent);
    }

    public void simple(View view) {
        Intent intent = new Intent(this, RecyclerViewSimpleActivity.class);
        startActivity(intent);
    }

    public void devider(View view) {
        Intent intent = new Intent(this, RecyclerViewDeviderActivity.class);
        startActivity(intent);
    }

    public void addHeaderAndFooter(View view) {
        Intent intent = new Intent(this, RecyclerViewHeaderFooterActivity.class);
        startActivity(intent);
    }

    public void itemTouch(View view) {
        Intent intent = new Intent(this, RecyclerViewItemTouchActivity.class);
        startActivity(intent);
    }

    public void setRefreshAndLoad(View view) {
        Intent intent = new Intent(this, RecyclerViewRefreshLoadActivity.class);
        startActivity(intent);
    }
}
