
package com.programacion.servicios;

import com.programacion.db.Singer;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioSingerImpl implements ServicioSinger {

    private PreparedStatement pstmt = null;
    private Connection con = null;
    @Inject
    private DataSource dataSource;

    //Listar
    @Override
    public List<Singer> findAll() {

        //Connection con = null;
        List<Singer> ret = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement("select * from singer order by first_name");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Singer obj = new Singer();
                obj.setId(rs.getInt("id"));
                obj.setFirstName(rs.getString("first_name"));
                obj.setLastName(rs.getString("last_name"));
                obj.setBirthDate(rs.getDate("birth_date"));
                ret.add(obj);
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return ret;
    }

    //bucar por id
    @Override
    public Singer findById(Integer id) {
        Singer s = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement("select * from singer where id=?");
            //le pasamos el id al primer  interrogante de la consulta
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                s = new Singer();
                s.setId(id);
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setBirthDate(rs.getDate("birth_date"));
            }
            rs.close();
            pstmt.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return s;
    }

    //Crear cantante
    @Override
    public void create(Singer s) {

        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO Singer(id, first_name, last_name, birth_date) VALUES(?,?,?,?)");
            //le pasamos el id al primer  interrogante de la consulta
            pstmt.setInt(1, s.getId());
            //el nombre y apellido q es un string
            pstmt.setString(2, s.getFirstName());
            pstmt.setString(3, s.getLastName());

            //transformar de util.Date a sql.Date
            long timeInMilliSeconds = s.getBirthDate().getTime();
            java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
            //la fecha es el cuarto interrogante
            pstmt.setDate(4, date1);
            //PreparedStatement INSERT UPDATE DELETE
            pstmt.executeUpdate();
            pstmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //actualizar cantante
    @Override
    public void update(Singer s) {
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement("UPDATE Singer SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?;");
            pstmt.setString(1, s.getFirstName());
            pstmt.setString(2, s.getLastName());
            //transformar de util.Date a sql.Date
            long timeInMilliSeconds = s.getBirthDate().getTime();
            java.sql.Date date2 = new java.sql.Date(timeInMilliSeconds);
            //la fecha es el cuarto interrogante
            pstmt.setDate(3, date2);
            pstmt.setInt(4, s.getId());
            pstmt.executeUpdate();
            pstmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //borrar cantante
    @Override
    public void delete(Integer id) {

        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement("DELETE FROM Singer WHERE id=?;");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}