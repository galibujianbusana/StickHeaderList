package com.example.stickheaderlist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TestStickAdapter  extends RecyclerView.Adapter<TestStickAdapter.TestViewHolder> {

    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;
    private Context context;
    private List<TestData> data;

    public TestStickAdapter(Context context, List<TestData> data) {
        this.context = context;
        this.data = data;
    }

    public void updateData(List<TestData> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        TestViewHolder holder1 = (TestViewHolder) holder;
        holder1.bindView(data.get(position), position);

        if (position == 0) {
            holder1.textViewTitle.setVisibility(View.VISIBLE);
            holder1.itemView.setTag(FIRST_STICKY_VIEW);
        } else {
            if (!data.get(position).title.equals(data.get(position - 1).title)) {
                holder1.textViewTitle.setVisibility(View.VISIBLE);
                holder1.textViewTitle.setText(data.get(position).title);
                holder1.itemView.setTag(HAS_STICKY_VIEW);
            } else {
                holder1.textViewTitle.setVisibility(View.GONE);
                holder1.itemView.setTag(NONE_STICKY_VIEW);
                holder1.textViewTitle.setText(data.get(position).title);

            }
        }
        holder1.textViewTitle.setText(data.get(position).title);
        holder.textViewTitle.getPaint().setFakeBoldText(true);
        holder1.itemView.setContentDescription(data.get(position).title);

    }

    @Override
    public int getItemCount() {
        return  data.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textView;
        private View itemView;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            this.itemView = itemView;
        }
        public void bindView(final TestData data, int position) {
            textView.setText(data.contract);
            textViewTitle.setText(data.title);
        }

    }
}
