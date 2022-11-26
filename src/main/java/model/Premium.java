package model;
import java.util.ArrayList;
public class Premium extends Consumer {

    private ArrayList<Shop> songs;
    private ArrayList<Playlist> playlists;

    /**
     * Constructor allows to create a Premium's object.
     * 
     * @param nick is the user's nick.
     * @param id is the user's identification number.
     */
    public Premium(String nick, String id) {
        super(nick, id);

        songs = new ArrayList<>();
        playlists = new ArrayList<>();
    }

    /**
     * addAudio
     * allows to add an audio to the user's list of audios.
     * pre: the audio must be not added before.
     * post: the audio will be added to the user's list of audios.
     * 
     * @param audio is the audio to be added.
     * @return String the message that indicates if the audio was added successfully
     *         or not.
     */
    public String addAudio(Audio audio) {
        String msg = "";
        Shop obj = searchAudio(audio.getName());
        
        if (obj == null) {
                    if (audio instanceof Song) {
                        Song s = (Song) audio;
                        msg+=s.sell();
                        super.getShops().add(new Shop(s));
                    } else {
                        msg = "The audio is not a song.";
                    } 
        } else {
            msg = "The song already exists";
        }

        return msg;
    }

    /**
     * searchAudio
     * allows to search a song by its name.
     * pre: the song must be already created.
     * post: the song will be searched.
     * @param name is the name of the song to be searched.
     * @return Shop the shop that contains the song.
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
     * addPlaylist
     * allows to add a playlist to the user's list of playlists.
     * pre: the playlist must be not added before.
     * post: the playlist will be added to the user's list of playlists.
     * 
     * @param name is the name of the playlist to be added.
     * @return String the message that indicates if the playlist was added
     *         successfully or not.
     */
    @Override
    public String addPlaylist(String name) {

        String msg = "Playlist added successfully";
        Playlist obj = searchPlaylist(name);
        if (obj == null) {
            playlists.add(new Playlist(name));
        } else {
            msg = "The playlist already exists";
        }
        return msg;
    }

    /**
     * addAudioToPlaylist
     * allows to add an audio to a playlist.
     * pre: the audio must be already created.
     * post: the audio will be added to the playlist.
     * 
     * @param name  is the name of the playlist.
     * @param audio is the audio to be added.
     * @return String the message that indicates if the audio was added successfully
     *         or not.
     */
    @Override
    public String addAudioToPlaylist(String name, Audio audio) {

        String msg = "The audio was added successfully";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            msg = obj.addAudio(audio);
        } else {
            msg = "The playlist does not exist";
        }
        return msg;

    }

    /**
     * searchPlaylist
     * allows to search a playlist by its name.
     * pre: the playlist must be already created.
     * post: the playlist will be searched.
     * 
     * @param name is the name of the playlist to be searched.
     * @return PlayList the playlist found.
     */
    public Playlist searchPlaylist(String name) {

        Playlist obj = null;
        boolean search = false;
        if (playlists != null) {
            for (int i = 0; i < playlists.size() && !search; i++) {
                if (playlists.get(i).getName().equalsIgnoreCase(name)) {
                    obj = playlists.get(i);
                    search = true;
                }
            }
        }

        return obj;

    }

    /**
     * editPlaylist
     * allows to change the name of a playlist.
     * pre: the playlist must be already created.
     * post: the playlist will be edited.
     * 
     * @param name    is the name of the playlist to be edited.
     * @param newName is the new name of the playlist.
     * @return String the message that indicates if the playlist was edited
     *         successfully or not.
     */
    @Override
    public String editPlaylist(String name, String newName) {

        String msg = "The playlist was edited successfully";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            obj.setName(newName);
        } else {
            msg = "The playlist does not exist";
        }
        return msg;

    }

    /**
     * removeAudioFromPlaylist
     * allows to remove an audio from a playlist.
     * pre: the audio must be already created.
     * post: the audio will be removed from the playlist.
     * 
     * @param name  is the name of the playlist.
     * @param audio is the audio to be removed.
     * @return String the message that indicates if the audio was removed
     *         successfully or not.
     */
    @Override
    public String removeAudioFromPlaylist(String name, Audio audio) {

        String msg = "The audio was removed successfully";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            msg = obj.removeAudio(audio);
        } else {
            msg = "The playlist does not exist";
        }
        return msg;

    }

    /**
     * sharePlaylist
     * allows to share a playlist with another user.
     * pre: the playlist must be already created.
     * post: the playlist will be shared.
     * 
     * @param name is the name of the playlist.
     * @return String the message that indicates if the playlist was shared
     *         successfully or not.
     */
    @Override
    public String sharePlaylist(String name) {

        String msg = "The playlist does not exist";
        Playlist obj = searchPlaylist(name);
        if (obj != null) {
            msg = obj.share();
        }
        return msg;

    }

   
    @Override
    public ArrayList<Shop> getShops() {
        return songs;
    }

    @Override
    public void setShops(ArrayList<Shop> songs) {
        this.songs = songs;
    }

    @Override
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    @Override
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

}