package com.example.omii026.testing.SupportClasses;

/**
 * Created by Omii026 on 10/11/2015.
 */
public class GroupChatData {

    private String groupName;
    private String groupDesc;
    private String groupImage;
    private String groupOwner;

    public GroupChatData(String id, String desc, String image, String owner) {
        this.groupDesc = desc;
        this.groupName = id;
        this.groupImage = image;
        this.groupOwner = owner;
    }


    public String getGroupName() {
        return groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public String getGroupOwner() {
        return groupOwner;
    }

    public GroupChatData(String groupName,String groupDesc,String groupImage){
        this.groupDesc = groupDesc;
        this.groupName = groupName;
        this.groupImage = groupImage;
    }

}
