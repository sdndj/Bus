package com.sdndj.leedongjin.bus.List;

/**
 * Created by LeeDongJin on 2016-08-23.
 */
public class StationNameItem {
    String stationNm;
    String station;
    String seq;


    public StationNameItem(String stationNm, String station, String seq) {
        // TODO Auto-generated constructor stub
        this.stationNm = stationNm;
        this.station = station;
        this.seq = seq;
    }

    public void setstationNm(String stationNm) {
        this.stationNm = stationNm;
    }

    public void setStation(String station){
        this.station = station;
    }

    public void setSeq(String seq){
        this.seq = seq;
    }



    public String getstationNm() {
        return stationNm;
    }

    public String getStation(){
        return station;
    }

    public String getSeq(){
        return seq;
    }


}
