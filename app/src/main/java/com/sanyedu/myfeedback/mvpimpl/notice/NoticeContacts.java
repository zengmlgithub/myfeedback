package com.sanyedu.myfeedback.mvpimpl.notice;

import com.sanyedu.myfeedback.model.NoticeBean;
import com.sanyedu.myfeedback.mvp.IBasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

import java.util.ArrayList;

public final class NoticeContacts {
    public interface INoticeUI extends IBaseView {
        void setNotices(ArrayList<NoticeBean> notices,int maxPageCount);

        void setNoNotices();

        /**
         * 查看错误
         * @param serverError
         */
        void showError(String serverError);
    }

    public interface INoticePresenter extends IBasePresenter{
        /**
         *
         * @param curPage  当前查询的页面
         * @param pageCount 每个页面拥有的记录条数
         */
        public void getNotices(String curPage,String pageCount);
    }
}
