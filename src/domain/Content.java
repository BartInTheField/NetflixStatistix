package domain;

// @author Bart

public class Content {
    private int contentID;
    private String title;
    
    public Content(int contentID, String title){
        this.contentID = contentID;
        this.title = title;
    }

    public int getContentID() {
        return contentID;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
