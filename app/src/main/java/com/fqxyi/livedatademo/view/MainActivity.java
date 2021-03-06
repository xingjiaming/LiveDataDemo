package com.fqxyi.livedatademo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fqxyi.livedatademo.R;
import com.fqxyi.library.BaseActivity;
import com.fqxyi.livedatademo.observer.ActivityLifecycleObserver;
import com.fqxyi.livedatademo.viewmodel.MainViewModel;

/**
 * View层
 * 只负责显示UI，不执行数据处理逻辑
 */
public class MainActivity extends BaseActivity<MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initFragment();
        initLifecycle();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment fragment = new MainFragment();

        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            bundle = new Bundle();
        }

        Bundle fragmentArguments = fragment.getArguments();
        if (null != fragmentArguments) {
            bundle.putAll(fragmentArguments);
        }

        fragment.setArguments(bundle);

        transaction.add(R.id.main_activity_root, fragment, "MainActivity");

        transaction.commitAllowingStateLoss();
    }

    public void initLifecycle() {
        getLifecycle().addObserver(new ActivityLifecycleObserver(getApplicationContext()));
    }

    @Override
    protected MainViewModel getViewModel() {
        return null;
    }
}
