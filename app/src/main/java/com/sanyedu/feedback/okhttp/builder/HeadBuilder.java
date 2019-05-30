package com.sanyedu.feedback.okhttp.builder;

import com.sanyedu.feedback.okhttp.OkHttpUtils;
import com.sanyedu.feedback.okhttp.request.OtherRequest;
import com.sanyedu.feedback.okhttp.request.RequestCall;


/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
