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

public class AddWordsFragment extends DialogFragment {
    private EditText mEditTextPersian ;
    private EditText mEditTextEnglish ;
    public static final String ADD_NEW_WORD = "com.example.alitranslate.Conroller.Fragment.Word";
    private Words mWords = new Words();
    public AddWordsFragment() {
        // Required empty public constructor
    }


    public static AddWordsFragment newInstance() {
        AddWordsFragment fragment = new AddWordsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_words,null);
        findViews(view);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mWords.setWordFa(mEditTextPersian.getText().toString());
                        mWords.setWordEn(mEditTextEnglish.getText().toString());
                        sendResult();
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
    private void sendResult() {
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(ADD_NEW_WORD,mWords);
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}