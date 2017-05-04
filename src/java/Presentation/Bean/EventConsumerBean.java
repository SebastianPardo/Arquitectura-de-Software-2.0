/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.ServiceConsume.Event;
import BussinessLogic.ServiceConsume.Events;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author snrivera
 */
@ManagedBean
@SessionScoped
public class EventConsumerBean {
    
    public List<Event> getPublicEvents(){
        return Events.publicEvents();
    }
    
    public List<Event> getPrivateEvents(Integer usrID){
        return Events.privateEvents(usrID);
    }
    
    public String linkEvent(Integer eventID, Integer usrHost, Integer usrGuest){
        return Events.linkEvent(eventID, usrHost, usrGuest);
    }
    
}
