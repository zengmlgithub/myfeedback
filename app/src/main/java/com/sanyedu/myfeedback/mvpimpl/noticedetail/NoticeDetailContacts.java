package com.sanyedu.myfeedback.mvpimpl.noticedetail;

import com.sanyedu.myfeedback.model.NoticeDetailBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public final class NoticeDetailContacts {

    public interface INoticeDetailUI extends IBaseView{
        public void setNoticeDetail(NoticeDetailBean detailBean);
    }

    public interface INoticeDetaiPresenter extends IBasePresenter{
        public void getNoticeDetail(String id);
    }


}
