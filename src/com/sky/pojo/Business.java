package com.sky.pojo;

public class Business {
    private Integer bid;
    private String btitle;
    private String binfo;
    private Integer bprice;
    private Integer bcod;

    @Override
    public String toString() {
        return "Business{" +
                "bid=" + bid +
                ", btitle='" + btitle + '\'' +
                ", binfo='" + binfo + '\'' +
                ", bprice=" + bprice +
                ", bcod=" + bcod +
                '}';
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBinfo() {
        return binfo;
    }

    public void setBinfo(String binfo) {
        this.binfo = binfo;
    }

    public Integer getBprice() {
        return bprice;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public Integer getBcod() {
        return bcod;
    }

    public void setBcod(int bcod) {
        this.bcod = bcod;
    }

    public Business(Integer bid, String btitle, String binfo, Integer bprice, Integer bcod) {
        this.bid = bid;
        this.btitle = btitle;
        this.binfo = binfo;
        this.bprice = bprice;
        this.bcod = bcod;
    }

    public Business() {
    }
}
