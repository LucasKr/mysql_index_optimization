package oficiali.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import oficiali.models.TrajectoryModel;
import oficiali.utils.ConexaoApi;

public class TrajectoryDao {
    
    private static TrajectoryModel makeModel(ConexaoApi cn) throws SQLException {
        return new TrajectoryModel(cn.getInt("id"), cn.getInt("taxi_id"), cn.getDate("when_ocurrs"), cn.getString("latitude"), cn.getString("longitude"));
    }
    
    public static void insertTrajectory(TrajectoryModel t, ConexaoApi cn) throws SQLException {
        cn.setQuery("insert into trajectory (when_ocurrs, taxi_id, longitude, latitude) values (?, ?, ?, ?) ");
        cn.setDate(1, t.getWhen());
        cn.setInt(2, t.getIdveicle());
        cn.setString(3, t.getLongitude());
        cn.setString(4, t.getLatitude());
        cn.executeUpdate();
    }
    
    public static void insertTrajectoryWithIndex(TrajectoryModel t, ConexaoApi cn) throws SQLException {
        cn.setQuery("insert into trajectory_w_index (when_ocurrs, taxi_id, longitude, latitude) values (?, ?, ?, ?) ");
        cn.setDate(1, t.getWhen());
        cn.setInt(2, t.getIdveicle());
        cn.setString(3, t.getLongitude());
        cn.setString(4, t.getLatitude());
        cn.executeUpdate();
    }
    
    public static ArrayList<TrajectoryModel> listTrajectorys(ConexaoApi cn, String longitude, String latitude) throws SQLException, ParseException {
        ArrayList<TrajectoryModel> r = new ArrayList<>();
        String sql = "select id, taxi_id, when_ocurrs, taxi_id, longitude, latitude "
            + "from trajectory "
            + "where longitude = ? "
            + "  and latitude = ? ";
        cn.setQuery(sql);
        cn.setString(1, longitude);
        cn.setString(2, latitude);
        cn.executeQuery();
        while(cn.next()) {
            r.add(makeModel(cn));
        }
        System.out.println("Records Found: " + r.stream().count());
        return r;
    } 
    
    public static ArrayList<TrajectoryModel> listTrajectorysWithIndex(ConexaoApi cn, String longitude, String latitude) throws SQLException, ParseException {
        ArrayList<TrajectoryModel> r = new ArrayList<>();
        String sql = "select id, taxi_id, when_ocurrs, taxi_id, longitude, latitude "
            + "from trajectory_w_index "
            + "where longitude = ? "
            + "  and latitude = ? ";
        cn.setQuery(sql);
        cn.setString(1, longitude);
        cn.setString(2, latitude);
        cn.executeQuery();
        while(cn.next()) {
            r.add(makeModel(cn));
        }
        System.out.println("Records Found: " + r.stream().count());
        return r;
    } 
    
    public static void cleanTables(ConexaoApi cn) throws SQLException {
        cn.setQuery("delete from trajectory");
        cn.executeUpdate();
        cn.setQuery("delete from trajectory_w_index");
        cn.executeUpdate();
        cn.commit();
    }
    
}
