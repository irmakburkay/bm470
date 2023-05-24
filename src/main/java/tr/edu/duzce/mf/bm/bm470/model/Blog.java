package tr.edu.duzce.mf.bm.bm470.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "blog", schema = "bm470")
public class Blog implements Serializable {

    //isim aynıysa name ile belirtmene gerek yok

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "baslik", nullable = false)
    private String baslik;
    @Column(name = "icerik", nullable = false)
    private String icerik;
    @Column(name = "tarih", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tarih;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
}
