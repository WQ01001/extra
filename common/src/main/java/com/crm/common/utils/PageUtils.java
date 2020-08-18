package com.crm.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageUtils {

  public static <T> Page<T> getPage(Integer pageIndex, Integer pageSize) {
    int size = 10, index = 1;
    if (pageSize != null) {
      size = pageSize;
    }
    if (pageIndex != null) {
      index = pageIndex;
    }
    return new Page<T>(index, size);
  }
}
