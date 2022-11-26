package model;
import java.util.ArrayList;
import java.util.Random;
public class Controller {

    /**
     * A global variable that allows to create random numbers
     */
    public static Random ran = new Random();

    private String name;

    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    /**
     * Controller
     * This method is the constructor of the class
     * 
     * @param name This is the name of the program
     */
    public Controller(String name) {

        this.name = name;
        users = new ArrayList<>();
        audios = new ArrayList<>();

    }

    /**
     * addUser
     * This method adds a producer to the system.
     * pre: The user must not be in the system
     * post: The user is added to the system
     * 
     * @param nick This is the nick of the user
     * @param id This is the id of the user
     * @param name     This is the name of the user
     * @param url      This a url to a picture of the user
     * @param type     This is the type of user: artist or producer
     * @return String This returns a message that indicates if the user was added or
     *         not
     */
    public String addUser(String nick, String id, String name, String url, int type) {
        String msg = "The user was added successfully";
        User obj = searchUser(nick);
        if (obj == null) {
            if (type == 1) {
                obj = new Artist(nick, id, name, url);
            } else if (type == 2) {
                obj = new ContentCreator(nick, id, name, url);
            }
            users.add(obj);
        } else {
            msg = "The user already exists";
        }
        return msg;
    }

    /**
     * addUser
     * This method adds a Consumer to the system.
     * pre: The user must not be in the system
     * post: The user is added to the system
     * 
     * @param nick This is the nick of the user.
     * @param id This is the id of the user.
     * @param type     This is the type of user: premium or standaran
     * @return String This returns a message that indicates if the user was added or
     *         not
     */
    public String addUser(String nick, String id, int type) {
        String msg = "The user was added successfully";
        User obj = searchUser(nick);
        if (obj == null) {
            if (type == 1) {
                obj = new Premium(nick, id);
            } else if (type == 2) {
                obj = new Estandar(nick, id);
            }
            users.add(obj);
        } else {
            msg = "The user already exists";
        }

        return msg;

    }

    /**
     * addAudio
     * This method adds a song to the system.
     * pre: The song must not be in the system
     * post: The song is added to the system
     * 
     * @param name     This is the name of the song
     * @param m        This is the duration of the song in minutes
     * @param s        This is the duration of the song in seconds
     * @param url      This is the url to the album's cover of the song
     * @param nick This is the nick of the producer of the song
     * @param album    This is the name of the album of the song
     * @param price    This is the price of the song
     * @param genre    This is the genre of the song: rock, pop, etc.
     * @return String This returns a message that indicates if the song was added or
     *         not
     */
    public String addAudio(String name, int m, int s, String url, String nick, String album, double price,
            int genre) {
        String msg = "The audio was added successfully";
        Audio obj = searchAudio(name);
        User autor = searchUser(nick);
        int duration = toSeconds(0, m, s);
        if (autor != null) {

            if (autor instanceof Artist) {
                if (obj == null) {
                    Artist a = (Artist) autor;
                    obj = new Song(name, duration, url, autor, album, price, genre);
                    audios.add(obj);
                    a.addAudio((Song) obj);
                } else {
                    msg = "The audio already exists";
                }
            } else if (autor instanceof ContentCreator) {
                msg = "The user is not an artist";
            }

        } else {
            msg = "The user does not exist";
        }
        return msg;

    }

    /**
     * addAudio
     * This method adds a podcast to the system.
     * pre: The podcast must not be in the system
     * post: The podcast is added to the system
     * 
     * @param name        This is the name of the podcast
     * @param h           This is the duration of the podcast in hours
     * @param m           This is the duration of the podcast in minutes
     * @param s           This is the duration of the podcast in seconds
     * @param url         This is the url to the album's cover of the podcast
     * @param nick    This is the nick of the producer of the podcast
     * @param description This is the description of the podcast
     * @param category    This is the category of the podcast
     * @return String This returns a message that indicates if the podcast was added
     *         or not
     */
    public String addAudio(String name, int h, int m, int s, String url, String nick, String description,
            int category) {
        String msg = "The audio was added successfully";
        Audio obj = searchAudio(name);
        User autor = searchUser(nick);
        int duration = toSeconds(h, m, s);
        if (autor != null) {
            if (autor instanceof ContentCreator) {
                if (obj == null) {
                    ContentCreator a = (ContentCreator) autor;
                    obj = new Podcast(name, duration, url, autor, description, category);
                    audios.add(obj);
                    a.addAudio((Podcast) obj);
                } else {
                    msg = "The audio already exists";
                }
            } else if (autor instanceof Artist) {
                msg = "The user is not a content creator";
            }
        } else {
            msg = "The user does not exist";
        }
        return msg;
    }

