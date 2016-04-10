/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.dto;

public class Comment {

    private final int id;
    private final String bodyHtml;

    public Comment(int id, String bodyHtml) {
        this.id = id;
        this.bodyHtml = bodyHtml;
    }

    public int getId() {
        return id;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }
}
