package asw;

import java.util.List;
import java.util.ArrayList;


public class DAO {
   public List<String> getUserNames(){
            
        List<String> names = new ArratList<String>;
        names.add("Pedro");
        names.add("Mestre");
        names.add("Mais um...");
        names.add("e outro");


        return names;
    }
}



// Na pagina ............ DAO dao = new DAO();
// List<String> names = dao.getUserNames();