package com.firstapp.arthub.models;

public class artoftheweekModel {
    String Drawing,ArtistName;

    public artoftheweekModel() {
    }

    public artoftheweekModel(String drawing, String artistName) {
        Drawing = drawing;
        ArtistName = artistName;
    }

    public String getDrawing() {
        return Drawing;
    }

    public void setDrawing(String drawing) {
        Drawing = drawing;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }
}
