package com.example.demo;

import org.apache.flink.api.common.functions.FilterFunction;

public class RemoveNumber3Filter implements FilterFunction<Integer> {
    @Override
    public boolean filter(Integer i) throws Exception {
        return i != 3;
    }
}
