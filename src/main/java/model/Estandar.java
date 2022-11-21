package model;
import java.util.Random;
public class Estandar extends Consumer {

    // private Audio[] audios;
    // private Playlist[] playlists;
    public static final int MAX_AUDIOS = 100;
    public static final int MAX_PLAYLISTS = 20;
    public static Random random = new Random();
    private int currentPlaybacks;

    /**
     * <b>Constructor</b><br>
     *  allows to create a Consumer's object.
     * 
     * @param nick is the user's nick.
     * @param id is the user's identification number.
     */
    public Estandar(String nick, String id) {
        super(nick, id);
        currentPlaybacks = 0;
    }

    /**
     * <b>addAudio</b><br>
     * allows to add an audio to the user's list of audios.<br>
     * <b>pre:</b> the audio must be already created.<br>
     * <b>post:</b> the audio will be added to the user's list of audios.<br>
     * 
     * @param audio is the audio to be added.
     * @return String the message that indicates if the audio was added successfully
     *         or not.
     */
    public String addAudio(Audio audio) {
        String msg = "";
        Shop obj = searchAudio(audio.getName());
        boolean available = isAvailableSong();
        if (obj == null) {
            if (available) {

                if (audio instanceof Song) {
                    Song s = (Song) audio;
                    msg += s.sell();
                    super.getShops().add(new Shop(s));

                } else {
                    msg = "The audio is not a song.";
                }

            } else {
                msg = "The user has reached the maximum number of songs";
            }
        } else {
            msg = "The song already exists";
        }

        return msg;
    }

    /**
     * <b>addPlaylist</b><br>
     * allows to add a playlist to the user's list of playlists.<br>
     * <b>pre:</b> the playlist must not exist.<br>
     * <b>post:</b> the playlist will be added to the user's list of playlists.<br>
     * 
     * @param name is the name of the playlist to be added.
     * @return String the message that indicates if the playlist was added
     *         successfully or not.
     */
    @Override
    public String addPlaylist(String name) {

        String msg = "Playlist Added succesfully";
        Playlist obj = searchPlaylist(name);
        boolean available = isAvailablePlaylist();
        if (obj == null) {
            if (available) {
                for (int i = 0; i < super.getPlaylists().size() && obj == null; i++) {
                    if (super.getPlaylists().get(i) == null) {
                        super.getPlaylists().add(new Playlist(name));
                        obj = super.getPlaylists().get(i);
                    }
                }
            } else {
                msg = "The user has reached the maximum number of playlists";
            }
        } else {
            msg = "The playlist already exists";
        }

        return msg;

    }

    /**
     * <b>addAudioToPlaylist</b><br>
     * allows to add an audio to a playlist.<br>
     * <b>pre:</b> the audio and the playlist must be already created.<br>
     * <b>post:</b> the audio will be added to the playlist.<br>
     * 
     * @param name  is the name of the playlist.
     * @param audio is the audio to be added.
     * @return String the message that indicates if the audio was added successfully
     *         or not.
     */
    @Override
    public String addAudioToPlaylist(String name, Audio audio) {

        String msg = "The audio was added succesfully";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            msg = obj.addAudio(audio);
        } else {
            msg = "The playlist does not exist";
        }

