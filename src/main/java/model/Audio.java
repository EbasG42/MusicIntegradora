package model;
public abstract class Audio implements Playable {

    private String name;
    private int duration;
    private String url;
    private int totalPlays;
    private Productor autor;

    public Audio(String name, int duration, String url, User autor) {
        this.name = name;
        this.duration = duration;
        this.url = url;

        this.autor = (Productor )autor;
        totalPlays = 0;
    }
    @Override
    public String plays(){
        totalPlays+=1;
        autor.setaccumPlay(autor.getaccumPlay() + 1);
        return "The audio " + getName() + " is playing";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotalPlays() {
        return totalPlays;
    }

    public void setTotalPlays(int totalPlays) {
        this.totalPlays = totalPlays;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Productor getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = (Productor) autor;
    }

}