package me.willowcheng.myproject;

/**
 * Created by willowcheng on 4/18/2015.
 */
public class ProjectItem {

    private int id;
    private String name;
    private String description;
    private String due;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectItem (int id, String name, String description, String due) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.due = due;
    }


}
