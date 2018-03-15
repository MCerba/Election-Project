package election.data;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;

import java.util.List;

/**
 * Class that creates ser files and checks if SequentialTextFileList
 * ObjectSeriliazedList work properly
 *
 * @author Saad Khan
 */
public class SerializedFileLoaderApp {
    public static void main(String[] args) {
        SequentialTextFileList stfl = new SequentialTextFileList
                ("../ElectionSys6/datafiles/database/voters.txt",
                        "../ElectionSys6/datafiles/database/elections.txt",
                        "../ElectionSys6/datafiles/database/tally.txt");
        ObjectSerializedList osl = new ObjectSerializedList("../ElectionSys6/datafiles/database/voters.ser",
                "../ElectionSys6/datafiles/database/elections.ser");
        List<Voter> voters = stfl.getVoterDatabase();
        List<Election> elections = stfl.getElectionDatabase();
        try {
            osl.saveVoterDatabase(voters);
            osl.saveElectionDatabase(elections);
        } catch (Exception e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        System.out.println("SerializedFileLoader---->Works");
    }
}
