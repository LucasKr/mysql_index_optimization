package oficiali.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficiali.dao.TrajectoryDao;
import oficiali.models.TrajectoryModel;
import oficiali.utils.ConexaoApi;

public class FeedDataBase {
    private static final File RESOURCES = new File("resources");
    
    public static void loadResourceToDataBase(ConexaoApi cn) throws ClassNotFoundException, SQLException {
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
        System.out.println("Doing... ");
        trajectoryModels.forEach(t -> {
            try {
                TrajectoryDao.insert(t, cn);
            } catch (SQLException ex) {
                Logger.getLogger(FeedDataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        System.out.println("Done! ");
        
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
