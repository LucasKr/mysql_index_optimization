package oficiali.dao;

import java.sql.SQLException;
import oficiali.models.TrajectoryModel;
import oficiali.utils.ConexaoApi;

public class TrajectoryDao {
    
    public static void insert(TrajectoryModel t, ConexaoApi cn) throws SQLException {
        cn.setQuery("insert into trajectory (when_ocurrs, taxi_id, longitude, latitude) values (?, ?, ?, ?) ");
        cn.setDate(1, t.getWhen());
        cn.setInt(2, t.getIdveicle());
        cn.setString(3, t.getLongitude());
        cn.setString(4, t.getLatitude());
        cn.executeUpdate();
    }
    
}
