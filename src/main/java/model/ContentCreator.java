package model;
import java.util.ArrayList;
public class ContentCreator extends Productor {

    private ArrayList<Podcast> podcasts;

    /**
     * <b>Constructor</b> allows to create a ContentCreator's object.
     * 
     * @param nick is the user's nick.
     * @param id is the user's identification number.
     * @param name     is the productor's name.
     * @param url      is the productor's url.
     */
    public ContentCreator(String nick, String id, String name, String url) {
        super(nick, id, name, url);
        podcasts = new ArrayList<Podcast>();
    }

    /**
     * <b>addAudio</b> allows to add a podcast to the user's list of podcasts.
     * 
     * @param audio is the podcast to be added.
     * @return String the message of the operation.
     */
    public String addAudio(Audio audio) {

        String msg = "The podcast was added successfully";
        Audio obj = searchAudio(audio.getName());
        if (obj == null) {
            podcasts.add((Podcast) audio);
        } else {
            msg = "The audio already exists";
        }
        return msg;
    }

    /**
     * <b>searchAudio</b> allows to search an audio in the user's list of audios.
     * 
     * @param name is the name of the audio to be searched.
     * @return Audio the audio found.
     */
    public Audio searchAudio(String name) {

        Audio obj = null;
        boolean search = false;
        if (podcasts != null) {
            for (int i = 0; i < podcasts.size() && !search; i++) {
                if (podcasts.get(i).getName().equalsIgnoreCase(name)) {
                    obj = podcasts.get(i);
                    search = true;
                }
            }
        }

        return obj;

    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

}