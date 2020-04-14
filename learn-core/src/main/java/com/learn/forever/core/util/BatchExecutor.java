package com.learn.forever.core.util;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author : gejianhua
 * @date 2020/3/18 19:18
 * 分批操作
 */
public class BatchExecutor {

    public static <T> void batchExecute(List<T> list, int size, Consumer<List<T>> consumer) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must > 0");
        }

        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        if (list.size() <= size) {
            consumer.accept(list);
        }

        List<T> batchList = Lists.newArrayList();

        for (int i = 0; i < list.size(); i++) {
            batchList.add(list.get(i));

            if ((i + 1) % size == 0) {
                consumer.accept(batchList);
                batchList.clear();
            }
        }

        if (!batchList.isEmpty()) {
            consumer.accept(batchList);
        }
    }

    public static  <T, R> List<R> batchExecute(List<T> list, int size, Function<List<T>, List<R>> function) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must > 0");
        }

        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        if (list.size() <= size) {
            return function.apply(list);
        }

        List<R> result = Lists.newArrayList();
        List<T> batchList = Lists.newArrayList();

        for (int i = 0; i < list.size(); i++) {
            batchList.add(list.get(i));

            if ((i + 1) % size == 0) {
                result.addAll(function.apply(batchList));
                batchList.clear();
            }
        }

        if (!batchList.isEmpty()) {
            result.addAll(function.apply(batchList));
        }

        return result;
    }
}
