package info.pauek.songlist;

class Song {
    private String title;
    private String band;
    private String year;

    /*Constructor*/
    public Song(String title) {
        this.title = title;
    }

    public Song(String title, String band, String year) {
        this.title = title;
        this.band = band;
        this.year = year;
    }

    /*Getters/Setters*/
    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
