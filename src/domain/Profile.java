    package domain;

// @AUTHOR Felix
 
public class Profile {
    private int profileNumber, subscriberNumber;
    private String name, birthday;

    public Profile(int profileNumber, int subscriberNumber, String name, String birthday) {
        this.profileNumber = profileNumber;
        this.subscriberNumber = subscriberNumber;
        this.name = name;
        this.birthday = birthday;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