        return msg;
    }

    /**
     * <b>searchPlaylist</b><br>
     * allows to search a playlist by its name.<br>
     * <b>pre:</b> the playlist must be already created.<br>
     * <b>post:</b> the playlist will be searched.<br>
     * 
     * @param name is the name of the playlist to be searched.
     * @return PlayList the playlist found.
     */
    public Playlist searchPlaylist(String name) {

        Playlist obj = null;
        boolean search = false;
        for (int i = 0; i < super.getPlaylists().size() && !search; i++) {
            if (super.getPlaylists().get(i) != null && super.getPlaylists().get(i).getName().equalsIgnoreCase(name)) {
                obj = super.getPlaylists().get(i);
                search = true;
            }
        }

        return obj;

    }

    /**
     * <b>isAvailablePlaylist</b><br>
     * allows to know if the user can add more playlists.<br>
     * <b>pre:</b> the playlist list must exist.<br>
     * <b>post:</b> the availability has been found.<br>
     * 
     * @return boolean true if can, false if not.
     */
    public boolean isAvailablePlaylist() {
        boolean available = false;
        if (super.getPlaylists().size() < MAX_PLAYLISTS) {
            available = true;
        }
        return available;
    }

    /**
     * <b>searchAudio</b><br>
     * allows to search an audio by its name.<br>
     * <b>pre:</b> the shop list must be already created.<br>
     * <b>post:</b> the audio will be searched.<br>
     * 
     * @param name is the name of the audio to be searched.
     * @return Audio the audio found.
     */
    public Shop searchAudio(String name) {
        Shop obj = null;
        boolean search = false;
        for (int i = 0; i < super.getShops().size() && !search; i++) {
            if (super.getShops().get(i).getSong().getName().equalsIgnoreCase(name)) {
                obj = super.getShops().get(i);
                search = true;
            }
        }

        return obj;
    }

    /**
     * <b>isAvailableSong</b><br>
     *  allows to know if the user can add more audios.<br>
     * <b>pre:</b> the shop list must exist.<br>
     * <b>post:</b> the availability has been found.<br>
     * 
     * @return boolean true if can, false if not.
     */
    public boolean isAvailableSong() {
        boolean available = false;
        if (super.getShops().size() < MAX_AUDIOS) {
            available = true;
        }
        return available;
    }

    /**
     * <b>editPlaylist</b><br>
     * allows to edit a playlist.<br>
     * <b>pre:</b> the playlist must be already created.<br>
     * <b>post:</b> the playlist will be edited.<br>
     * 
     * @param name    is the name of the playlist to be edited.
     * @param newName is the new name of the playlist.
     * @return String the message that indicates if the playlist was edited
     *         successfully or not.
     */
    @Override
    public String editPlaylist(String name, String newName) {
        String msg = "The playlist was edited succesfully";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            obj.setName(newName);
        } else {
            msg = "The playlist does not exist";
        }
        return msg;
    }


    /**
     * <b>removeAudioFromPlaylist</b><br>
     * allows to remove an audio from a playlist.<br>
     * <b>pre:</b> the audio and the playlist must be already created.<br>
     * <b>post:</b> the audio will be removed from the playlist.<br>
     * 
     * @param name  is the name of the playlist.
     * @param audio is the audio to be removed.
     * @return String the message that indicates if the audio was removed
     *         successfully or not.
     */
    @Override
    public String removeAudioFromPlaylist(String name, Audio audio) {
        String msg = "The audio was removed succesfully";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            msg = obj.removeAudio(audio);
        } else {
            msg = "The playlist does not exist";
        }
        return msg;
    }

    /**
     * <b>sharePlaylist</b><br>
     * allows to share a playlist with another user.<br>
     * <b>pre:</b> the playlist must be already created.<br>
     * <b>post:</b> the playlist will be shared.<br>
     * 
     * @param name is the name of the playlist.
     * @return String the message that indicates if the playlist was shared
     *         successfully or not.
     */
    @Override
    public String sharePlaylist(String name) {
        String msg = "The playlist was shared succesfully";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            obj.share();
        } else {
            msg = "The playlist does not exist";
        }
        return msg;
    }

    /**
     * <b>songAd</b><br>
     * allows to know if an add will be played.<br>
     * <b>pre:</b> the shop list must be already created.<br>
     * <b>post:</b> the instruction about the ad will be give.<br>
     * @return boolean true if an add will be played, false if not.
     */
    public boolean songAd(){
        boolean ad = false;
        if (currentPlaybacks==2){
            ad = true;
            currentPlaybacks = 0;
        }else{
            currentPlaybacks++;
        }
        return ad;
    }

    public int getCurrentPlaybacks() {
        return currentPlaybacks;
    }

    public void setCurrentPlaybacks(int currentPlaybacks) {
        this.currentPlaybacks = currentPlaybacks;
    }

}