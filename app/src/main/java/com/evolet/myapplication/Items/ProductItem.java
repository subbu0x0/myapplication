package com.evolet.myapplication.Items;

public class ProductItem {

    String prodID, prodName, prodPrice, prodCategory, unit;
    int prodImage;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ProductItem( String prodName, String prodPrice, String unit,
                       String prodCategory) {
        //this.prodID = prodID;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodCategory = prodCategory;

        this.unit = unit;
    }

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public int getProdImage() {
        return prodImage;
    }

    public void setProdImage(int prodImage) {
        this.prodImage = prodImage;
    }
}
