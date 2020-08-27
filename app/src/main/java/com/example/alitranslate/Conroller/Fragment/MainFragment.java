package com.example.alitranslate.Conroller.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.alitranslate.R;

public class MainFragment extends Fragment {
    private EditText mEditTextSearchBox;
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
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findViews(view);
        return view ;
    }
    private void findViews(View view){
        mEditTextSearchBox = view.findViewById(R.id.word_search_box);
        mSearchButton = view.findViewById(R.id.Search_box_button);
    }
    private void setListener(){
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void searchWords(){

    }
}