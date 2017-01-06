package domain;

// @author Bart

public class Content {
    private int contentID, ageCategory, profileNumber;
    private String tvShow, seasonCode, film, title, language, duration, 
            genre, similarTo;
    private float percentage;
    
    public Content(int contentID, String film, String tvShow, String seasonCode, 
            String title, int ageCategory, String language, String duration, 
            String genre, String similarTo){
        this.contentID = contentID;
        this.tvShow = tvShow;
        this.film = film;
        this.seasonCode = seasonCode;
        this.title = title;
        this.ageCategory = ageCategory;
        this.language = language;
        this.duration = duration;
        this.genre = genre;
        this.similarTo = similarTo;
    }
    
    public Content(int contentID, String film, float percentage,  int profileNumber){
        this.contentID = contentID;
        this.film = film;
        this.percentage = percentage;
        this.profileNumber = profileNumber;
    }
    
    public Content(int contentID, String film, float percentage){
        this.contentID = contentID;
        this.film = film;
        this.percentage = percentage;
    }
    
    public Content(int contentID, String tvShow, String seasonCode, String title) {
        this.contentID = contentID;
        this.tvShow = tvShow;
        this.seasonCode = seasonCode;
        this.title = title;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSimilarTo() {
        return similarTo;
    }

    public void setSimilarTo(String similarTo) {
        this.similarTo = similarTo;
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

    public int getProfileNumber() {
        return profileNumber;
    }

    public void setProfileNumber(int profileNumber) {
        this.profileNumber = profileNumber;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
    
    
//    public String toString() {
//        return getContentID() + " | " + getFilm() + ", " + getPercentage() + " " + getProfileNumber() + "\n";
//    }
    
}
