package tht.app.random;

import java.io.Serializable;

public class DanhSach implements Serializable {
    int IdDS;
    String TenRanDom;

    public DanhSach(String tenRanDom) {
        //IdDS = idDS;
        TenRanDom = tenRanDom;
    }

    public DanhSach() {
    }

    public int getIdDS() {
        return IdDS;
    }

    public void setIdDS(int idDS) {
        IdDS = idDS;
    }

    public String getTenRanDom() {
        return TenRanDom;
    }

    public void setTenRanDom(String tenRanDom) {
        TenRanDom = tenRanDom;
    }
}
