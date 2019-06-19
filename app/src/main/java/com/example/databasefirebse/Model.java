package com.example.databasefirebse;

public class Model {
    String roomDetail,complaint;

    public Model() {
    }

    public Model(String roomDetail, String complaint) {
        this.roomDetail = roomDetail;
        this.complaint = complaint;
    }

    public String getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(String roomDetail) {
        this.roomDetail = roomDetail;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
