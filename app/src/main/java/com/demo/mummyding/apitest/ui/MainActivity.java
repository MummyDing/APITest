package com.demo.mummyding.apitest.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demo.mummyding.apitest.R;
import com.demo.mummyding.apitest.adapter.NewsAdapter;
import com.demo.mummyding.apitest.cache.PolicyCache;
import com.demo.mummyding.apitest.model.PolicyBean;
import com.demo.mummyding.apitest.sax.SAXParse;
import com.yalantis.phoenix.PullToRefreshView;

import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private PullToRefreshView refreshView;
    private RequestQueue queue;
    private  List<PolicyBean> items = new ArrayList<>();
    private NewsAdapter adapter;
    private PolicyCache cache = new PolicyCache(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d("handle","cache"+items.size());
            cache.cache(items);
            adapter.notifyDataSetChanged();
            return false;
        }
    });
    private void initData(){
        cache = new  PolicyCache(this);
        items = cache.loadFromCache();
        listView = (ListView) findViewById(R.id.listnews);
         refreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        adapter = new NewsAdapter(MainActivity.this,R.layout.item_news,items);
        listView.setOnItemClickListener(adapter);
        listView.setAdapter(adapter);
        if(items.size() == 0 ){
            loadDataFromNet();
        }else{
            Log.d("size",items.size()+"");
        }
        refreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshView.post(new Runnable() {
                    @Override
                    public void run() {
                        loadDataFromNet();
                    }
                });
            }
        });
    }
    private void loadDataFromNet(){
        queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                "http://www.xinhuanet.com/local/news_province.xml",
                new Response.Listener<String>() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String s) {
                        InputStream is =
                                new ByteArrayInputStream(s.getBytes(StandardCharsets.ISO_8859_1));
                        try {
                            items.clear();
                            items.addAll(SAXParse.parse(is));
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                        refreshView.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,"网络异常",Toast.LENGTH_SHORT).show();
                refreshView.setRefreshing(false);
            }
        });
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }
}
