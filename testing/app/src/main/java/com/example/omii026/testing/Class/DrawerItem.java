package com.example.omii026.testing.Class;

/**
 * Created by Omii026 on 9/3/2015.
 */
public class DrawerItem {

        String Item;
        int image;

        public DrawerItem(String Item){
            this.Item = Item;
        }
        public DrawerItem(String Item,int Image){
            this.image = image;
            this.Item = Item;
        }

        public String getItem() {
            return Item;
        }

        public void setItem(String item) {
            Item = item;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }

