package com.example.alitranslate.Conroller.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.alitranslate.Model.Words;
import com.example.alitranslate.R;
import com.example.alitranslate.Repository.IRepository;
import com.example.alitranslate.Repository.TranslateRepository;

import java.util.List;


public class EditWordFragment extends DialogFragment {
    private EditText mEditTextPersian ;
    private EditText mEditTextEnglish ;
    public static final String EXTRA_EDIT_WORD = "com.example.alitranslate.Conroller.Fragment.EditWord";
    private IRepository mRepository ;
    private Words mWords = new Words();
    public EditWordFragment() {
        // Required empty public constructor
    }

    public static EditWordFragment newInstance(String eng,String per) {
        EditWordFragment fragment = new EditWordFragment();
        Bundle args = new Bundle();
        args.putString("English",eng);
        args.putString("Persian",per);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TranslateRepository.getInstance(getActivity());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_words,null);
        findViews(view);
        getArgumentsValues();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getWords();
                        mWords.setWordEn(mEditTextEnglish.getText().toString());
                        mWords.setWordFa(mEditTextPersian.getText().toString());
                        mRepository.updateWords(mWords);
                        sendResult(mWords);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).create();
    }
    private void findViews(View view){
        mEditTextEnglish = view.findViewById(R.id.word_En);
        mEditTextPersian = view.findViewById(R.id.word_Fa);
    }
    private void getArgumentsValues(){
        mEditTextEnglish.setText(getArguments().getString("English"));
        mEditTextPersian.setText(getArguments().getString("Persian"));

    }
    private void getWords(){
        List<Words> wordsList = mRepository.getWordsList();
        for (Words words:wordsList) {
            if (words.getWordEn().equalsIgnoreCase(mEditTextEnglish.getText().toString())
                    ||words.getWordFa().equalsIgnoreCase(mEditTextPersian.getText().toString())){
                mWords =words ;
                return;
            }
        }
    }
    private void sendResult(Words words){
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_EDIT_WORD,words);
        fragment.onActivityResult(getTargetRequestCode(),Activity.RESULT_OK, intent);
    }
}