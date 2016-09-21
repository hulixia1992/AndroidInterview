package com.hulixia1992.interview.androidinterview.presenter;

import com.hulixia1992.interview.androidinterview.acitivty.MainActivity;
import com.hulixia1992.interview.androidinterview.acitivty.inter.IMainView;
import com.hulixia1992.interview.androidinterview.data.ContentComponent;
import com.hulixia1992.interview.androidinterview.data.DaggerContentComponent;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hulixia on 2016/9/21.
 */
public class MainPresentet {
    private IMainView mView;
    private MainActivity mActivity;
    /**
     * 面试的所有数据
     */
    private HashMap<String, List<String>> dataMaps;
    @Inject
    List<String> titleList;

    public MainPresentet(IMainView mainView, MainActivity mainActivity) {
        mView = mainView;
        this.mActivity = mainActivity;
        // ContentComponent.
    //    DaggerContentComponent.builder().build().inject(mainActivity);
    }

    public List<String> getTitleList() {
        return titleList;
    }
}
