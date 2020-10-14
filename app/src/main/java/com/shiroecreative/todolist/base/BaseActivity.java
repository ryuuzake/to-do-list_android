package com.shiroecreative.todolist.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shiroecreative.todolist.R;

public abstract class BaseActivity extends FragmentActivity implements FragmentListener {

    protected BaseFragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        initializeFragment();
    }

    protected abstract void initializeFragment();

    protected abstract void initializeView();

    protected void setCurrentFragment(BaseFragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (currentFragment != null && addToBackStack) {
            fragmentTransaction.addToBackStack(currentFragment.getTitle());
        }

        fragmentTransaction.replace(R.id.fl_fragment_container, fragment, fragment.getTitle());
        fragmentTransaction.commit();

        this.currentFragment = fragment;
    }
}
