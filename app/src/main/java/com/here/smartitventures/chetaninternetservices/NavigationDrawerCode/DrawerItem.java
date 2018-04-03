package com.here.smartitventures.chetaninternetservices.NavigationDrawerCode;

/**
 * Created by atinder on 9/6/17.
 */

public class DrawerItem  {String ItemName;
    int imgResID;

public DrawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
        }

public String getItemName() {
        return ItemName;
        }
public void setItemName(String itemName) {
        ItemName = itemName;
        }
public int getImgResID() {
        return imgResID;
        }
public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
        }

        }