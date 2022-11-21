package model;
public class Song extends Audio implements Sellable {

    private String album;
    private double price;
    private int sellAmount;

    private Genre genre;

    public Song(String name, int duration, String url, User autor, String album, double price, int genre) {
        super(name, duration, url, autor);
        this.album = album;
        this.price = price;
        sellAmount=0;


        switch (genre) {
            case 1:this.genre = Genre.ROCK;
                break;
            case 2:this.genre = Genre.POP;
                break;
            case 3:this.genre = Genre.TRAP;
                break;
            case 4:this.genre = Genre.HOUSE;
                break;
        }
    }

    @Override
    public String sell(){
        sellAmount++;
        super.getAutor().updateSoldInfo(super.getDuration());
        return "The song " + getName() + " has been sold";
        
    }

    public double totalSell(){
        double totalSells= sellAmount*price;
        return totalSells;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}