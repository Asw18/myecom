package com.example.ecom.model;

public class SliderModel {
    private int sliderImageLink;

    public String getBackGroundCoor() {
        return backGroundCoor;
    }

    public void setBackGroundCoor(String backGroundCoor) {
        this.backGroundCoor = backGroundCoor;
    }

    public SliderModel(int sliderImageLink, String backGroundCoor) {
        this.sliderImageLink = sliderImageLink;
        this.backGroundCoor = backGroundCoor;
    }

    private String backGroundCoor;

    public int getSliderImageLink() {
        return sliderImageLink;
    }

    public void setSliderImageLink(int sliderImageLink) {
        this.sliderImageLink = sliderImageLink;
    }


}