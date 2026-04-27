package com.turkcell.library.dto;

public class GetMemberStatusResponse {
    private Integer id;
    private String statusValue;

    public GetMemberStatusResponse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

}
