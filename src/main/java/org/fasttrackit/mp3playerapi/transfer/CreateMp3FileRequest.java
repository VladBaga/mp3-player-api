package org.fasttrackit.mp3playerapi.transfer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateMp3FileRequest {

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
        return "CreateMp3FileRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
