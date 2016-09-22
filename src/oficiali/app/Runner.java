package oficiali.app;

import java.sql.SQLException;
import oficiali.models.FeedDataBase;
import oficiali.utils.ConexaoApi;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexaoApi cn = new ConexaoApi();
        FeedDataBase.loadResourceToDataBase(cn);
        cn.commit();
    }
}
