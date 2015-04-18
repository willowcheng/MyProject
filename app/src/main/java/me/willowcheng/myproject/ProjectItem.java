package me.willowcheng.myproject;

/**
 * Created by willowcheng on 4/18/2015.
 */
public class ProjectItem {
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

    private String name;
    private String description;
    private String due;

    public ProjectItem (String name, String description, String due) {
        this.name = name;
        this.description = description;
        this.due = due;
    }


}
