package org.fasttrackit.mp3playerapi.transfer;

import javax.validation.constraints.NotBlank;

public class UpdateMp3FileRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetMp3FilesRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
