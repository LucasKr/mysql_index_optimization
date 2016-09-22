package oficiali.app;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import oficiali.dao.TrajectoryDao;
import oficiali.models.FeedDataBase;
import oficiali.utils.ConexaoApi;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        ConexaoApi cn = new ConexaoApi();
        System.out.println("--- Inserting 10k rows");
        FeedDataBase.loadResourceToDataBase(cn, 10000);
        System.out.println("--- Done Inserting 10k rows");
        System.out.println("");
          
        final String log = "116.36117";
        final String lat = "39.97532";
        System.out.println(String.format("Searching for |log: %s | lat: %s | WITHOUT index", log, lat));
        Date startSearching = new Date();
        TrajectoryDao.listTrajectorys(cn, log, lat);
        Date endSearching = new Date();
        System.out.println(String.format("Done, total time %s milliseconds", endSearching.getTime() - startSearching.getTime()));
        System.out.println("");
        System.out.println(String.format("Searching for |log: %s | lat: %s | WITH index", log, lat));
        startSearching = new Date();
        TrajectoryDao.listTrajectorysWithIndex(cn, log, lat);
        endSearching = new Date();
        System.out.println(String.format("Done, total time %s milliseconds", endSearching.getTime() - startSearching.getTime()));
        System.out.println("");
//        TrajectoryDao.cleanTables(cn);
        
        System.out.println("\n");
        
        System.out.println("--- Inserting 100k rows");
        FeedDataBase.loadResourceToDataBase(cn, 100000); 
        System.out.println("--- Done Inserting 100k rows");
        System.out.println("");
        System.out.println(String.format("Searching for |log: %s | lat: %s | WITHOUT index", log, lat));
        startSearching = new Date();
        TrajectoryDao.listTrajectorys(cn, log, lat);
        endSearching = new Date();
        System.out.println(String.format("Done, total time %s milliseconds", endSearching.getTime() - startSearching.getTime()));
        System.out.println("");
        System.out.println(String.format("Searching for |log: %s | lat: %s | WITH index", log, lat));
        startSearching = new Date();
        TrajectoryDao.listTrajectorysWithIndex(cn, log, lat);
        endSearching = new Date();
        System.out.println(String.format("Done, total time %s milliseconds", endSearching.getTime() - startSearching.getTime()));
        System.out.println("");
        System.out.println("\n");
//        TrajectoryDao.cleanTables(cn);
        
        cn.commit();
    }
}
