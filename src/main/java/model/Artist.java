package model;
import java.util.ArrayList;
public class Artist extends Productor {

    private ArrayList<Song> songs;

    /**
     * <b>Constructor</b> allows to create an Artist's object.
     * 
     * @param nick is the user's nick.
     * @param id is the user's identification number.
     * @param name     is the artist's name.
     * @param url      is the artist's url.
     */
    public Artist(String nick, String id, String name, String url) {
        super(nick, id, name, url);
        songs = new ArrayList<Song>();
    }

    /**
     * <b>addAudio</b> allows to add a song to the artist's list of songs.
     * 
     * @param audio is the song to be added.
     * @return String the message that indicates if the song was added successfully
     *         or not.
     */
    public String addAudio(Audio audio) {

        String msg = "The was added successfully";
        Audio obj = searchAudio(audio.getName());
        if (obj == null) {
            songs.add((Song) audio);
            return "Audio added";
        } else {
            msg = "The audio already exists";
        }
        return msg;
    }

    /**
     * <b>searchAudio</b> allows to search an audio in the artist's list of audios.
     * 
     * @param name is the name of the audio to be searched.
     * @return Audio the audio that was searched.
     */
    public Audio searchAudio(String name) {

        Audio obj = null;
        boolean search = false;
        if (songs != null) {
            for (int i = 0; i < songs.size() && !search; i++) {
                if (songs.get(i).getName().equalsIgnoreCase(name)) {
                    obj = songs.get(i);
                    search = true;
                }
            }
        }

        return obj;

    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

}