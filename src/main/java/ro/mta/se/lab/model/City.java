package ro.mta.se.lab.model;


/**
 * Stocheaza datele oraselor din fisierul initial.
 * @author Marginean Florin
 */

public class City {

    /**
     * @param id stocheaza Id-ul unic global orasului.
     */
    private String id;
    /**
     * @param name stocheaza numele orasului.
     */
    private String name;

    /**
     * Setters
     */
    public void setId(String id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getters
     */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Constructor
     * @param id
     * @param name
     */
    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
