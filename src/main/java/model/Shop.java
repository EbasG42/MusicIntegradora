package model;
import java.time.*;

public class Shop {

    private Song song;
    private LocalDate operationDate;

    public Shop(Song audio) {
        this.song = audio;
        operationDate = LocalDate.now();
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song audio) {
        this.song = audio;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

}
