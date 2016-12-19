package domain;

// @author Bart

public class Content {
    private int contentID;
    private String tvShow, seasonCode, film;
    
    public Content(int contentID, String film, String tvShow, String seasonCode){
        this.contentID = contentID;
        this.tvShow = tvShow;
        this.film = film;
        this.seasonCode = seasonCode;
    }

    public int getContentID() {
        return contentID;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getTvShow() {
        return tvShow;
    }

    public void setTvShow(String tvShow) {
        this.tvShow = tvShow;
    }

    public String getSeasonCode() {
        return seasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        this.seasonCode = seasonCode;
    }
    
    
}
