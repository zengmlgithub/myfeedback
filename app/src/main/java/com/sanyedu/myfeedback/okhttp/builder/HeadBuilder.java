package com.sanyedu.myfeedback.okhttp.builder;

import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.okhttp.request.OtherRequest;
import com.sanyedu.myfeedback.okhttp.request.RequestCall;


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
