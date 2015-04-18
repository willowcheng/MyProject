package me.willowcheng.myproject;

/**
 * Created by willowcheng on 4/18/2015.
 */
public class ProjectItem {

    private int id;
    private String name;
    private String courseNumber;
    private String instructor;
    private String projectNumber;
    private String description;
    private String due;

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String number) {
        this.courseNumber = number;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

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

    public ProjectItem(String name, String courseNumber, String instructor, String projectNumber, String description, String due) {
        this.name = name;
        this.courseNumber = courseNumber;
        this.instructor = instructor;
        this.projectNumber = projectNumber;
        this.description = description;
        this.due = due;
    }

    public ProjectItem(int id, String name, String courseNumber, String instructor, String projectNumber, String description, String due) {
        this.id = id;
        this.name = name;
        this.courseNumber = courseNumber;
        this.instructor = instructor;
        this.projectNumber = projectNumber;
        this.description = description;
        this.due = due;
    }

}
