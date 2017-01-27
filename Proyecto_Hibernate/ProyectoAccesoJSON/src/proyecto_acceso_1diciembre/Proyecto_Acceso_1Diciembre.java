/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_acceso_1diciembre;

import entities.VistaActividadesAlojamiento;
import entities.VistaActividadesAlojamientoId;
import hibernate.HibernateUtil;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vista.swing.comun.VentanaLoginBDD;

/**
 * Tercer Proyecto para Angel (Acceso a Datos). Hibernate.
 * @author Mario Codes Sánchez
 * @since 26/01/2017
 * @version 0.1 Haciendo Limpieza de Codigo.
 */
public class Proyecto_Acceso_1Diciembre {

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        new VentanaLoginBDD();
//        SingletonVentanas.getVentanaPrincipalObtencionSingleton();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        
        VistaActividadesAlojamientoId vaid = new VistaActividadesAlojamientoId();
        vaid.setIdAlojamiento(1);
        vaid.setIdActividad(2);
        vaid.setNombreAlojamiento("SUUU");
        
        VistaActividadesAlojamiento va = new VistaActividadesAlojamiento();
        va.setId(vaid);
        
//        s.save(va);
    }
}