package model;
import java.time.*;
public abstract class User {

    private String nick;
    private String id;
    private LocalDate vincDate;

    /**
     * <b>Constructor</b> allows to create an User's object.
     * 
     * @param nick is the user's nick.
     * @param id is the user's identification number.
     */
    public User(String nick, String id) {

        this.nick = nick;
        this.id = id;
        vincDate = LocalDate.now();
    }

    public String getnick() {
        return nick;
    }

    public void setnick(String nick) {
        this.nick = nick;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public LocalDate getvincDate() {
        return vincDate;
    }

    public void setvincDate(LocalDate vincDate) {
        this.vincDate = vincDate;
    }
    @Override
    public String toString() {
        return "nick: " + nick + "\nID: " + id + "\nVinculation date: " + vincDate.toString();
    }

}