package org.fasttrackit.mp3playerapi.transfer;

public class UploadMp3FileResponse {

    private String name;

    public UploadMp3FileResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
