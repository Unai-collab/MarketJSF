package bean.eredua;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class FacadeBean {
    private static BLFacade facadeInterface;

    private FacadeBean() {}

    public static BLFacade getBusinessLogic() {
        if (facadeInterface == null) {
            try {
                facadeInterface = new BLFacadeImplementation(new DataAccess());
                System.out.println("--> FacadeBean: Negozioaren logika ondo sortu da.");
            } catch (Exception e) {
                System.out.println("--> FacadeBean ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return facadeInterface;
    }//prueba
}