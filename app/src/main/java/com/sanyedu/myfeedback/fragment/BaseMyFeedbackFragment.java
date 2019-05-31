package com.sanyedu.myfeedback.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.NeedModifyAdapter;
import com.sanyedu.myfeedback.base.BaseFragment;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.Records;
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvpimpl.MyFeedbackFragment.MyFeedbackFragmentContacts;
import com.sanyedu.myfeedback.mvpimpl.MyFeedbackFragment.MyFeedbackFragmentPresenter;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;

import java.util.List;

/**
 *  我的反馈
 */
public class BaseMyFeedbackFragment extends BaseFragment<MyFeedbackFragmentPresenter> implements MyFeedbackFragmentContacts.IMyFeedbackFragmentUI{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView listView;
    private NeedModifyAdapter adapter;

    public BaseMyFeedbackFragment() {
        // Required empty public constructor
    }

    public static BaseMyFeedbackFragment newInstance(String param1, String param2) {
        BaseMyFeedbackFragment fragment = new BaseMyFeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BaseMyFeedbackFragment newInstance(String param1) {
        return newInstance(param1,null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_feedback;
    }

    @Override
    protected void init(View view) {
        listView = view.findViewById(R.id.wait_el);
        adapter = new NeedModifyAdapter(getContext());
        listView.setHasFixedSize(true);
        listView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layout);

        setData();
    }

    private void setData() {
//        getPresenter().getRecords("1","10","1");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }else{
            mParam1 = ConstantUtil.FK_STATE_SUBMITED;
            mParam2 = null;
        }

        //TODO:
        TeacherBean teacherBean = SpHelper.getObj(ConstantUtil.USERINFO);
        if(teacherBean != null){
            SanyLogs.i("get id:" + teacherBean.getId() + ",type:" + mParam1);
            getPresenter().getFeedbacks("1","10",teacherBean.getId(),mParam1);
        }

    }


    @Override
    public MyFeedbackFragmentPresenter onBindPresenter() {
        return new MyFeedbackFragmentPresenter(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
////        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
////        }
////    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void setFeebacks(List<Records> recordsList) {
        adapter.setRecordsList(recordsList);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
