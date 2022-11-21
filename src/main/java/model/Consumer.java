package model;
import java.util.ArrayList;
public abstract class Consumer extends User {

    private ArrayList<Playlist> playlists;
    private ArrayList<Shop> shops;
    private ArrayList<Playback> playbacks;

    /**
     * <b>Constructor</b> allows to create a Consumer's object.
     * 
     * @param nick is the user's nick.
     * @param id is the user's identification number.
     */
    public Consumer(String nick, String id) {
        super(nick, id);
        playlists = new ArrayList<Playlist>();
        shops = new ArrayList<Shop>();
        playbacks = new ArrayList<Playback>();
    }

    /**
     * <b>playAudio</b><br>
     *  allows to play an audio.<br>
     * <b>pre:</b> the audio must be already created.<br>
     * <b>post:</b> the audio will be played.<br>
     * @param audio is the audio that will be played.
     * @return String the message that indicates if the audio was played.
     */
    public String playAudio(Audio audio) {
        String msg = "";
        msg += audio.plays();
        Playback obj = searchPlayback(audio.getName());
        if (obj == null) {
            playbacks.add(new Playback(audio));
        } else {
            obj.updateInfo();
        }

        return msg;

    }

    /**
     * <b>mostHearedGenre</b><br>
     * allows to get the most heared genre.<br>
     * <b>pre:</b> the playbacks must be already created.<br>
     * <b>post:</b> the most heared genre will be returned.<br>
     * @return String the most heared genre.
     */
    public String mostHearedGenre() {

        String msg = "";
        int[] genres = playbackPerGenre();
        int max = 0;
        int pos = -1;
        for (int i = 0; i < genres.length; i++) {
            if (genres[i] > max) {
                max = genres[i];
                pos = i;
            }
        }
        switch (pos) {
            case 0:msg = "The most heared genre is: Pop, with " + max + " plays.";
                break;
            case 1:msg = "The most heared genre is: Rock, with " + max + " plays.";
                break;
            case 2:msg = "The most heared genre is: trap, with " + max + " plays.";
                break;
            case 3:msg = "The most heared genre is: house, with " + max + " plays.";
                break;
            default:
                msg = "no songs have been played.";
                break;
        }
        return msg;
    }

    /**
     * <b>playbackPerGenre</b><br>
     * allows to get the number of playbacks per genre.<br>
     * <b>pre:</b> the playbacks must be already created.<br>
     * <b>post:</b> the number of playbacks per genre will be returned.<br>
     * @return int[] the number of playbacks per genre.
     */
    public int[] playbackPerGenre() {
        int[] playbacksG = new int[4];
        for (int i = 0; i < playbacks.size(); i++) {
            if (playbacks.get(i).getAudio() instanceof Song s) {
                switch (s.getGenre()) {
                    case POP:
                        playbacksG[0] += playbacks.get(i).getPlayback();
                        break;
                    case ROCK:
                        playbacksG[1] += playbacks.get(i).getPlayback();
                        break;
                    case TRAP:
                        playbacksG[2] += playbacks.get(i).getPlayback();
                        break;
                    case HOUSE:
                        playbacksG[3] += playbacks.get(i).getPlayback();
                        break;
                    default:
                        break;
                }
            }
        }
        return playbacksG;
    }

    /**
     * <b>mostHearedCategory</b><br>
     * allows to get the most heared category.<br>
     * <b>pre:</b> the playbacks must be already created.<br>
     * <b>post:</b> the most heared category will be returned.<br>
     * 
     * @return String the most heared category.
     */
    public String mostHearedCategory() {

        String msg = "";
        int[] category = playbackPerCategory();
        int max = 0;
        int pos = -1;
        for (int i = 0; i < category.length; i++) {
            if (category[i] > max) {
                max = category[i];
                pos = i;
            }
        }
        switch (pos) {
            case 0:
                msg = "The most heared genre is: Policy, with " + max + " plays.";
                break;
            case 1:
                msg = "The most heared genre is: Entertainment, with " + max + " plays.";
                break;
            case 2:
                msg = "The most heared genre is: Videogames, with " + max + " plays.";
                break;
            case 3:
                msg = "The most heared genre is: Fashion, with " + max + " plays.";
                break;
            default:
                msg = "no songs have been played.";
                break;
        }
        return msg;
    }

    public int[] playbackPerCategory() {
        int[] playbacksC = new int[4];
        for (int i = 0; i < playbacks.size(); i++) {
            if (playbacks.get(i).getAudio() instanceof Podcast) {
                Podcast p = (Podcast) playbacks.get(i).getAudio();
                switch (p.getCategory()) {
                    case POLICY:
                        playbacksC[0] += playbacks.get(i).getPlayback();
                        break;
                    case ENTERTAINMENT:
                        playbacksC[1] += playbacks.get(i).getPlayback();
                        break;
                    case VIDEOGAMES:
                        playbacksC[2] += playbacks.get(i).getPlayback();
                        break;
                    case FASHION:
                        playbacksC[3] += playbacks.get(i).getPlayback();
                        break;
                    default:
                        break;
                }
            }
        }
        return playbacksC;
    }


    /**
     * <b>searchPlayback</b><br>
     * allows to search a playback.<br>
     * <b>pre:</b> the playbacks must be already created.<br>
     * <b>post:</b> the playback will be returned.<br>
     * @param name is the name of the audio that will be searched.
     */
    public Playback searchPlayback(String name) {
        Playback obj = null;
        boolean search = false;
        for (int i = 0; i < playbacks.size() && !search; i++) {
            if (playbacks.get(i).getAudio().getName().equalsIgnoreCase(name)) {
                obj = playbacks.get(i);
                search = true;
            }
        }
        return obj;
    }

    /**
     * <b>addPlaylist</b><br>
     *  allows to add a playlist.<br>
     * <b>pre:</b> the playlists must be already created.<br>
     * <b>post:</b> the playlist will be added.<br>
     * 
     * @param name is the playlist's name.
     * @return String the message of the operation.
     */
    public abstract String addPlaylist(String name);

    /**
     * <b>addAudioToPlaylist</b><br>
     *  allows to add an audio to a playlist.<br>
     * <b>pre:</b> the playlists must be already created.<br>
     * <b>post:</b> the audio will be added to the playlist.<br>
     * 
     * @param name  is the playlist's name.
     * @param audio is the audio to be added.
     * @return String the message of the operation.
     */
    public abstract String addAudioToPlaylist(String name, Audio audio);

    /**
     * <b>editPlaylist</b> <br>
     * allows to edit a playlist's name.<br>
     * <b>pre:</b> the playlists must be already created.<br>
     * <b>post:</b> the playlist's name will be edited.<br>
     * 
     * @param name    is the playlist's name.
     * @param newName is the new playlist's name.
     * @return String the message of the operation.
     */
    public abstract String editPlaylist(String name, String newName);

    /**
     * <b>removeAudioFromPlaylist</b><br>
     *  allows to remove an audio from a playlist.<br>
     * <b>pre:</b> the playlists must be already created.<br>
     * <b>post:</b> the audio will be removed from the playlist.<br>
     * 
     * @param name  is the playlist's name.
     * @param audio is the audio to be removed.
     * @return String the message of the operation.
     */
    public abstract String removeAudioFromPlaylist(String name, Audio audio);

    /**
     * <b>sharePlaylist</b><br>
     *  allows to share a playlist.<br>
     * <b>pre:</b> the playlists must be already created.<br>
     * <b>post:</b> the playlist will be shared.<br>
     * 
     * @param name is the playlist's name.
     * @return String the message of the operation.
     */
    public abstract String sharePlaylist(String name);

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }
}