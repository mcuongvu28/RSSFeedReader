package com.vumanhcuong.rssfeedreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vumanhcuong.rssfeedreader.R;
import com.vumanhcuong.rssfeedreader.models.Item;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {

    private Context mContext;
    private int mLayout;
    private List<Item> mItems;

    public ChannelAdapter(Context context, int layout, List<Item> items) {
        mContext = context;
        mLayout = layout;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mLayout, null);

            holder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.pubDate = (TextView) convertView.findViewById(R.id.tvPubDate);
//            holder.description = (TextView) convertView.findViewById(R.id.tvDescription);
            holder.description = (WebView) convertView.findViewById(R.id.wvDescription);

            holder.description.setFocusable(false);
            holder.description.setClickable(false);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item item = mItems.get(position);
        holder.title.setText(item.getTitle());
        holder.pubDate.setText(item.getPubDate());
//        holder.description.setText(item.getDescription());
        holder.description.loadData(item.getDescription(), "text/html", "UTF-8");

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView pubDate;
        //        TextView description;
        WebView description;
    }
}
