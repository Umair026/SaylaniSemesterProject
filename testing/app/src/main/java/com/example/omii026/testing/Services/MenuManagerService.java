package com.example.omii026.testing.Services;

import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by panacloud on 5/8/15.
 */
/* Dynamically Change ActionBar Items

   Program's Flow..
   onCreate called then fragment will be created then onCreateOptionsMenu called then onPrepareOptionsMenu called

    Implementation
*  initialized an empty object of MenuManagerService and place before setContentView()
*  when fragment is creating we set the empty menu
*  when onCreateMenuOption called we set the menu
   and when onPrepareOptionMenu called we update menu.
*/
public class MenuManagerService {

    private Menu menu;
    private int[] menuIdsToDisplay;

    public MenuManagerService(Menu menu){
        this.menu = menu;
    }

    public MenuManagerService(){ }

    public void setMenu(Menu menu){
        this.menu = menu;
    }

    public void setUpMenuItems(int... menuIds){
        this.menuIdsToDisplay = menuIds;
    }

    public void setUpMenuItems(){
        this.menuIdsToDisplay = new int[0];
    }

    public Menu getMenu() {
        return menu;
    }

    public void updateMenu() {
        if(menu==null){
            return;
        }
        for(int i=0;i<menu.size();i++){
            menu.getItem(i).setVisible(false);
        }
        for(int id : menuIdsToDisplay){
            MenuItem menuItem = menu.findItem(id);
            if(menuItem != null) {
                menuItem.setVisible(true);
            }
        }
    }
}
