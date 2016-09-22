package com.hulixia1992.interview.androidinterview.acitivty;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.EditText;

import com.hulixia1992.interview.androidinterview.BaseActivity;
import com.hulixia1992.interview.androidinterview.R;

import butterknife.InjectView;

public class LoginActivity extends BaseActivity {
    @InjectView(R.id.fab_login_change)
    FloatingActionButton fabLogin;
    @InjectView(R.id.et_login_password)
    EditText etPass;
    @InjectView(R.id.et_login_username)
    EditText etName;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide_login);
        getWindow().setEnterTransition(slide);
        initView();
        initListener();
    }

    private void initListener() {
        fabLogin.setOnClickListener((v) -> {
            getWindow().setExitTransition(null);
            getWindow().setEnterTransition(null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, fabLogin, fabLogin.getTransitionName());
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class), options.toBundle());
            } else {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initView() {
        mToolbar.setTitle("登录");
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }
}
