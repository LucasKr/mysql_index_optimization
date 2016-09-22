package oficiali.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficiali.dao.TrajectoryDao;
import oficiali.utils.ConexaoApi;

public class FeedDataBase {
    private static final File RESOURCES = new File("resources");
    
    public static void loadResourceToDataBase(ConexaoApi cn, int numberOfRows) throws ClassNotFoundException, SQLException {
        ArrayList<File> scriptFiles = new ArrayList<>();
        blowFileStructure(RESOURCES.listFiles(), scriptFiles);  
        final ArrayList<TrajectoryModel> trajectoryModels = new ArrayList<>();
        scriptFiles.forEach(o -> {
            
            try {
                trajectoryModels.addAll(listTrajectoryModels(o));
            } catch (IOException | ParseException ex) {
                Logger.getLogger(FeedDataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }); 
        
        System.out.println("Inserting trajectory and trajectory_w_index tables... ");
        Date startDate = new Date();
        trajectoryModels.stream().limit(numberOfRows).forEach(t -> {
            try {
                TrajectoryDao.insertTrajectory(t, cn);
                TrajectoryDao.insertTrajectoryWithIndex(t, cn);
            } catch (SQLException ex) {
                Logger.getLogger(FeedDataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Date endDate = new Date();
        System.out.println(String.format("Done! rows inserted: %s", trajectoryModels.stream().limit(numberOfRows).count()));
        System.out.println(String.format("Started at %s", startDate));
        System.out.println(String.format("Finished at %s", endDate));
        System.out.println(String.format("Total time: %s milli sec.", (endDate.getTime() - startDate.getTime()) ));
        
    }
    
    private static ArrayList<TrajectoryModel> listTrajectoryModels(File f) throws FileNotFoundException, IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<TrajectoryModel> result = new ArrayList<>();
        String line;
        while( ( line = br.readLine()) != null) {
            result.add(TrajectoryModel.parser(line));
        }
        return result;
    }
    
    private static void blowFileStructure(File[] files, ArrayList<File> scriptFiles) {
        for(File file : files) {
            if(file.isDirectory())
                blowFileStructure(file.listFiles(), scriptFiles);
            else 
                scriptFiles.add(file);
        }
    }
    
}
