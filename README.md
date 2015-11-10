# baseadapterhelper
a handy adapter for ListView and RecyClerview. base on JoanZapata's base-adapter-helper and add support for RecyClerview

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
