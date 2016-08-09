package com.example.yueyangzou.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by yueyangzou on 16/8/8.
 */
public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
