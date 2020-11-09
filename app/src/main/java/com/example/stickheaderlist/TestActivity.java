package com.example.stickheaderlist;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TestActivity extends AppCompatActivity {

    RecyclerView recycleView;
    private TestStickAdapter adapter;
    List<TestData> data = new ArrayList<>();
    private TextView textViewTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        recycleView = findViewById(R.id.recycleView);
        recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        textViewTitle = findViewById(R.id.textViewTitle);
        for (int i = 0; i < 100; i ++ ){
            String titleNum = (i / 10) + "";
            data.add(new TestData("标题" + titleNum, "内容详情"+i));
        }


        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(manager);


        adapter = new TestStickAdapter(this,data);

        recycleView.setAdapter(adapter);


        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickyInfoView = recyclerView.findChildViewUnder(
                        textViewTitle.getMeasuredWidth() / 2, 5);
                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    textViewTitle.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }
                View transInfoView = recyclerView.findChildViewUnder(
                        textViewTitle.getMeasuredWidth() / 2, textViewTitle.getMeasuredHeight() + 1);
                if (transInfoView != null && transInfoView.getTag() != null) {

                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - textViewTitle.getMeasuredHeight();

                    if (transViewStatus == adapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            textViewTitle.setTranslationY(dealtY);
                        } else {
                            textViewTitle.setTranslationY(0);
                        }
                    } else if (transViewStatus == adapter.NONE_STICKY_VIEW) {
                        textViewTitle.setTranslationY(0);
                    }
                }
            }
        });
    }

}