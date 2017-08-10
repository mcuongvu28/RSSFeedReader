package com.vumanhcuong.rssfeedreader.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vumanhcuong.rssfeedreader.R;
import com.vumanhcuong.rssfeedreader.adapters.ChannelAdapter;
import com.vumanhcuong.rssfeedreader.models.Item;
import com.vumanhcuong.rssfeedreader.network.HttpHandler;
import com.vumanhcuong.rssfeedreader.utils.XMLParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ChannelActivity extends AppCompatActivity {
    private ListView mChannelListView;
    private ArrayList<Item> mItems;
    private ChannelAdapter mChannelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        mChannelListView = (ListView) findViewById(R.id.lvChannel);

        mItems = new ArrayList<>();

        mChannelAdapter = new ChannelAdapter(this, R.layout.channel_item, mItems);
        mChannelListView.setAdapter(mChannelAdapter);

        new LoadDataTask().execute("http://vnexpress.net/rss/so-hoa.rss");

        mChannelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChannelActivity.this, DetailsActivity.class);
                intent.putExtra("link", mItems.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    private class LoadDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return HttpHandler.getInstance().getData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLParser parser = XMLParser.getInstance();
            Document document = parser.getDocument(s, "UTF-8");
            NodeList node = document.getElementsByTagName("item");

            int length = node.getLength();
            for (int i = 0; i < length; i++) {
                Element element = (Element) node.item(i);

                Item item = new Item(
                        parser.getString(element, "title"),
                        parser.getString(element, "pubDate"),
                        parser.getString(element, "link"),
                        parser.getString(element, "description"));
                mItems.add(item);
                Log.i("Info", String.format("Add feed %d successfully!", i));
            }
            mChannelAdapter.notifyDataSetChanged();
        }
    }
}
