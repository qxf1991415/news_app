package com.qxf.newsapp.talk;

import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * Created by Administrator on 2017/9/15.
 */

public class TalkListFrgment extends EaseConversationListFragment {
    @Override
    protected void initView() {
        super.initView();
        hideTitleBar();
    }
}
