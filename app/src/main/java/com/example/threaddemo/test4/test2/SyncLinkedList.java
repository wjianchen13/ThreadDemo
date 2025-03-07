package com.example.threaddemo.test4.test2;

import java.util.LinkedList;
import java.util.List;

public class SyncLinkedList<T> {

    private List<T> mQueue;

    public SyncLinkedList(){
        mQueue = new LinkedList<>();
    }


}
