package model;
public abstract class Productor extends User {

    private String name;
    private String url;
    private int accumPlay;
    private double timePlayed;

    /**
     * <b>Constructor</b> allows to create a Productor's object.
     * 
     * @param nick is the user's nick.
     * @param id is the user's identification number.
     * @param name     is the productor's name.
     * @param url      is the productor's url.
     */
    public Productor(String nick, String id, String name, String url) {
        super(nick, id);
        this.name = name;
        this.url = url;
        accumPlay=0;
        timePlayed=0;

    }

    public void updateSoldInfo(int duration){
        accumPlay++;
        timePlayed+=duration;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getaccumPlay() {
        return accumPlay;
    }

    public void setaccumPlay(int accumPlay) {
        this.accumPlay = accumPlay;
    }

    public double getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(double timePlayed) {
        this.timePlayed = timePlayed;
    }
}