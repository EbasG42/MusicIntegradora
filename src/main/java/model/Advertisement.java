package model;
public enum Advertisement implements Playable {

    NIKE("Nike, Just do it"),
    COKE("Coca-cola, open happiness"),
    MyM("M&M, Melts in your mouth, not in your hands");
    
    private String message;
    
    Advertisement(String message) {
        this.message = message;
    }
    @Override
    public String plays() {
        String msg = "Sponsored by: " + message;
        return msg;
    }

}