package com.example.alitranslate.Conroller.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alitranslate.Conroller.Activity.ShowWordsActivity;
import com.example.alitranslate.Model.Words;
import com.example.alitranslate.R;
import com.example.alitranslate.Repository.IRepository;
import com.example.alitranslate.Repository.TranslateRepository;

import java.util.List;

public class MainFragment extends Fragment {
    private EditText mEditTextSearchBox;
    private Words mWords = new Words();
    private static final int REQUEST_CODE_ADD_WORD = 0;
    private static final String DIALOG_TAG = "Dialog";
    public static final String EXTRA_SHOW_WORD_ACTIVITY = "com.example.alitranslate.Conroller.Fragment.ShowWords";
    private IRepository mRepository;
    private CardView mSearchButton;
    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mRepository = TranslateRepository.getInstance(getActivity());
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findViews(view);
        subTitle();
        setListener();
        return view;
    }

    private void findViews(View view) {
        mEditTextSearchBox = view.findViewById(R.id.word_search_box);
        mSearchButton = view.findViewById(R.id.Search_box_button);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tools, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_word: {
                AddWordsFragment addWordsFragment = AddWordsFragment.newInstance();
                addWordsFragment.setTargetFragment(MainFragment.this, REQUEST_CODE_ADD_WORD);
                addWordsFragment.show(getActivity().getSupportFragmentManager(), DIALOG_TAG);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        subTitle();
    }

    private void subTitle() {
        int numberOfWords = mRepository.getWordsList().size();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle("Words : " + numberOfWords);
    }

    private void setListener() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchWords(mEditTextSearchBox.getText().toString());
            }
        });
    }

    private void searchWords(String string) {
        List<Words> WordList = mRepository.getWordsList();
        for (Words word : WordList) {
            if (word.getWordFa().equalsIgnoreCase(string) ||
                    word.getWordEn().equalsIgnoreCase(string)) {
                mWords = word;
                Intent intent = ShowWordsActivity.newIntent(getActivity());
                intent.putExtra(EXTRA_SHOW_WORD_ACTIVITY, mWords);
                startActivity(intent);
                return;
            }
        }
        Toast.makeText(getActivity(),"This Word not Found",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_ADD_WORD) {
            mWords = (Words) data.getSerializableExtra(AddWordsFragment.ADD_NEW_WORD);
            mRepository.insetWords(mWords);
            subTitle();
        }
    }
}