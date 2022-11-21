package model;
 import java.util.ArrayList;
import java.util.Random;
public class Playlist {

    public static Random random = new Random();

    private ArrayList<Audio> audioList;
    private String name;
    private int[][] matriz;
    private String id;
    private int songAmount;
    private int podcastAmount;
    
    public Playlist(String name) {
        this.name = name;
        audioList = new ArrayList<Audio>();
        matriz = new int[6][6];
        generateMatrizCode();
        songAmount = 0;
        podcastAmount = 0;
    }

    /**
     * <b>generateMatrizCode</b> allows to generate a random code.
     */
    public void generateMatrizCode() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = random.nextInt(10);
            }
        }

    }

    /**
     * <b>generateCode</b> allows to generate a code based on the matriz code.
     */
    public void generateCode() {
        String id1 = "";
        String id2 = "";
        String id3 = "";
        if (songAmount > 0 && podcastAmount > 0) {

            for (int i = 5; i >= 0; i--) {
                for (int j = 5; j >= 0; j--) {
                    int sum = i + j;
                    if (sum % 2 != 0) {
                        if (sum != 1) {
                            id += matriz[i][j] + " ";
                        }
                    }

                }
            }

        } else if (songAmount > 0) {

            for (int j = 0; j < matriz.length; j++) {
                if (j > 0 && j < matriz.length - 1) {
                    id2 += matriz[j][j] + " ";
                }
                id1 += matriz[matriz.length - (j + 1)][0] + " ";

                id3 += matriz[matriz.length - (j + 1)][matriz.length - 1] + " ";
            }
            id = id1 + id2 + id3;
        } else if (podcastAmount > 0) {

            for (int i = 0; i < matriz.length; i++) {
                id1 += matriz[0][i] + " ";
                if (i != 0) {
                    id2 += matriz[i][2] + " ";
                }
                if (i != matriz.length - 1) {
                    id3 += matriz[matriz.length - (i + 1)][3] + " ";
                }
            }
            id = id1.substring(0, 5) + " " + id2 + id3 + id1.substring(6, 11);

        }

    }

    /**
     * <b>share</b> allows to share the playlist.
     * 
     * @return String the playlist's code.
     */
    public String share() {
        String msg = "";
        if (!audioList.isEmpty()) {
            generateCode();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    msg += " " + matriz[i][j] + " ";
                }
                msg += "\n";
            }
            msg += "\n id:" + id;
        } else {
            msg = "The playlist is empty";
        }

        return msg;
    }

    /**
     * <b>addAudio</b> allows to add an audio to the playlist.
     * 
     * @param audio is the audio to be added.
     * @return String the message that indicates if the audio was added successfully
     *         or not.
     */
    public String addAudio(Audio audio) {

        String msg = "The audio was added successfully";
        Audio obj = searchAudio(audio.getName());
        if (obj == null) {

            audioList.add(audio);
            if (audio instanceof Song) {
                songAmount++;
            } else {
                podcastAmount++;
            }

        } else {
            msg = "The audio already exists";
        }
        return msg;

    }

    /**
     * <b>searchAudio</b> allows to search an audio in the playlist.
     * 
     * @param name is the name of the audio to be searched.
     * @return Audio the audio that was searched.
     */
    public Audio searchAudio(String name) {

        Audio obj = null;
        boolean search = false;
        if (audioList != null) {
            for (int i = 0; i < audioList.size() && !search; i++) {
                if (audioList.get(i).getName().equalsIgnoreCase(name)) {
                    obj = audioList.get(i);
                    search = true;
                }
            }
        }
        return obj;
    }

    /**
     * <b>showAudios</b><br>
     * allows to show the audios in the playlist.
     * 
     * @return String the audios in the playlist.
     */
    public String showAudios() {
        String msg = "";
        for (int i = 0; i < audioList.size(); i++) {
            msg += audioList.get(i).getName() + "\n";
        }
        return msg;
    }

    /**
     * <b>removeAudio</b> <br>
     * allows to remove an audio from the playlist.
     * 
     * @param audio is the audio to be removed.
     * @return String the message that indicates if the audio was removed
     *         successfully or not.
     */
    public String removeAudio(Audio audio) {

        String msg = "The audio was removed successfully";
        Audio obj = searchAudio(audio.getName());
        if (obj != null) {
            audioList.remove(audio);
        } else {
            msg = "The audio doesn't exist";
        }
        return msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Audio> getAudioList() {
        return audioList;
    }

    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }

    public int[][] getCode() {
        return matriz;
    }
    public void setCode(int[][] code) {
        this.matriz = code;
    }

}