package com.demo.mummyding.apitest.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.demo.mummyding.apitest.model.PolicyBean;
import com.demo.mummyding.apitest.sax.SAXParse;

import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    /*private TextView textView;
    private WebView webView;*/
    private RequestQueue queue;
    private List<PolicyBean> items ;
    private NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            adapter = new NewsAdapter(MainActivity.this,R.layout.item_news,items);
            listView.setOnItemClickListener(adapter);
            listView.setAdapter(adapter);
            return false;
        }
    });
    private void initData(){
        queue = Volley.newRequestQueue(this);
        listView = (ListView) findViewById(R.id.listnews);
        StringRequest stringRequest = new StringRequest(
                "http://www.xinhuanet.com/politics/news_politics.xml",
                new Response.Listener<String>() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String s) {
                        InputStream is =
                                new ByteArrayInputStream(s.getBytes(StandardCharsets.ISO_8859_1));
                        try {
                            items =  SAXParse.parse(is);
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}
