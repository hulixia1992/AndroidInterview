package com.hulixia1992.interview.androidinterview.data;

import com.hulixia1992.interview.androidinterview.acitivty.MainActivity;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by hulixia on 2016/9/21.
 */
@Component(modules = HashMapModule.class)
public interface ContentComponent {
    void inject(MainActivity mainActivity);
}
