package com.example.alitranslate.Conroller.Activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.alitranslate.Conroller.Fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        return MainFragment.newInstance();
    }
}