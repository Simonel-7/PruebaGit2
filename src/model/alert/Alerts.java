package model.alert;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Identifiable;
import model.habitat.*;

public class Alerts implements Identifiable, Serializable{

    private static final long SerialVersionUID = 10l;
    
    private String id;
    private AlertType alertType;
    private LocalDate alertDate;
    private String relatedEntity;
    private String description;

    private Habitat relatedHabitat;     
    
    public Alerts(AlertType alertType, String relatedEntity, String description) {
        this.alertType = alertType;
        this.relatedEntity = relatedEntity;
        this.description = description;
        this.relatedHabitat = null;
        this.id = generateId();
        this.alertDate = LocalDate.now();
    }


    @Override
    public String generateId() {
        return "ALERT" + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + LocalDateTime.now().getNano();
    }
    @Override
    public String getId() {
        return this.id;
    }

    
}
