package com.spider.school.Model;


public class PathModel {
    private String path;
    private String fileName;
    private int index;
    private String fileSuffix=".txt";

    public PathModel() {
    }

    public PathModel(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public PathModel(String path, String fileName, int index) {
        this.path = path;
        this.fileName = fileName;
        this.index = index;
    }

    public PathModel(String path, String fileName, int index, String fileSuffix) {
        this.path = path;
        this.fileName = fileName;
        this.index = index;
        this.fileSuffix = fileSuffix;
    }

    @Override
    public String toString() {
        return path+fileName+"_"+(index>0?index:"")+fileSuffix;
    }

    public String getPathPrefix() {
        return path;
    }

    public void setPathPrefix(String path) {
        this.path = path;
    }

    public String getName() {
        return fileName;
    }

    public void setName(String fileName) {
        this.fileName = fileName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }
}
