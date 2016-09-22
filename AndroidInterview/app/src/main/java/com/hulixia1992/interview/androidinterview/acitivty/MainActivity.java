package com.hulixia1992.interview.androidinterview.acitivty;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.hulixia1992.interview.androidinterview.BaseActivity;
import com.hulixia1992.interview.androidinterview.R;
import com.hulixia1992.interview.androidinterview.acitivty.inter.IMainView;
import com.hulixia1992.interview.androidinterview.adapter.ContentAdapter;
import com.hulixia1992.interview.androidinterview.data.DaggerContentComponent;
import com.hulixia1992.interview.androidinterview.presenter.MainPresentet;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements IMainView {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.pw_main_progress)
    ProgressWheel mProgressWheel;
    @InjectView(R.id.rv_main_content)
    RecyclerView rvContent;
    @InjectView(R.id.sl_main_refresh)
    SwipeRefreshLayout mRefreshLayout;
    @InjectView(R.id.fb_main_edit)
    FloatingActionButton fabButton;

    private ContentAdapter mContentAdapter;
    private MainPresentet mPresentet;

    @Inject
    List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        DaggerContentComponent.builder().build().inject(this);
        initToolbar();
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        fabButton.setOnClickListener((v) -> {
            showSnackToast(v);
        });
        mRefreshLayout.setOnRefreshListener(() -> {
            new Thread() {
                @Override
                public void run() {
                    //super.run();
                    try {
                        Thread.sleep(500);
                        runOnUiThread(() -> {
                            mRefreshLayout.setRefreshing(false);

                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        });
    }

    private void initView() {
        rvContent.setHasFixedSize(true);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setAdapter(mContentAdapter);
        mRefreshLayout.setColorSchemeColors(getColorAccent());

    }

    private void initData() {
        mPresentet = new MainPresentet(this, this);
        //    Log.i("hulixia",mPresentet.getTitleList().toString());
        mContentAdapter = new ContentAdapter(titleList, this);
        mContentAdapter.setContentItemClick(() -> {
        });

    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    private void showSnackToast(View view) {

        Snackbar.make(view, "是否要编辑个人简历?", Snackbar.LENGTH_SHORT).setAction("是", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this, "将跳转到个人信息", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    private void initToolbar() {
        mToolbar.setBackgroundColor(getColorPrimary());
        mToolbar.setLogo(getDrawable(R.drawable.header));
        mToolbar.setTitle("Android 面试题");
        mToolbar.setTitleTextColor(0xffffffff);
        mToolbar.setSubtitleTextColor(0xffffffff);
        mToolbar.inflateMenu(R.menu.menu_main);
        View logoView = getToolbarLogoIcon(mToolbar);
        logoView.setOnClickListener((v) -> {
            //TODO
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide_login);
            getWindow().setExitTransition(slide);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }

    public static View getToolbarLogoIcon(Toolbar toolbar) {
        //check if contentDescription previously was set
        boolean hadContentDescription = android.text.TextUtils.isEmpty(toolbar.getLogoDescription());
        String contentDescription = String.valueOf(!hadContentDescription ? toolbar.getLogoDescription() : "logoContentDescription");
        toolbar.setLogoDescription(contentDescription);
        ArrayList<View> potentialViews = new ArrayList<View>();
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(potentialViews, contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
        View logoIcon = null;
        if (potentialViews.size() > 0) {
            logoIcon = potentialViews.get(0);
        }
        //Clear content description if not previously present
        if (hadContentDescription)
            toolbar.setLogoDescription(null);
        return logoIcon;
    }
}
