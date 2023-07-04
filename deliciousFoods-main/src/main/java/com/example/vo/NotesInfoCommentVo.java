package com.example.vo;

import com.example.entity.NotesInfoComment;

public class NotesInfoCommentVo extends NotesInfoComment {

    private String foreignName;

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}