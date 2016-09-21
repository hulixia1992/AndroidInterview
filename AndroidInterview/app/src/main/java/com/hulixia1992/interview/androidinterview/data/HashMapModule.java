package com.hulixia1992.interview.androidinterview.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hulixia on 2016/9/21.
 */
@Module
public class HashMapModule {
    private List<String> titleList;
    private HashMap<String, ItemData> hashData;

    @Provides
    public HashMap<String, ItemData> provideHashData() {


        return hashData;
    }

    private void initData() {
        hashData = new HashMap<>();
        initAndoridData();

    }

    private void initAndoridData() {
        ItemData itemData = new ItemData();
        List<String> smallTitle = new ArrayList<>();
        List<ItemData.ItemSmallData> smallDatas = new ArrayList<>();

        smallTitle.add("Android基础知识");
        ItemData.ItemSmallData smallData = new ItemData.ItemSmallData();
        smallData.itemTitle = "Android基础知识";
        smallData.itemUrl = "https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.md";


        smallTitle.add("Android内存泄漏总结");
        smallTitle.add("Handler内存泄漏分析及解决");
        smallTitle.add("Android性能优化");
        smallTitle.add("ListView详解");
        smallTitle.add("RecyclerView和ListView的异同");
        itemData.itemTitles = smallTitle;
        itemData.mSmallDatas = smallDatas;


        hashData.put("Android(安卓)", itemData);
    }

    private void initTitleList() {
        titleList = new ArrayList<>();
        titleList.add("Android(安卓)");
        titleList.add("DesignPattern(设计模式)");
        titleList.add("JavaSE(Java基础)");
        titleList.add("JVM(Java虚拟机)");
        titleList.add("JavaConcurrent(Java并发)");
        titleList.add("DataStructure(数据结构)");
        titleList.add("Algorithm(算法)");
        titleList.add("Network(网络)");
        titleList.add("OperatingSystem(操作系统)");
        titleList.add("ReadingNotes(读书笔记)");
        titleList.add("InterviewExperience(面试经验)");
    }

    @Provides
    public List<String> provideTitleList() {
        if (titleList == null) {
            initTitleList();
        }
        return titleList;
    }
}
