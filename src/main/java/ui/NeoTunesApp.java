package ui;
import model.NeoTunesController;
import java.util.Scanner;
public class NeoTunesApp {
    public static Scanner scanner = new Scanner(System.in);
    private NeoTunesController control;
    public NeoTunesApp() {
        control = new NeoTunesController("NeoTunes");
    }
    public static void main(String[] args) {

        NeoTunesApp app = new NeoTunesApp();
        int option;
        boolean salir=false;
        while(!salir){
            System.out.println("\nWelcome, please select the option you need");
        System.out.println("""
                           1.Add an user
                           2.Add an audio
                           3.Create a playlist.
                            4.Edit a playList
                           5.Share a playList
                           6.Play a song or a podcast
                           7.Buy a song
                           8.To Show the total playback for audio type.
                           9.To Show the most heard song genre
                           10.To Show the most heard podcast category
                           11.To Show the top 5 artists or producer
                           12.To Show the top 5 podcast or songs
                           13.To Show the total sales for all the genres
                           14. To show the most saled song
                           0 to exit""");            
        option=scanner.nextInt();
        switch (option) {
            case 1:app.addUser();
                break;
            case 2:app.addAudio();
                break;
            case 3:app.createPlaylist();
                break;
            case 4:app.editPlaylist();
                break;
            case 5:app.sharePlaylist();
                break;
            case 6:app.playAudio();
                break;
            case 7:app.buyAudio();
                break;
            case 8:app.showTotalPlayback();
                break;
            case 9:app.showMostHearedGenre();
                break;
            case 10:app.showMostHearedCategory();
                break;
            case 11:app.showTop5Producers();
                break;
            case 12:app.showTop10Audios();
                break;
            case 13:app.showTotalSales();
                break;
            case 14:app.showMostSaledSong();
                break;
            case 0:
                salir=true;
                System.out.println("Thanks for using our services, good bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        }

    }
    /**
     * <b>addUser</b><br>
     * reads the info and call the controller to add an user to the application.<br>
     */
    public void addUser() {
        System.out.print("Please input the user's nick : ");
        scanner.nextLine();
        String nick = scanner.nextLine();
        System.out.print("Input the user's id : ");
        String id = scanner.nextLine();
        int option;
        do {
            System.out.print("Select the User's type:\n1. Productor\n2. Consumer :\n ");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1 && option != 2) {
                System.out.println("Invalid option");
            }
        } while (option != 1 && option != 2);
        if (option == 1) {
            System.out.println("Enter the productor´s name : ");
            String producerName = scanner.nextLine();
            System.out.println("Enter the productor´s url : ");
            String producerUrl = scanner.nextLine();
            do {
                System.out.print("Select the Productor´s type:\n1. Artist\n2. Content creator : \n ");
                option = scanner.nextInt();
                scanner.nextLine();
                if (option != 1 && option != 2) {
                    System.out.println("Invalid option");
                }
            } while (option != 1 && option != 2);
            System.out.println(control.addUser(nick, id, producerName, producerUrl, option));
        } else {
            do {
                System.out.print("Select the Consumer's type:\n1. Premium\n2. Standard :\n ");
                option = scanner.nextInt();
                scanner.nextLine();
                if (option != 1 && option != 2) {
                    System.out.println("Invalid option");
                }
            } while (option != 1 && option != 2);
            System.out.println(control.addUser(nick, id, option));
        }
    }

    /**
     * <b>addAudio</b><br>
     * reads the info and call the controller to add an audio to the
     * application.<br>
     */
    public void addAudio() {
        System.out.print("Input the audio's name : ");
        scanner.nextLine();
        String audioName = scanner.nextLine();
        System.out.print("Enter the audio's autor : ");
        String audioAutor = scanner.nextLine();
        int option;
        do {
            System.out.println("Select between:\n1. Song\n2. Podcast : ");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option != 1 && option != 2) {
                System.out.println("Invalid option");
            }

        } while (option != 1 && option != 2);
        if (option == 1) {
            boolean correct = false;
            int minutes = 0;
            int seconds = 0;
            do {
                System.out.println(
                        "Enter the song's duration in format mm:ss, please fill 2 numbers per min/sec : ");
                String songDuration = scanner.nextLine();
                if (songDuration.length() != 5) {
                    System.out.println("Invalid format");
                } else if (songDuration.charAt(2) != ':') {
                    System.out.println("Invalid format");
                } else if (Integer.parseInt(songDuration.substring(0, 2)) > 59
                        || Integer.parseInt(songDuration.substring(3, 5)) > 59) {

                    System.out.println("Invalid format");

                } else {
                    minutes = Integer.parseInt(songDuration.substring(0, 2));
                    seconds = Integer.parseInt(songDuration.substring(3, 5));
                    correct = true;
                }
            } while (!correct);
            System.out.print("Enter the album's cover url : ");
            String albumCoverUrl = scanner.nextLine();
            System.out.print("Enter the  album's name : ");
            String albumName = scanner.nextLine();
            System.out.print("Enter the song price : ");
            double songPrice = scanner.nextDouble();
            scanner.nextLine();
            do {
                System.out.println("Select the song genre:\n1. Rock\n2. Pop\n3. Trap\n4. House : ");
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 1 && option > 4) {
                    System.out.println("Invalid option");
                }
            } while (option < 1 && option > 4);
            System.out.println(control.addAudio(audioName, minutes, seconds, albumCoverUrl, audioAutor,
                    albumName, songPrice, option));

        } else {

            boolean correct = false;
            int minutes = 0;
            int seconds = 0;
            int hours = 0;
            do {
                System.out.println(
                        "Enter the song duration in format hh:mm:ss, please each space with two digits : ");
                String songDuration = scanner.nextLine();
                if (songDuration.length() != 8) {
                    System.out.println("Invalid format");
                } else if (songDuration.charAt(2) != ':' && songDuration.charAt(5) != ':') {
                    System.out.println("Invalid format");
                } else if (Integer.parseInt(songDuration.substring(0, 2)) > 24
                        || Integer.parseInt(songDuration.substring(3, 5)) > 59
                        || Integer.parseInt(songDuration.substring(6, 8)) > 59) {

                    System.out.println("Invalid format");

                } else {
                    hours = Integer.parseInt(songDuration.substring(0, 2));
                    minutes = Integer.parseInt(songDuration.substring(3, 5));
                    seconds = Integer.parseInt(songDuration.substring(6, 8));
                    correct = true;
                }
            } while (!correct);
            System.out.print("Enter the podcast's image url : ");
            String podcastImageUrl = scanner.nextLine();
            System.out.print("Enter a description for the podcast : ");
            String podcastDescription = scanner.nextLine();
            do {
                System.out.println(
                        "Select the podcast's category :\n1. Politics\n2. Entertainment\n3. Videogames\n4.Fashion");
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 1 && option > 4) {
                    System.out.println("Invalid option");
                }
            } while (option < 1 && option > 4);

            System.out.println(control.addAudio(audioName, hours, minutes, seconds, podcastImageUrl,
                    audioAutor, podcastDescription, option));
        }

    }

    /**
     * <b>createPlaylist</b><br>
     * reads the info and call the controller to add a playlist to the
     * application.<br>
     */
    public void createPlaylist() {
        System.out.println("\n-Playlist creation-");
        System.out.print("Enter the playlist's name : ");
        scanner.nextLine();
        String playlistName = scanner.nextLine();
        System.out.print("Enter the user's nick : ");
        String usernick = scanner.nextLine();
        System.out.println(control.addPlaylistToConsumer(playlistName, usernick));
    }

    /**
     * <b>editToPlaylist</b><br>
     * reads the info and call the controller to change some data of a playlist.<br>
     */
    public void editPlaylist() {
        System.out.print("Enter the playlist's name : ");
        scanner.nextLine();
        String playlistName = scanner.nextLine();
        System.out.print("Enter the user's nick : ");
        String usernick = scanner.nextLine();
        int option;
        do {
            System.out.println("Select the action to do : \n1. Change playlist's name\n2. Add audio\n3. Remove audio");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 && option > 3) {
                System.out.println("Invalid option");
            }
        } while (option < 1 && option > 3);
        switch (option) {
            case 1:
                System.out.print("Enter the new playlist's name : ");
                String newPlaylistName = scanner.nextLine();
                System.out.println(control.editPlaylist(usernick, playlistName, newPlaylistName));
                break;
            case 2:
                {
                    System.out.print("Enter the audio's name : ");
                    String audioName = scanner.nextLine();
                    System.out.println(control.addAudioToPlaylist(audioName, playlistName, usernick));
                    break;
                }
            default:
                {
                    System.out.print("Enter the audio's name : ");
                    String audioName = scanner.nextLine();
                    System.out.println(control.removeAudioFromPlaylist(playlistName, usernick, audioName));
                    break;
                }
        }

    }

    /**
     * <b>sharePlaylist</b><br>
     * reads the info and call the controller to share a playlist with another
     * user.<br>
     */
    public void sharePlaylist() {
        System.out.print("Enter the playlist's name : ");
        scanner.nextLine();
        String playlistName = scanner.nextLine();
        System.out.print("Enter the user's nick : ");
        String usernick = scanner.nextLine();
        System.out.println(control.sharePlaylist(usernick, playlistName));
    }

    /**
     * <b>playAudio</b><br>
     * reads the info and call the controller to simulate the play an audio.<br>
     */
    public void playAudio() {
        System.out.print("Enter the audio's name : ");
        scanner.nextLine();
        String audioName = scanner.nextLine();
        System.out.print("Enter the user's nick : ");
        String usernick = scanner.nextLine();
        System.out.println(control.playAudios(usernick, audioName));
    }

    /**
     * <b>buyAudio</b><br>
     * reads the info and call the controller to an audio.<br>
     */
    public void buyAudio() {
        System.out.print("Enter the song's name : ");
        scanner.nextLine();
        String audioName = scanner.nextLine();
        System.out.print("Enter the user's nick : ");
        String usernick = scanner.nextLine();
        System.out.println(control.buySong(usernick, audioName));
    }

    /**
     * <b>showTotalPlayback</b><br>
     * reads the info and call the controller to show the total playback of all
     * audios according to it's type.<br>
     */
    public void showTotalPlayback() {
        int option;
        do {
            System.out.println("Select the type of audio to show : \n1. Song\n2. Podcast");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 && option > 2) {
                System.out.println("Invalid option");
            }
        } while (option < 1 && option > 2);
        if (option == 1) {
            System.out.println(control.showTotalPlaybackSongs());
        } else {
            System.out.println(control.showTotalPlaybackPodcasts());
        }
    }

    /**
     * <b>showMostHearedGenre</b><br>
     * reads the info and call the controller to show the most heared genre of all
     * the app and for a single user.<br>
     */
    public void showMostHearedGenre() {
        int option;
        do {
            System.out.println("Select the category you need: \n1. All the app\n2. A specific user");
            option = scanner.nextInt();
            if (option < 1 && option > 2) {
                System.out.println("Invalid option");
            }
        } while (option < 1 && option > 2);
        if (option == 1) {
            System.out.println(control.mostListenedGenre());
        } else {
            System.out.print("Enter the user's nick : ");
            scanner.nextLine();
            String usernick = scanner.nextLine();
            System.out.println(control.mostListenedGenre(usernick));
        }

    }

    /**
     * <b>showMostHearedCategory</b><br>
     * reads the info and call the controller to show the most heared category of
     * all the app and for a single user.<br>
     */
    public void showMostHearedCategory() {
        int option;
        do {
            System.out.println("Select the category you need: \n1. All the app\n2. A specific user");
            option = scanner.nextInt();
            if (option < 1 && option > 2) {
                System.out.println("Invalid option");
            }
        } while (option < 1 && option > 2);
        if (option == 1) {
            System.out.println(control.mostListenedCategory());
        } else {
            System.out.print("Enter the user's nick : ");
            scanner.nextLine();
            String usernick = scanner.nextLine();
            System.out.println(control.mostListenedCategory(usernick));
        }
    }

    /**
     * <b>showTop5Producers</b><br>
     * calls the controller to show the top 5 producers of all the app.<br>
     */
    public void showTop5Producers() {
        System.out.println(control.top5());
    }

    /**
     * <b>showTop10Audios</b><br>
     * calls the controller to show the top 10 audios of all the app.<br>
     */
    public void showTop10Audios() {
        System.out.println(control.top10());
    }

    /**
     * <b>showTotalSales</b><br>
     * Reads the info and calls the controller to show the total sales of all the
     * app.<br>
     */
    public void showTotalSales() {
        int option;
        do {
            do {
                System.out.println(
                        "Select the genre to show it´s total sales : \n1.Rock\n2. Pop\n3. Trap\n4. House\n0.Go back to the main menu");
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 0 && option > 4) {
                    System.out.println("Invalid option");
                }
            } while (option < 0 && option > 4);
            switch (option) {
                case 1:
                    System.out.println(control.reportByGenre("ROCK"));
                    break;
                case 2:
                    System.out.println(control.reportByGenre("POP"));
                    break;
                case 3:
                    System.out.println(control.reportByGenre("TRAP"));
                    break;
                case 4:
                    System.out.println(control.reportByGenre("HOUSE"));
                    break;
                default:
                    System.out.println("Returning to the main menu");
                    break;
            }
        } while (option != 0);

    }

    /**
     * <b>showMostSaledSong</b><br>
     * calls the controller to show the most saled song of all the app.<br>
     */
    public void showMostSaledSong() {
        System.out.println(control.mostSoldSong());
    }

}