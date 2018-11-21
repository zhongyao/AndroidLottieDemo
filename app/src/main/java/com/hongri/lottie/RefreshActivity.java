package com.hongri.lottie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.hongri.lottie.adapter.MyRecyclerAdapter;
import com.hongri.lottie.adapter.NormalAdapter;
import com.hongri.lottie.widget.XRecyclerView;

import static com.hongri.lottie.util.DataUtil.getData;

/**
 * @author hongri
 */
public class RefreshActivity extends AppCompatActivity {

    private XRecyclerView mRecyclerView;
    private MyRecyclerAdapter mAdapter;
    private View mHeaderView, mFooterView;
    private NormalAdapter mNormalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        mRecyclerView = (XRecyclerView)findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.item_header, mRecyclerView, false);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.item_footer, mRecyclerView, false);
        mRecyclerView.setHeaderView(mHeaderView);
        mRecyclerView.setFooterView(mFooterView);
        mNormalAdapter = new NormalAdapter(this, getData());
        mAdapter = new MyRecyclerAdapter(this, mNormalAdapter,mHeaderView, mFooterView);
        mRecyclerView.setAdapter(mAdapter);

    }
}
