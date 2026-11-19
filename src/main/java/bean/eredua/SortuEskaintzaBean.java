package bean.eredua;

import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import businessLogic.BLFacade;
import exceptions.MustBeLaterThanTodayException;
import exceptions.ParamNullException;
import exceptions.SaleAlreadyExistException;

@Named("sortuEskaintzaBean")
@RequestScoped
public class SortuEskaintzaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tituloa;
    private String deskripzioa;
    private int egoera = 1;
    private float prezioa;
    private Date data;
    private String sellerEmail = "seller3@gmail.com"; 

    public SortuEskaintzaBean() {}

    // Getters y Setters
    public String getTituloa() { return tituloa; }
    public void setTituloa(String tituloa) { this.tituloa = tituloa; }

    public String getDeskripzioa() { return deskripzioa; }
    public void setDeskripzioa(String deskripzioa) { this.deskripzioa = deskripzioa; }

    public int getEgoera() { return egoera; }
    public void setEgoera(int egoera) { this.egoera = egoera; }

    public float getPrezioa() { return prezioa; }
    public void setPrezioa(float prezioa) { this.prezioa = prezioa; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getSellerEmail() { return sellerEmail; }
    public void setSellerEmail(String sellerEmail) { this.sellerEmail = sellerEmail; }

    // oferta egiteko
    public String sortuEskaintza() {
        try {
            BLFacade facade = FacadeBean.getBusinessLogic();
            facade.createSale(tituloa, deskripzioa, egoera, prezioa, data, sellerEmail, null);
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Eskaintza ondo sortu da!", "Oferta ondo egin da."));
        } catch (SaleAlreadyExistException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Eskaintza hau dagoeneko existitzen da."));
        } catch (MustBeLaterThanTodayException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Data gaur baino beranduago izan behar da."));
        } catch (ParamNullException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Eremu guztiak bete behar dira (Faltan parámetros)."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", e.getMessage()));
        }
        return null;
    }
}