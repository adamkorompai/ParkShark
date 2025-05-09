package com.switchfully.parkshark.domain.dtos;

public class CreateDivisionDto {
    private String name;
    private String originalName;
    private String director;
    private Long parentDivisionId;

    public CreateDivisionDto() {}

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDirector() {
        return director;
    }

    public Long getParentDivisionId() {
        return parentDivisionId;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setParentDivisionId(Long parentDivisionId) {
        this.parentDivisionId = parentDivisionId;
    }
}
