package com.jcodecraeer.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.android.recyclerview.BaseAdapterHelper;
import com.joanzapata.android.recyclerview.QuickAdapter;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private QuickAdapter<Item> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new QuickAdapter<Item>(this, R.layout.item_demo){
            @Override
            protected void convert(BaseAdapterHelper helper, Item item) {
                helper.getTextView(R.id.title).setText(item.getTitle());
                // or
                // TextView title = (TextView)helper.getView(R.id.avatar);
                // title.setText(item.getTitle());
                helper.getTextView(R.id.description).setText(item.getDescription());
                //helper.getImageView(R.id.avatar).setImageResource(item.getAvatar());
                Picasso.with(context).load(item.getAvatar()).into(helper.getImageView(R.id.avatar));
                // or
                // ImageView avatar = (ImageView)helper.getView(R.id.avatar);
                // Picasso.with(context).load(item.getAvatar()).into(avatar);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        for(int i=0; i<30; i++){
            Item  temp = new Item();
            temp.setTitle("item" + i);
            temp.setDescription("this is the description of item" + i);
            temp.setAvatar("http://www.jcodecraeer.com/member/templets/images/dfboy.png");
            mAdapter.addItem(temp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
