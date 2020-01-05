package com.baidu.ai.bean;

/**
 *     // "result":{"face_token":"19819b8a93208d0d5c19f93082f7f4b3",
 *     // "location":{"left":184.16,"top":227.39,"width":216,"height":213,"rotation":1}}}
 */
public class Location {
    private Double left;

    private Double top;

    private Integer width;

    private Integer height;

    private Integer rotation;

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        return "Location{" +
                "left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                ", rotation=" + rotation +
                '}';
    }
}