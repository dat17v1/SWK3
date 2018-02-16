package network.objects;

import java.io.Serializable;

public class Message implements Serializable{

    private String content = "";

    public void verify(){
        int pos = content.indexOf("boring");
        if(pos >= 0){
            content = content.substring(0,pos) + " exciting " +
                    content.substring(pos + "boring".length());
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
