package com.programacion.servicios;

import com.programacion.db.Album;

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
public class ServicioAlbumImpl implements ServicioAlbum {

    private Connection con = null;
    @Inject
    private DataSource dataSource;


    @Override
    public List<Album> albumfindAll() {
        List<Album> ret = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            PreparedStatement pstml = con.prepareStatement("select * from album order by title");

            ResultSet rs = pstml.executeQuery();

            while(rs.next()){
                Album obj = new Album();
                obj.setId(rs.getInt("id"));
                obj.setSinger_id(rs.getInt("singer_id"));
                obj.setTitle(rs.getString("title"));
                obj.setRelease_date(rs.getDate("release_date"));

                ret.add(obj);
            }

            rs.close();
            pstml.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    @Override
    public Album albumfindById(Integer id) {
        Album obj = new Album();

        try{
            con = dataSource.getConnection();

            PreparedStatement pstml = con.prepareStatement("select * from album where id = ?");
            pstml.setInt(1, id);
            ResultSet rs = pstml.executeQuery();

            while(rs.next()){

                obj.setId(id);
                obj.setSinger_id(rs.getInt("singer_id"));
                obj.setTitle(rs.getString("title"));
                obj.setRelease_date(rs.getDate("release_date"));

            }

            rs.close();
            pstml.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    @Override
    public void createAlbum(Album album) {
        try{
            con = dataSource.getConnection();

            PreparedStatement pstml = con.prepareStatement("INSERT INTO  Album(id, singer_id, title, release_date) values(?,?,?,?)");

              List<Album> ret = new ArrayList<>();
              ret = albumfindAll();
              int i =  ret.size()+1;

            pstml.setInt(1, i);
            pstml.setInt(2, album.getSinger_id());
            pstml.setString(3,album.getTitle());
            java.sql.Date release_dated = new java.sql.Date(album.getRelease_date().getTime());
            pstml.setDate(4,release_dated);
            pstml.executeUpdate();
            pstml.close();
            //return true;

        }catch (Exception ex){
            ex.printStackTrace();
            //return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateAlbum(Integer id, Album album) {
        Integer bandera = id;
        try{
            con = dataSource.getConnection();

            PreparedStatement pstml = con.prepareStatement("UPDATE album\n" +
                    "\tSET singer_id=?, title=?, release_date=?\n" +
                    "\tWHERE id = ?");

            pstml.setInt(1, album.getSinger_id());
            pstml.setString(2,album.getTitle());
            java.sql.Date release_date = new java.sql.Date(album.getRelease_date().getTime());
            pstml.setDate(3,release_date);
            pstml.setInt(4, album.getId());
            pstml.executeUpdate();
            pstml.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAlbum(Integer id) {
        try{
            con = dataSource.getConnection();

            PreparedStatement pstml = con.prepareStatement("delete from album where id = ?");
            pstml.setInt(1, id);
            pstml.executeUpdate();
            pstml.close();
          //  return true;


        }catch (Exception ex){
            ex.printStackTrace();
            //return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
