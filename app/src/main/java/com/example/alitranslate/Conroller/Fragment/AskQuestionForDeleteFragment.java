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
import android.widget.TextView;

import com.example.alitranslate.Model.Words;
import com.example.alitranslate.R;
import com.example.alitranslate.Repository.IRepository;
import com.example.alitranslate.Repository.TranslateRepository;

import java.util.List;


public class AskQuestionForDeleteFragment extends DialogFragment {
    private IRepository mRepository;
    private TextView mTextView;
    private Words mWords = new Words();
    private String eng;
    private String per;

    public AskQuestionForDeleteFragment() {
        // Required empty public constructor
    }


    public static AskQuestionForDeleteFragment newInstance(String eng, String per) {
        AskQuestionForDeleteFragment fragment = new AskQuestionForDeleteFragment();
        Bundle args = new Bundle();
        args.putString("English", eng);
        args.putString("Persian", per);
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
        View view = inflater.inflate(R.layout.fragment_ask_question_for_delete, null);
        mTextView = view.findViewById(R.id.QuestionText);
        getArgumentsValues();
        return new AlertDialog.Builder(getActivity())
                .setView(view).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getWords();
                        mRepository.deleteWords(mWords);
                        backToMainPage();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();
    }

    private void getArgumentsValues() {
        eng = getArguments().getString("English");
        per = getArguments().getString("Persian");

    }

    private void getWords() {
        List<Words> wordsList = mRepository.getWordsList();
        for (Words words : wordsList) {
            if (words.getWordEn().equalsIgnoreCase(eng)
                    || words.getWordFa().equalsIgnoreCase(per)){
                mWords = words;
                return;
            }
        }
    }
    private void backToMainPage(){
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

    }
}