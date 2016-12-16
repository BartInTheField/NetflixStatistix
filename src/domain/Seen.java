package domain;

// @AUTHOR Felix
 
public class Seen {
    private int seenId, profileNumber, subscriberNumber, contentId;
    private float percentage;

    public Seen(int seenId, int profileNumber, int subscriberNumber, int contentId, float percentage) {
        this.seenId = seenId;
        this.profileNumber = profileNumber;
        this.subscriberNumber = subscriberNumber;
        this.contentId = contentId;
        this.percentage = percentage;
    }

    public int getSeenId() {
        return seenId;
    }

    public void setSeenId(int seenId) {
        this.seenId = seenId;
    }

    public int getProfileNumber() {
        return profileNumber;
    }

    public void setProfileNumber(int profileNumber) {
        this.profileNumber = profileNumber;
    }

    public int getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(int subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
