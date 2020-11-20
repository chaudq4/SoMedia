package com.chauduong.somedia;

import android.os.Bundle;
import android.view.MenuItem;

import com.chauduong.somedia.adapter.Util;
import com.chauduong.somedia.keycode.Constant;
import com.chauduong.somedia.model.User;
import com.chauduong.somedia.session.SessionManager;
import com.chauduong.somedia.ui.examination.ExaminationFragment;
import com.chauduong.somedia.ui.home.HomeFragment;
import com.chauduong.somedia.ui.message.MessFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_bottom);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        CoordinatorLayout.LayoutParams layoutParam = (CoordinatorLayout.LayoutParams) navView.getLayoutParams();
        layoutParam.setBehavior(new BottomNavigationBehavior());
        loadFragment(new HomeFragment());
        initData();
    }

    private void initData() {
        mUser = (User) getIntent().getSerializableExtra(Constant.KEY_USER_LOGIN);
        Util.setmUser(mUser);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_message:
                fragment = new MessFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_examination:
                fragment = new ExaminationFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_setting:
                SessionManager.getInstance(this).setIsLogin(false);
                finish();
                break;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}