package com.tvr.rxjavapractice.features.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.tvr.rxjavapractice.R;
import com.tvr.rxjavapractice.features.adapter.RepoRecyclerViewAdapter;
import com.tvr.rxjavapractice.features.model.response.GetRepoModel;
import com.tvr.rxjavapractice.features.model.response.GetRepoModelImplementation;
import com.tvr.rxjavapractice.features.model.response.GithubRepo;
import com.tvr.rxjavapractice.features.viewmodel.GetRepoViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {
    private GetRepoModel model;
    private GetRepoViewModel viewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchView searchView;
    private ImageView emptyIm;
    private TextView emptyTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new GetRepoModelImplementation(getApplicationContext());
        viewModel = ViewModelProviders.of(this).get(GetRepoViewModel.class);

        recyclerView = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progress_circular);
        searchView = findViewById(R.id.searchview);
        emptyIm = findViewById(R.id.emptyIm);
        emptyTv = findViewById(R.id.emptyTv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSearchview();
    }

    private void getSearchview() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getRepoData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getRepoData(s);
                return false;
            }
        });
    }

    private void getRepoData(String userName) {
        progressBar.setVisibility(View.VISIBLE);
        emptyIm.setVisibility(View.GONE);
        emptyTv.setVisibility(View.GONE);

        viewModel.getRepo(userName,model);
        viewModel.getReposuccessLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(new RepoRecyclerViewAdapter(((List<GithubRepo>)o),MainActivity.this));
            }
        });
        viewModel.getRepoFailedLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object data) {
                progressBar.setVisibility(View.GONE);
                Log.d("tttf", data.toString());
            }
        });
        viewModel.isEmptyListLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    emptyIm.setVisibility(View.VISIBLE);
                    emptyTv.setVisibility(View.VISIBLE);
                }else {
                    emptyIm.setVisibility(View.GONE);
                    emptyTv.setVisibility(View.GONE);
                }
            }
        });
    }
}