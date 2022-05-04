package com.example.order_delivery.model;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.parceler.Parcel;

@ParseClassName("sz_item_cust")
@Parcel(analyze={sz_item_cust.class})
public class sz_item_cust extends ParseObject {
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_RATING = "rating";
    public static final String KEY_IMGFILE = "imgFile";
//    public static final String KEY_OBJECTID = "objectId";

    //blank constructor for parceler
    public sz_item_cust(){}
    public String getItemName() {
        return getString(KEY_NAME);
    }
    public String getItemDescription() {
        return getString(KEY_DESCRIPTION);
    }
    public String getItemPrice() {
        return getString(KEY_PRICE);
    }
    public String getItemRating() {
        return getString(KEY_RATING);
    }
//    public String getObjectId() {return getString(KEY_OBJECTID);}
    public ParseFile getItemImage() {
        return getParseFile(KEY_IMGFILE);
    }
}
