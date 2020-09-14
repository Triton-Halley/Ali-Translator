package com.example.alitranslate.Conroller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.alitranslate.Conroller.Fragment.MainFragment;
import com.example.alitranslate.Conroller.Fragment.ShowWordsFragment;
import com.example.alitranslate.Model.Words;
import com.example.alitranslate.R;

public class ShowWordsActivity extends SingleFragmentActivity {
    private Words mWords = new Words();
    private String eng ;
    private String per ;
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,ShowWordsActivity.class);
        return intent ;
    }
    @Override
    public Fragment createFragment() {
        getIntentValue();
        return ShowWordsFragment.newInstance(eng,per);

    }
    private void getIntentValue(){
        Intent intent = getIntent();
        mWords = (Words) intent.getSerializableExtra(MainFragment.EXTRA_SHOW_WORD_ACTIVITY);
        eng = mWords.getWordEn();
        per = mWords.getWordFa();
    }
}