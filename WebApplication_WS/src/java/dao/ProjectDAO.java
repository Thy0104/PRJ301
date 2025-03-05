/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author baothy2004
 */
public class ProjectDAO implements IDAO<ProjectDTO, String>{

    @Override
    public boolean create(ProjectDTO entity) {
        return null;
    }
    @Override
    public List<ProjectDTO> readAll() {
        return null; 
    }

    @Override
    public ProjectDTO readById(String id) {
       return null;
    }

    @Override
    public boolean update(ProjectDTO entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<ProjectDTO> search(String searchTerm) {
        return null;
    }

         public List<ProjectDTO> searchByTitle(String searchTerm) {
        String sql = "SELECT * FROM tblBooks WHERE title LIKE ?";
        List<ProjectDTO> list = new ArrayList<ProjectDTO>();
        
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+searchTerm+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ProjectDTO b = new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("estimated_launch")
                
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }
     
    public List<ProjectDTO> searchByName(String searchTerm) {
        String sql = "SELECT * FROM tblStartupProjects WHERE project_name LIKE ?";
        List<ProjectDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProjectDTO project = new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("estimated_launch")
                );
                list.add(project);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public boolean updateStatusToCompleted(Integer id) {
        String sql = "UPDATE tblStartupProjects SET status='Completed' WHERE project_id=?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
