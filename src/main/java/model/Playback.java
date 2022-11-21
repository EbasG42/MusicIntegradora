
package model;

public class Playback {

    private Audio audio;
    private int playback;

    public Playback(Audio audio) {
        this.audio = audio;
        playback = 0;
    }

    public void updateInfo(){
        playback++;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public int getPlayback() {
        return playback;
    }

    public void setPlayback(int playback) {
        this.playback = playback;
    }

}
