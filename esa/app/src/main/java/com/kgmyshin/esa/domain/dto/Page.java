/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.domain.dto;

import java.util.List;

public class Page<T> {

    private final int page;
    private final List<T> items;
    private final boolean hasNext;
    private final int nextPage;

    public Page(int page, List<T> items, boolean hasNext, int nextPage) {
        this.page = page;
        this.items = items;
        this.hasNext = hasNext;
        this.nextPage = nextPage;
    }

    public int getPage() {
        return page;
    }

    public List<T> getItems() {
        return items;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public int getNextPage() {
        return nextPage;
    }
}
