package com.sanyedu.myfeedback.mvp;

/**
 * 此处有两个接口
 */
public interface IBaseView extends IBaseXView {

   /**
    * 显示正在加载 view
    */
    void showLoading();
    
    /**
     * 关闭正在加载 view
     */
    void hideLoading();

}