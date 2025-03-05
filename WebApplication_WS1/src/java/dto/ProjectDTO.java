/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author baothy2004
 */
public class ProjectDTO {
    private int project_id;
    private String project_name;
    private String description;
    private String status;
    private String estimate_lanch;

    public ProjectDTO() {
    }

    public ProjectDTO(int project_id, String project_name, String description, String status, String estimate_lanch) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.description = description;
        this.status = status;
        this.estimate_lanch = estimate_lanch;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimate_lanch() {
        return estimate_lanch;
    }

    public void setEstimate_lanch(String estimate_lanch) {
        this.estimate_lanch = estimate_lanch;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" + "project_id=" + project_id + ", project_name=" + project_name + ", description=" + description + ", status=" + status + ", estimate_lanch=" + estimate_lanch + '}';
    }
    
    
}
