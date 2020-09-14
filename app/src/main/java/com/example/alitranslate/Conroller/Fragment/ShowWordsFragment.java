package com.example.alitranslate.Conroller.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alitranslate.Conroller.Activity.MainActivity;
import com.example.alitranslate.Model.Words;
import com.example.alitranslate.R;


public class ShowWordsFragment extends Fragment {
    private TextView mTextViewEnglish;
    private TextView mTextViewShare;
    private TextView mTextViewPersian;
    private String eng;
    private String per;
    private static String ENGLISH_WORD = "English";
    private static String PERSIAN_WORD = "Persian";

    public ShowWordsFragment() {
        // Required empty public constructor
    }

    public static ShowWordsFragment newInstance(String eng, String per) {
        ShowWordsFragment fragment = new ShowWordsFragment();
        Bundle args = new Bundle();
        args.putString(ENGLISH_WORD, eng);
        args.putString(PERSIAN_WORD, per);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_words, container, false);
        findViews(view);
        getArgumentValue();
        setWords();
        listener();
        return view;
    }
    private void listener(){
        mTextViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareWord();
            }
        });
    }
    private void findViews(View view) {
        mTextViewEnglish = view.findViewById(R.id.english_word);
        mTextViewPersian = view.findViewById(R.id.persian_word);
        mTextViewShare = view.findViewById(R.id.share);
    }

    private void getArgumentValue() {
        eng = getArguments().getString(ENGLISH_WORD);
        per = getArguments().getString(PERSIAN_WORD);
    }

    private void setWords() {
        mTextViewEnglish.setText(eng);
        mTextViewPersian.setText(per);
    }
    //share Words method
    private void ShareWord(){
        Intent sendWord =new Intent(Intent.ACTION_SEND);
        sendWord.putExtra(Intent.EXTRA_TEXT,"Words : " + eng + "," + per);
        sendWord.setType("text/plain");
        Intent shareWord = Intent.createChooser(sendWord,null);
        if (sendWord.resolveActivity(getActivity().getPackageManager())!=null){
            startActivity(shareWord);
        }
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tools_show_words, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_word: {
                AskQuestionForDeleteFragment delete = AskQuestionForDeleteFragment.newInstance
                        (mTextViewEnglish.getText().toString()
                                , mTextViewPersian.getText().toString());
                delete.setTargetFragment(ShowWordsFragment.this, 0);
                delete.show(getActivity().getSupportFragmentManager(), "Dialog");
                return true;
            }
            case R.id.edit: {
                EditWordFragment edit = EditWordFragment.newInstance(mTextViewEnglish.getText().toString()
                        , mTextViewPersian.getText().toString());
                edit.setTargetFragment(ShowWordsFragment.this, 1);
                edit.show(getActivity().getSupportFragmentManager(), "Dialog");
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null) {
            return;
        }
        if (requestCode == 0) {
            Intent intent = MainActivity.newIntent(getActivity());
            startActivity(intent);
        } else if (requestCode == 1) {
            Words words = (Words) data.getSerializableExtra(EditWordFragment.EXTRA_EDIT_WORD);
            mTextViewEnglish.setText(words.getWordEn());
            mTextViewPersian.setText(words.getWordFa());
        }
    }
}