package com.roytho.live.home.commentary;

/**
 * Created by Suresh on 2/27/2018.
 */

public class Comment {

    private String comment;
    private String state;

    public Comment(){}

    public Comment(String comment, String state){
        this.comment = comment;
        this.state = state;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getComment(){
        return comment;
    }

    public String getState(){
        return state;
    }

}
