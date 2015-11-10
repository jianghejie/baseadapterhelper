# baseadapterhelper
a handy adapter for ListView and RecyClerview. base on JoanZapata's base-adapter-helper and add support for RecyClerview

##使用
**activity_main.xml**
``
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity"
     />

</FrameLayout>
``
MainActivity.java
``
package com.jcodecraeer.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
}

``
item_demo.xml
``
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    >
    <ImageView
        android:id="@+id/avatar"
        android:layout_width="50dip"
        android:layout_height="50dip"
        android:layout_marginRight="10dip"
        android:layout_alignParentLeft="true"/>
    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@id/avatar"/>
    <TextView
        android:id="@+id/description"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@id/avatar"
        android:layout_below="@id/title"/>

</RelativeLayout>
``
Item.java
``
package com.jcodecraeer.sample;

/**
 * Created by jianghejie on 15/11/10.
 */


public class Item {

    private String title;
    private String description;
    private String avatar;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
``