    /**
     * addAudioToPlaylist
     * This method adds an audio to a playlist.
     * pre: The audio must not be in the playlist
     * post: The audio is added to the playlist
     * 
     * @param nameSong     This is the name of the song
     * @param namePlaylist This is the name of the playlist
     * @param nick     This is the nick of the user that owns the playlist
     * @return String This returns a message that indicates if the song was added to
     *         the playlist or not
     */
    public String addAudioToPlaylist(String nameSong, String namePlaylist, String nick) {
        String msg = "";
        User obj = searchUser(nick);
        Audio song = searchAudio(nameSong);
        if (obj != null) {
            if (song != null) {
                if (obj instanceof Consumer) {
                    msg = ((Consumer) obj).addAudioToPlaylist(namePlaylist, song);

                }
            } else {
                msg = "The audio does not exist";
            }
        } else {
            msg = "The user does not exist";
        }
        return msg;
    }

    /**
     * addPlaylistToConsumer
     * This method adds a playlist to a consumer user.
     * pre: The playlist must not be in the user
     * post: The playlist is added to the user
     * 
     * @param name     This is the name of the playlist
     * @param nick This is the nick of the user that owns the playlist
     * @return String This returns a message that indicates if the playlist was
     *         added to the user or not
     */
    public String addPlaylistToConsumer(String name, String nick) {
        String msg = "";
        User obj = searchUser(nick);
        if (obj != null) {
            if (obj instanceof Premium) {
                msg = ((Premium) obj).addPlaylist(name);
            } else if (obj instanceof Estandar) {
                msg = ((Estandar) obj).addPlaylist(name);
            }
        } else {
            msg = "The user does not exist";
        }

        return msg;

    }

    /**
     * searchUser
     * This method searches a user in the system.
     * pre: The user must be in the system
     * post: The user is searched in the system
     * 
     * @param nick This is the nick of the user to search
     * @return User This returns the user if it exists or null if it does not
     */
    public User searchUser(String nick) {
        User obj = null;
        boolean found = false;
        for (int i = 0; i < users.size() && !found; i++) {
            if (users.get(i).getnick().equals(nick)) {
                obj = users.get(i);
                found = true;
            }
        }
        return obj;
    }

