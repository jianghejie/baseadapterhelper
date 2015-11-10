# baseadapterhelper
a handy adapter for ListView and RecyClerview. base on   [JoanZapata's base-adapter-helper](https://github.com/JoanZapata/base-adapter-helper )  but add support for RecyClerview.

##How to use it

```java
mAdapter = new QuickAdapter<Item>(this, R.layout.item_demo){
    @Override
    protected void convert(BaseAdapterHelper helper, Item item) {
        helper.getTextView(R.id.title).setText(item.getTitle());
        helper.getTextView(R.id.description).setText(item.getDescription());
        Picasso.with(context).load(item.getAvatar()).into(helper.getImageView(R.id.avatar));
    }
};
mRecyclerView.setAdapter(mAdapter);
```
very simple and clean.

example code above show how to set the value of TextView and ImageView,infact if you read the code of the lib,you can find there is another method to solve Button
```java
  public Button getButton(int viewId)
```
***so how can I set the value of(or handle) other type of views?***

for example a custom circle ImageView from the third party :CircleImageView.
we can use getView method  
```java
 CircleImageView avatar = (CircleImageView)helper.getView(R.id.avatar);
 Picasso.with(context).load(item.getAvatar()).into(avatar);
```
##other
[an article about this project](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0809/3277.html ) 
