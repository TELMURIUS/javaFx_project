package insar_timur.javafx_project;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String album;
    private int releaseYear;
    private String genre;
    private String pic;
    private String file_path;

    public Song() {
    }

    public Song(int id, String title, String artist, String album, int releaseYear, String genre, String pic, String file_path) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.pic = pic;
        this.file_path = file_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

}
