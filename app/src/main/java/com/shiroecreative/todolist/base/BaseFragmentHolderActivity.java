package com.shiroecreative.todolist.base;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shiroecreative.todolist.R;

public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected RelativeLayout rlActivityFragmentHolder;
    protected ImageButton btnBack;

    @Override
    protected void initializeView() {
        setContentView(R.layout.activity_base);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        flFragmentContainer = findViewById(R.id.fl_fragment_container);
        rlActivityFragmentHolder = findViewById(R.id.rl_activity_fragment_holder);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(view -> super.onBackPressed());
    }

    @Override
    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }
}
