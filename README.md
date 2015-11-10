# baseadapterhelper
a handy adapter for ListView and RecyClerview. base on JoanZapata's base-adapter-helper and add support for RecyClerview

##使用
        mAdapter = new QuickAdapter<Item>(getActivity(), R.layout.item_repository){
            @Override
            protected void convert(BaseAdapterHelper helper, Item item) {
                helper.getTextView(R.id.repository_name).setText(item.getFullName());
                helper.getTextView(R.id.forks_count).setText(item.getForksCount()+"");
                helper.getTextView(R.id.score).setText(item.getScore()+"");
                helper.getTextView(R.id.createdAt).setText(item.getCreatedAt());
                helper.getTextView(R.id.updatedAt).setText(item.getUpdatedAt());
                helper.getTextView(R.id.size).setText(item.getSize()+"");
                helper.getTextView(R.id.contributors_url).setText(item.getContributorsUrl()+"");
                helper.getTextView(R.id.description).setText(item.getDescription());
                helper.getTextView(R.id.url).setText(item.getUrl());
                helper.getTextView(R.id.stargazers_count).setText(item.getStargazersCount()+"");

                final ImageView avatar = helper.getImageView(R.id.avatar);
                Glide.with(getActivity())
                        .load(item.getOwner().getAvatarUrl())
                        .placeholder(R.drawable.iconfont_avatar)
                        .crossFade()
                        .into(helper.getImageView(R.id.avatar));
            }
        };
        mRecyclerView.setAdapter(mAdapter);
