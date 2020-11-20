package com.subjectmanage.beans;

import java.util.Map;

public class PageElement {
    private String title;
    private String href;
    private String icon;
    private String target;
    private Map<String,Object> child;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Map<String, Object> getChild() {
        return child;
    }

    public void setChild(Map<String, Object> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "PageElement{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", target='" + target + '\'' +
                ", child=" + child +
                '}';
    }
}
