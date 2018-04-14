package com.selfdev.googlemapstut;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.selfdev.googlemapstut.linkedlist.LinkedList;

/**
 * Created by Rahul on 07/04/18.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_container_layout);
        LinkedList.SinglyLinkedList<Integer> integerSinglyLinkedList = new LinkedList().new SinglyLinkedList<>();

        integerSinglyLinkedList.addNewNode(1);
        integerSinglyLinkedList.addNewNode(2);
        integerSinglyLinkedList.addNewNode(3);
        integerSinglyLinkedList.addNewNode(4);
        integerSinglyLinkedList.addNewNode(5);
        integerSinglyLinkedList.addNewNode(6);
        integerSinglyLinkedList.addNewNode(7);

//        integerSinglyLinkedList.gtNodeAtPosition(5);
    }
}
