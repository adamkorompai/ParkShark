package com.switchfully.parkshark.domain.dtos;

import com.switchfully.parkshark.domain.Division;

public class DivisionDto {
    private Long id;
    private String name;
    private String originalName;
    private String director;
    private Long parentDivisionId;

    public DivisionDto() {}

    public DivisionDto(Long id, String name, String originalName, String director, Long parentDivisionId) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.parentDivisionId = parentDivisionId;
    }

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
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
