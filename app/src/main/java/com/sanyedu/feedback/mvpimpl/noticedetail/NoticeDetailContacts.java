package com.sanyedu.feedback.mvpimpl.noticedetail;

import com.sanyedu.feedback.model.NoticeDetailBean;
import com.sanyedu.feedback.mvp.IBasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

public final class NoticeDetailContacts {

    public interface INoticeDetailUI extends IBaseView{
        public void setNoticeDetail(NoticeDetailBean detailBean);
    }

    public interface INoticeDetaiPresenter extends IBasePresenter{
        public void getNoticeDetail(String id);
    }


}