    /**
     * searchAudio
     * This method searches an audio in the system.
     * pre: The audio must be in the system
     * post: The audio is searched in the system
     * 
     * @param name This is the name of the audio to search
     * @return Audio This returns the audio if it exists or null if it does not
     */
    public Audio searchAudio(String name) {
        Audio obj = null;
        boolean found = false;
        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equals(name)) {
                obj = audios.get(i);
                found = true;
            }
        }
        return obj;
    }

    /**
     * playAudios
     * This method plays an audio.
     * pre: The audio must be in the system
     * post: The audio is played
     * 
     * @param nick   This is the nick of the user that will play the audio
     * @param nameAudios This is the name of the audio to play
     * @return String This returns a message that indicates if the audio was played
     * 
     */
    public String playAudios(String nick, String nameAudios) {
        String msg = "";
        User obj = searchUser(nick);
        Audio audio = searchAudio(nameAudios);
        if (obj != null) {
            if (obj instanceof Consumer) {
                if (audio != null) {
                    if (obj instanceof Premium premium) {
                        msg = premium.playAudio(audio);
                    } else if (obj instanceof Estandar obj1) {
                        if (audio instanceof Song) {
                            if (obj1.songAd()) {
                                msg += showAd();
                                msg += "\n" + ((Estandar) obj).playAudio(audio);
                            } else {
                                msg = ((Estandar) obj).playAudio(audio);
                            }
                        } else {
                            msg += showAd();
                            msg += "\n" + ((Estandar) obj).playAudio(audio);
                        }

                    }
                } else {
                    msg = "The audio does not exist";
                }
            }
        } else {
            msg = "The user does not exist";
        }
        return msg;
    }

    /**
     * showAd
     * This method shows an ad.
     * pre: The ad must be in the system
     * post: The ad is shown
     * 
     * @return String This returns a message showing the ad
     */
    public String showAd() {
        String msg = "";
        int ad = ran.nextInt(3);
        switch (ad) {
            case 0:
                msg = Advertisement.COKE.plays();
                break;
            case 1:
                msg = Advertisement.NIKE.plays();
                break;
            case 2:
                msg = Advertisement.MyM.plays();
                break;
        }
        return msg;
    }

    /**
     * buySong
     * This method buys a song.
     * pre: The song must be in the system and must not have been sold
     * post: The song is bought
     * 
     * @param nick   Is the nick of the user that will buy the song
     * @param nameAudios Is the name of the song to buy
     * @return String This returns a message that indicates if the song was bought
     */
    public String buySong(String nick, String nameAudios) {
        String msg = "";
        User obj = searchUser(nick);
        Audio obj1 = searchAudio(nameAudios);
        if (obj != null) {
            if (obj1 != null && obj1 instanceof Song) {
                if (obj instanceof Premium premium) {
                    msg = premium.addAudio(obj1);
                } else if (obj instanceof Estandar estandar) {
                    msg = estandar.addAudio(obj1);
                }
            } else {
                msg = "The audio does not exist or is not a song";
            }
        } else {
            msg = "The user does not exist";
        }

        return msg;
    }

    /**
     * totalplaybackGanres
     * This method shows the total playback of the genres.
     * pre: The genres must be played
     * post: The total playback of the genres is found
     * 
     * @return int[] This returns an array with the total playback of the genres
     */
    public int[] totalplaybackGenres() {
        int[] total = new int[4];
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Consumer) {

                Consumer obj = (Consumer) users.get(i);

                for (int j = 0; j < total.length; j++) {
                    total[j] += obj.playbackPerGenre()[j];
                }

            }
        }
        return total;
    }

    /**
     * mostListenedGenre
     * This method shows the most listened genre.
     * pre: The genres must be played
     * post: The most listened genre is found and shown
     * 
     * @return String This returns a message that shows the most listened genre
     */
    public String mostListenedGenre() {
        String msg = "";
        int[] total = new int[4];
        for (int i = 0; i < total.length; i++) {
            total[i] = totalplaybackGenres()[i];
        }
        int max = 0;
        int pos = -1;
        for (int i = 0; i < total.length; i++) {
            if (total[i] > max) {
                max = total[i];
                pos = i;
            }
        }
        switch (pos) {
            case 0:msg = "The most listened genre is: Pop, with a total of " + max + " plays";
                break;
            case 1:msg = "The most listened genre is: Rock, with a total of " + max + " plays";
                break;
            case 2:msg = "The most listened genre is: trap, with a total of " + max + " plays";
                break;
            case 3:msg = "The most listened genre is: House, with a total of " + max + " plays";
                break;
            default:msg = "No songs have been listened to";
                break;
        }
        return msg;
    }

    /**
     * mostListenedGenre
     * This method shows the most listened genre for an especific user.
     * pre: The genres must be played
     * post: The most listened genre is found and shown
     * 
     * @param nick Is the nick of the user to search
     * @return String This returns a message that shows the most listened genre
     */
    public String mostListenedGenre(String nick) {
        String msg = "";
        User obj = searchUser(nick);
        if (obj != null) {

            if (obj instanceof Consumer obj1) {
                msg = obj1.mostHearedGenre();
            }

        } else {
            msg = "The user does not exist";
        }
        return msg;

    }

    /**
     * totalplaybackCategory
     * This method shows the total playback of the categories.
     * pre: The categories must be played
     * post: The total playback of the categories is found
     * 
     * @return int[] This returns an array with the total playback of the categories
     */
    public int[] totalplaybackCategories() {
        int[] total = new int[4];
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Consumer obj) {
                for (int j = 0; j < total.length; j++) {
                    total[j] += obj.playbackPerCategory()[j];
                }

            }
        }
        return total;
    }

    /**
     * mostListenedCategory
     * This method shows the most listened category.
     * pre: The categories must be played
     * post: The most listened category is found and shown
     * 
     * @return String This returns a message that shows the most listened category
     */
    public String mostListenedCategory() {
        String msg = "";
        int[] total = new int[4];
        for (int i = 0; i < total.length; i++) {
            total[i] = totalplaybackCategories()[i];
        }
        int max = 0;
        int pos = -1;
        for (int i = 0; i < total.length; i++) {
            if (total[i] > max) {
                max = total[i];
                pos = i;
            }
        }
        switch (pos) {
            case 0:msg = "The most listened category is: Politics, with a total of " + max + " plays";
                break;
            case 1:msg = "The most listened category is: Entertainment, with a total of " + max + " plays";
                break;
            case 2:msg = "The most listened category is: Videogames, with a total of " + max + " plays";
                break;
            case 3:msg = "The most listened category is: Fashion, with a total of " + max + " plays";
                break;
            default:msg = "No podcast have been listened to";
                break;
        }
        return msg;
    }

    /**
     * mostListenedCategory
     * This method shows the most listened category for an especific user.
     * pre: The categories must be played
     * post: The most listened category is found and shown
     * 
     * @param nick Is the nick of the user to search
     * @return String This returns a message that shows the most listened category
     */
    public String mostListenedCategory(String nick) {
        String msg = "";
        User obj = searchUser(nick);
        if (obj != null) {

            if (obj instanceof Consumer obj1) {
                msg = obj1.mostHearedCategory();
            }

        } else {
            msg = "The user does not exist";
        }
        return msg;

    }

    /**
     * showTotalPlaybackSongs
     * This method shows the total playback of the songs.
     * pre: The songs must be played
     * post: The total playback of the songs is found
     * 
     * @return String This returns a message that shows the total playback of the
     *         songs
     */
    public String showTotalPlaybackSongs() {
        String msg = "";
        int total = 0;
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                total += (audios.get(i)).getTotalPlays();
            }
        }

        if (total > 0) {
            msg = "The total of plays of the songs is: " + total;
        } else {
            msg = "There are no played songs";
        }

        return msg;
    }

    /**
     * showTotalPlaybackPodcasts
     * This method shows the total playback of the podcasts.
     * pre: The podcasts must be played
     * post: The total playback of the podcasts is found
     * 
     * @return String This returns a message that shows the total playback of the
     *         podcasts
     */
    public String showTotalPlaybackPodcasts() {
        String msg = "";
        int total = 0;
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Podcast) {
                total += audios.get(i).getTotalPlays();
            }
        }
        if (total != 0) {
            msg = "The total of plays of the podcasts is " + total;
        } else {
            msg = "There are no played podcasts";
        }

        return msg;
    }

    /**
     * editPlaylist
     * This method edits the name of a playlist.
     * pre: The playlist must exist
     * post: The name of the playlist is edited
     * 
     * @param nick This is the nick of the consumer that owns the playlist
     * @param name     This is the name of the playlist to edit
     * @param newName  This is the new name of the playlist
     * @return String This returns a message that indicates if the playlist was
     *         edited or not
     */
    public String editPlaylist(String nick, String name, String newName) {
        String msg = "The playlist was edited successfully";
        User obj = searchUser(nick);
        if (obj != null) {
            if (obj instanceof Consumer consumer) {
                msg = consumer.editPlaylist(name, newName);
            } else {
                msg = "The user is not a consumer";
            }
        } else {
            msg = "The user does not exist";
        }

        return msg;
    }

    /**
     * removeAudioFromPlaylist
     * This method removes an audio from a playlist.
     * pre: The playlist must exist
     * post: The audio is removed from the playlist
     * 
     * @param name     This is the name of the playlist
     * @param nick This is the nick of the consumer that owns the playlist
     * @param songName This is the name of the audio to remove
     * @return String This returns a message that indicates if the audio was removed
     *         or not
     */
    public String removeAudioFromPlaylist(String name, String nick, String songName) {
        String msg = "The song was removed successfully";
        User obj = searchUser(nick);
        Audio song = searchAudio(songName);
        if (obj != null && song != null) {
            if (obj instanceof Consumer) {
                msg = ((Consumer) obj).removeAudioFromPlaylist(name, song);
            } else {
                msg = "The user is not a consumer ";
            }
        } else {
            msg = "The user or the song does not exist";
        }

        return msg;

    }

    /**
     * sharePlaylist
     * This method shares a playlist with another user.
     * pre: The playlist must exist
     * post: The playlist is shared with another user
     * 
     * @param nick This is the nick of the consumer that owns the playlist
     * @param name     This is the name of the playlist
     * @return String This returns a message with the code of the playlist
     */
    public String sharePlaylist(String nick, String name) {
        String msg = "The playlist was shared successfully";
        User obj = searchUser(nick);
        if (obj != null) {
            if (obj instanceof Consumer) {
                msg = ((Consumer) obj).sharePlaylist(name);
            } else {
                msg = "The user is not a consumer";
            }
        } else {
            msg = "The user does not exist";
        }

        return msg;
    }

    /**
     * top5
     * This method shows the top 5 of the most listened songs and podcast.
     * pre: The songs and podcasts must be played
     * post: The top 5 of the most listened songs and podcast is shown
     * 
     * @return String This returns a message that shows the top 5 of the most
     *         listened songs and podcast
     */
    public String top5() {
        String msg = "no songs or podcasts have been listened to";
        Artist[] top5A = new Artist[5];
        ContentCreator[] top5C = new ContentCreator[5];
        ArrayList<Artist> artists = new ArrayList<>();
        ArrayList<ContentCreator> contentCreators = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                artists.add((Artist) users.get(i));
            } else if (users.get(i) instanceof ContentCreator) {
                contentCreators.add((ContentCreator) users.get(i));
            }
        }

        Productor max = null;
        int counter = 0;
        max = artists.get(0);
        for (int i = 0; i < artists.size(); i++) {

            if (artists.get(i).getaccumPlay() > max.getaccumPlay()) {
                max = artists.get(i);
            }
            if (i == artists.size() - 1) {
                if ((top5A[4] == null)) {

                    top5A[counter] = (Artist) max;
                    counter++;
                    artists.remove(max);
                    if (!artists.isEmpty()) {
                        max = artists.get(0);
                        i = 0;
                    }
                }
            }

        }

        counter = 0;
        max = contentCreators.get(0);
        for (int i = 0; i < contentCreators.size(); i++) {

            if (contentCreators.get(i).getaccumPlay() > max.getaccumPlay()) {
                max = contentCreators.get(i);
            }
            if (i == contentCreators.size() - 1) {
                if ((top5C[4] == null)) {

                    top5C[counter] = (ContentCreator) max;
                    counter++;
                    contentCreators.remove(max);
                    if (!contentCreators.isEmpty()) {
                        max = contentCreators.get(0);
                        i = 0;
                    }
                }
            }

        }
        for (int i = 0; i < top5A.length; i++) {
            if (top5A[i] != null) {
                msg = "The top 5 artists are:\n ";
                msg += (i + 1) + "." + top5A[i].getName() + " with " + top5A[i].getaccumPlay() + " plays";
            }
        }
        for (int i = 0; i < top5C.length; i++) {
            if (top5C[i] != null) {
                msg += "\nThe top 5 content creators are:\n ";
                msg += (i + 1) + "." + top5C[i].getName() + " with " + top5C[i].getaccumPlay() + " plays";
            }
        }

        return msg;

    }

    /**
     * top10
     * This method shows the top 10 of the most artist and content creator.
     * pre: The artist and content creator must be created
     * post: The top 10 of the most artist and content creator is shown
     * 
     * @return String This returns a message that shows the top 10 of the most
     *         artist and content creator.
     */
    public String top10() {
        String msg = "no songs or podcasts have been listened to";
        Song[] top10A = new Song[10];
        Podcast[] top10P = new Podcast[10];
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<Podcast> podcasts = new ArrayList<>();
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                songs.add((Song) audios.get(i));
            } else if (audios.get(i) instanceof Podcast) {
                podcasts.add((Podcast) audios.get(i));
            }
        }

        int counter = 0;
        Audio max = null;
        max = songs.get(0);
        for (int i = 0; i < songs.size(); i++) {

            if (songs.get(i).getTotalPlays() > max.getTotalPlays()) {
                max = songs.get(i);
            }
            if (i == songs.size() - 1) {
                if ((top10A[9] == null)) {

                    top10A[counter] = (Song) max;
                    counter++;
                    songs.remove(max);
                    if (!songs.isEmpty()) {
                        max = songs.get(0);
                        i = 0;
                    }
                }
            }

        }

        counter = 0;
        max = podcasts.get(0);
        for (int i = 0; i < podcasts.size(); i++) {

            if (podcasts.get(i).getTotalPlays() > max.getTotalPlays()) {
                max = podcasts.get(i);
            }
            if (i == podcasts.size() - 1) {
                if ((top10P[9] == null)) {

                    top10P[counter] = (Podcast) max;
                    counter++;
                    podcasts.remove(max);
                    if (!podcasts.isEmpty()) {
                        max = podcasts.get(0);
                        i = 0;
                    }
                }
            }

        }
        for (int i = 0; i < top10A.length; i++) {
            if (top10A[i] != null) {
                msg = "The top 10 songs are:\n ";
                msg += (i + 1) + "." + top10A[i].getName() + "with genre: " + top10A[i].getGenre().name() + " with "
                        + top10A[i].getTotalPlays() + " plays";
            }
        }

        for (int i = 0; i < top10P.length; i++) {
            if (top10P[i] != null) {
                msg += "\nThe top 10 podcasts are:\n ";
                msg += (i + 1) + "." + top10P[i].getName() + "with Category: " + top10P[i].getCategory().name()
                        + " with " + top10P[i].getTotalPlays() + " plays";
            }
        }

        return msg;
    }

    /**
     * reportByGenre
     * This method shows the report by genre.
     * pre: The songs must be created
     * post: The report by genre is shown
     * 
     * @param genre The genre of the song
     * @return String This returns a message that shows the report by genre.
     */
    public String reportByGenre(String genre) {
        String msg = "The genre has not been listened to";
        int count = 0;
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {

                Song s = (Song) audios.get(i);
                if (s.getGenre().name().equals(genre)) {

                    count += s.getTotalPlays();
                }
            }
        }
        if (count > 0) {
            msg = "The genre " + genre + " has been listened " + count + " times";
        }
        return msg;
    }

    /**
     * mostSoldSong
     * This method shows the most sold song.
     * pre: The songs must be created
     * post: The most sold song is shown
     * 
     * @return String This returns a message that shows the most sold song.
     */
    public String mostSoldSong() {
        String msg = "No songs have been sold";
        Song max = null;
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                Song s = (Song) audios.get(i);
                if (max == null) {
                    max = s;
                }
                if (s.getSellAmount() > max.getSellAmount()) {
                    max = s;
                }
            }
        }
        if (max != null) {
            if (max.getSellAmount() > 0) {
                msg = "The most sold song is " + max.getName() + " with " + max.getSellAmount() + " sales";
            }
        }
        return msg;
    }

    /**
     * toSeconds
     * This method converts a time in seconds.
     * pre: time must be in the format hh:mm:ss
     * post: time in seconds
     */
    public int toSeconds(int h, int m, int s) {
        int seconds = 0;
        seconds += (h * 3600) + m * 60 + s;
        return seconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }

}