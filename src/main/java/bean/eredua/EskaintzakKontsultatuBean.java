package bean.eredua;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import businessLogic.BLFacade;
import domain.Sale;

@Named("eskaintzakKontsultatuBean")
@SessionScoped
public class EskaintzakKontsultatuBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String testua = "";                 // bilatzeko tetua
    private List<Sale> eskaintzak;              // lista produktu guztiak
    private Sale aukeratutakoEskaintza;        // Aukera tu den eskaintza

    public EskaintzakKontsultatuBean() {}

    // Getters y Setters
    public String getTestua() {
        return testua;
    }
    public void setTestua(String testua) {
        this.testua = testua;
    }

    public List<Sale> getEskaintzak() {
        if (eskaintzak == null) {
            bilatu(); 
        }
        return eskaintzak;
    }
    public void setEskaintzak(List<Sale> eskaintzak) {
        this.eskaintzak = eskaintzak;
    }

    public Sale getAukeratutakoEskaintza() {
        return aukeratutakoEskaintza;
    }
    public void setAukeratutakoEskaintza(Sale aukeratutakoEskaintza) {
        this.aukeratutakoEskaintza = aukeratutakoEskaintza;
    }

    // Método de acción para buscar ofertas
    public String bilatu() {
        BLFacade facade = FacadeBean.getBusinessLogic();
        eskaintzak = facade.getSales(testua != null ? testua : "");
        aukeratutakoEskaintza = null; 
        return null;
    }
}