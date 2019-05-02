package com.evolet.myapplication.Items;

public class Materials {

    private String material_name;
    private String material_id;
    private String material_category;
    private String material_price;
    private String mImage;
    private String mDate;

    private String mTime;


    private String mProductUnit;

    public Materials() {
    }

    public Materials(String materialName, String materialId, String materialCategory, String materialPrice, String materialImage, String productunit,
                     String date, String time) {
        this.material_name = materialName;
        this.material_id = materialId;
        this.material_category = materialCategory;
        this.material_price = materialPrice;
        this.mImage = materialImage;
        this.mProductUnit = productunit;
        this.mDate = date;
        this.mTime = time;
    }


    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_category() {
        return material_category;
    }

    public void setMaterial_category(String material_category) {
        this.material_category = material_category;
    }

    public String getMaterial_price() {
        return material_price;
    }

    public void setMaterial_price(String material_price) {
        this.material_price = material_price;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmProductUnit() {
        return mProductUnit;
    }

    public void setmProductUnit(String mProductUnit) {
        this.mProductUnit = mProductUnit;
    }

}