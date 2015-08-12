package org.succlz123.AxBTube.ui.fragment.acfun.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.succlz123.AxBTube.R;
import org.succlz123.AxBTube.bean.acfun.AcBangumi;
import org.succlz123.AxBTube.support.adapter.acfun.recyclerview.AcBangumiRvAdapter;
import org.succlz123.AxBTube.support.helper.acfun.AcApi;
import org.succlz123.AxBTube.support.helper.acfun.AcString;
import org.succlz123.AxBTube.support.utils.LogUtils;
import org.succlz123.AxBTube.ui.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by fashi on 2015/5/2.
 */
public class AcBangumiFragment extends BaseFragment {
    @Bind(R.id.ac_fragment_bangumi_recycler_view)
    RecyclerView mRecyclerView;

    private boolean mIsPrepared;
    private AcBangumiRvAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ac_fragment_main_bangumi, container, false);
        ButterKnife.bind(this, view);
        mIsPrepared = true;

        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new AcNavigationRvAdapter.MyDecoration());
        mAdapter = new AcBangumiRvAdapter();


        mRecyclerView.setAdapter(mAdapter);
        xxx();
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    private void xxx() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AcString.URL_ACFUN_ICAO).build();
        AcApi.getAcBangumi acBangumi = restAdapter.create(AcApi.getAcBangumi.class);

        acBangumi.onBangumiResult(AcApi.getAcBangumiUrl(AcString.BANGUMI_TYPES_ANIMATION), new Callback<AcBangumi>() {
            @Override
            public void success(AcBangumi acBangumi, Response response) {
                mAdapter.setBangumiInfo(acBangumi);
                LogUtils.e(acBangumi.toString());

                int x=3;
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}