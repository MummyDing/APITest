package com.demo.mummyding.apitest.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.demo.mummyding.apitest.R;
import com.demo.mummyding.apitest.model.PolicyBean;
import com.demo.mummyding.apitest.ui.WebDetailsActivity;

import java.util.List;

/**
 * Created by mummyding on 15-11-9.
 */
public class NewsAdapter extends ArrayAdapter<PolicyBean> implements AdapterView.OnItemClickListener {

    private Context mContext;

    public NewsAdapter(Context context, int resource, List<PolicyBean> objects) {
        super(context, resource, objects);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        PolicyBean policyBean = getItem(position);
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_news,null);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(policyBean.getTitle());
        viewHolder.date.setText(policyBean.getPubTime());
        viewHolder.description.setText(policyBean.getDescription());

       return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PolicyBean policyBean = getItem(position);
        Intent intent = new Intent(getContext(), WebDetailsActivity.class);
        intent.putExtra("url",policyBean.getLink());
        mContext.startActivity(intent);
    }

    class ViewHolder{
        TextView title;
        TextView description;
        TextView date;
    }
}
