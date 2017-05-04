/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic.ServiceConsume;

/**
 *
 * @author snrivera
 */
public class Events {

    public static String linkEvent(java.lang.Integer idEvent, java.lang.Integer idHost, java.lang.Integer idGuest) {
        BusinessLogic.ServiceConsume.EventAccess_Service service = new BusinessLogic.ServiceConsume.EventAccess_Service();
        BusinessLogic.ServiceConsume.EventAccess port = service.getEventAccessPort();
        return port.linkEvent(idEvent, idHost, idGuest);
    }

    public static java.util.List<BusinessLogic.ServiceConsume.Event> privateEvents(java.lang.Integer idUsr) {
        BusinessLogic.ServiceConsume.EventAccess_Service service = new BusinessLogic.ServiceConsume.EventAccess_Service();
        BusinessLogic.ServiceConsume.EventAccess port = service.getEventAccessPort();
        return port.privateEvents(idUsr);
    }

    public static java.util.List<BusinessLogic.ServiceConsume.Event> publicEvents() {
        BusinessLogic.ServiceConsume.EventAccess_Service service = new BusinessLogic.ServiceConsume.EventAccess_Service();
        BusinessLogic.ServiceConsume.EventAccess port = service.getEventAccessPort();
        return port.publicEvents();
    }
     
}
