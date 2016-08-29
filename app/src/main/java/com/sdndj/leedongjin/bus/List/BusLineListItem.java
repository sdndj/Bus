package com.sdndj.leedongjin.bus.List;

public class BusLineListItem {

    String busNm;
    String busId;


    public BusLineListItem(String busNm, String busId) {
        // TODO Auto-generated constructor stub
        this.busNm= busNm;
        this.busId=busId;
    }

    public void setBusNm(String busNm) {
        this.busNm = busNm;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusNm() {
        return busNm;
    }

    public String getBusId() {
        return busId;
    }

}
