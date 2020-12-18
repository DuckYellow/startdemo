package com.example.startdemo.date;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.Map;

public class DemoDataListener implements ReadListener {
    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

    }

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return false;
    }

    @Override
    public void invokeHead(Map map, AnalysisContext analysisContext) {

    }
}
