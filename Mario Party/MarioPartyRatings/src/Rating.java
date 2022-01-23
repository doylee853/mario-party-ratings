import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Rating extends Thread{


    static double Probability(int rating1, int rating2){
        return 1.0 * 1.0 / (1 + 1.0 * Math.pow(10, 1.0 * (rating2 - rating1) / 400));
    }

    public static List<Integer> duelTime(Entry w, Entry l, boolean tie){
        int k = 24;

        double pw = Probability((int)w.getValue(), (int)l.getValue());
        double pl = Probability((int)l.getValue(), (int)w.getValue());

        int wr = (int)w.getValue();
        int lr = (int)l.getValue();

        if(tie == false){
            wr = (int)((wr + k*(1-pw)));
            lr = (int)((lr + k*(0-pl)));
        }
        else{
            wr = (int)((wr + k*(.5-pw)));
            lr = (int)((lr + k*(.5-pl)));
        }

        List<Integer> sugma = new ArrayList<Integer>();
        sugma.add(wr);
        sugma.add(lr);
        return sugma;
    }

    public static List<Integer> soloGame(Entry w, Entry l1, Entry l2, Entry l3, boolean t){
        int k = 50;

        //winners probability of beating all the losers
        double pw1 = Probability((int)w.getValue(), (int)l1.getValue());
        double pw2 = Probability((int)w.getValue(), (int)l2.getValue());
        double pw3 = Probability((int)w.getValue(), (int)l3.getValue());

        //loser 1s probability of beating all the losers
        double pl11 = Probability((int)l1.getValue(), (int)w.getValue());
        double pl12 = Probability((int)l1.getValue(), (int)l2.getValue());
        double pl13 = Probability((int)l1.getValue(), (int)l3.getValue());

        //loser 2s probability of beating all the losers
        double pl21 = Probability((int)l2.getValue(), (int)w.getValue());
        double pl22 = Probability((int)l2.getValue(), (int)l1.getValue());
        double pl23 = Probability((int)l2.getValue(), (int)l3.getValue());

        //loser 3s probability of beating all the losers
        double pl31 = Probability((int)l3.getValue(), (int)w.getValue());
        double pl32 = Probability((int)l3.getValue(), (int)l1.getValue());
        double pl33 = Probability((int)l3.getValue(), (int)l2.getValue());

        int wr = (int)w.getValue();
        int l1r = (int)l1.getValue();
        int l2r = (int)l2.getValue();
        int l3r = (int)l3.getValue();

        if(t==true){
            wr = (int)((wr + k*(1-pw2)) + (wr + k*(1-pw3)))/2;
            l1r = (int)((l1r + k*(1-pl12)) + (l1r + k*(1-pl13)))/2;
            l2r = (int)((l2r + k*(0-pl21)) + (l2r + k*(0-pl22)))/2;
            l3r = (int)((l3r + k*(0-pl31)) + (l3r + k*(0-pl32)))/2;
        }
        else{
            wr = (int)((wr + k*(1-pw1)) + (wr + k*(1-pw2)) + (wr + k*(1-pw3)))/3;
            l1r = (int)((l1r + k*(0-pl11)) + (l1r + k*(.5-pl12)) + (l1r + k*(.5-pl13)))/3;
            l2r = (int)((l2r + k*(0-pl21)) + (l2r + k*(.5-pl22)) + (l2r + k*(.5-pl23)))/3;
            l3r = (int)((l3r + k*(0-pl31)) + (l3r + k*(.5-pl32)) + (l3r + k*(.5-pl33)))/3;
        }

        /*System.out.println(wr);
        System.out.println(l1r);
        System.out.println(l2r);
        System.out.println(l3r);
        */


        List<Integer> sugma = new ArrayList<Integer>();
        sugma.add(wr);
        sugma.add(l1r);
        sugma.add(l2r);
        sugma.add(l3r);
        return sugma;

        //w.setValue(wr);
        //l1.setValue(l1r);
        //l2.setValue(l2r);
        //l3.setValue(l3r);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Entry ethan = Map.entry("Ethan", 1156);
        Entry simon = Map.entry("Simon", 1213);
        Entry s2e = Map.entry("S2E", 1250);
        Entry swoggles = Map.entry("Swoggles", 1090);
        Entry ezra = Map.entry("Ezra", 993);
        Entry erg = Map.entry("Erg", 1045);
        Entry darin = Map.entry("Darin", 1130);
        Entry al = Map.entry("Al", 1032);
        Entry waluigi = Map.entry("Waluigi", 964);
        Entry justin = Map.entry("Justin", 1099);
        Entry aj = Map.entry("AJ", 1102);
        Entry amanda = Map.entry("Amanda", 938);
        Entry franz = Map.entry("Franz", 1064);
        Entry ian = Map.entry("Ian", 1052);
        Entry ryder = Map.entry("Ryder", 974);
        Entry mike = Map.entry("Mike", 985);
        Entry rohan = Map.entry("Rohan", 996);

        List<Entry> players = new ArrayList<Entry>();
        players.add(ethan);
        players.add(simon);
        players.add(s2e);
        players.add(swoggles);
        players.add(ezra);
        players.add(erg);
        players.add(darin);
        players.add(al);
        players.add(waluigi);
        players.add(justin);
        players.add(aj);
        players.add(amanda);
        players.add(franz);
        players.add(ian);
        players.add(ryder);
        players.add(mike);
        players.add(rohan);

        while(true){

        Scanner obj = new Scanner(System.in);
        System.out.println("Press Enter to add a new game, type rankings to see everyones current rating");
        String brain = obj.nextLine();
        if(brain.equals("rankings")){
            for(int i = 0; i < players.size(); i++){
                System.out.println(players.get(i).getKey() + ": " + players.get(i).getValue());
            }
        }
        String w = "";
        String l1 = "";
        String l2 = "";
        String l3 = "";
        Boolean t = false;

        System.out.println("Teams? If so, type y then hit enter");
        String teams = obj.nextLine();
        if(teams.toString().equals("y")){
            System.out.println("Enter the name of winner #1");
            w = obj.nextLine();
            System.out.println("Enter the name of winner #2");
            l1 = obj.nextLine();
            System.out.println("Enter the name of loser #1");
            l2 = obj.nextLine();
            System.out.println("Enter the name of loser #2");
            l3 = obj.nextLine();
            t = true;
        }
        else{
            System.out.println("Enter the name of the winner");
            w = obj.nextLine();
            System.out.println("Enter the name of loser #1 (order doesn't matter)");
            l1 = obj.nextLine();
            System.out.println("Enter the name of loser #2");
            l2 = obj.nextLine();
            System.out.println("Enter the name of loser #3");
            l3 = obj.nextLine();
        }


        Entry winner = Map.entry("Test0", 500);
        Entry loser1 = Map.entry("Test1", 500);
        Entry loser2 = Map.entry("Test2", 500);
        Entry loser3 = Map.entry("Test3", 500);

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getKey().toString().equals(w.toString())){
                winner = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                //winner.setValue(players.get(i).getValue());
                //winner = players.get(i);
            }
            if(players.get(i).getKey().toString().equals(l1.toString())){
                loser1 = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                //loser1 = players.get(i);
            }
            if(players.get(i).getKey().toString().equals(l2.toString())){
                loser2 = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                //loser2 = players.get(i);
            }
            if(players.get(i).getKey().toString().equals(l3.toString())){
                loser3 = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                //var ind = players.indexOf(loser3);
                //players.set(ind, Map.entry("New guy in town", 900));
                //loser3 = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                //players.get(i).setValue(501);
                //loser3 = players.get(i);
            }
        }

        List<Integer> endplayers = soloGame(winner, loser1, loser2, loser3, t);
        System.out.println();
        System.out.println("New Ratings:");
        

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getKey().toString().equals(w.toString())){
                var ind = players.indexOf(winner);
                players.set(ind, Map.entry(w.toString(), endplayers.get(0)));
                //System.out.println(winner.getKey() + ": " + endplayers.get(0));
            }
            if(players.get(i).getKey().toString().equals(l1.toString())){
                var ind = players.indexOf(loser1);
                players.set(ind, Map.entry(l1.toString(), endplayers.get(1)));
                //System.out.println(loser1.getKey() + ": " + endplayers.get(1));
            }
            if(players.get(i).getKey().toString().equals(l2.toString())){
                var ind = players.indexOf(loser2);
                players.set(ind, Map.entry(l2.toString(), endplayers.get(2)));
                //System.out.println(loser2.getKey() + ": " + endplayers.get(2));
            }
            if(players.get(i).getKey().toString().equals(l3.toString())){
                var ind = players.indexOf(loser3);
                players.set(ind, Map.entry(l3.toString(), endplayers.get(3)));
                //System.out.println(loser3.getKey() + ": " + endplayers.get(3));
            }
        }
        System.out.println(winner.getKey() + ": " + endplayers.get(0));
        System.out.println(loser1.getKey() + ": " + endplayers.get(1));
        System.out.println(loser2.getKey() + ": " + endplayers.get(2));
        System.out.println(loser3.getKey() + ": " + endplayers.get(3));
        System.out.println();

        System.out.println("Who won the minigame star?");
        String mg = obj.nextLine();

        Entry mgWinner = Map.entry("Test0", 500);

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getKey().toString().equals(mg.toString())){
                //mgWinner = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                //var ind = players.indexOf(mgWinner);
                players.set(i, Map.entry(mg.toString(), (int)players.get(i).getValue() + 15));
                int newValue = (int)(players.get(i).getValue()) + 15;
                System.out.println("New rating for " + mg.toString() + ": " + (int)(players.get(i).getValue()));
            }
        }
        while(true){
            System.out.println("Were there any duels? If yes, type y then hit enter");
            String duels = obj.nextLine();
            boolean tie = false;
            String p1 = "";
            String p2 = "";
            if(duels.toString().equals("y")){
                System.out.println("Was it a tie? If yes, type t then hit enter");
                String tg = obj.nextLine();
                if(tg.toString().equals("t")){
                    System.out.println("Who was the first player?");
                    p1 = obj.nextLine();
                    System.out.println("Who was the second player?");
                    p2 = obj.nextLine();
                    tie = true;
                }
                else{
                    System.out.println("Who was the winner?");
                    p1 = obj.nextLine();
                    System.out.println("Who was the loser?");
                    p2 = obj.nextLine();
                }

                Entry duelWinner = Map.entry("Test0", 500);
                Entry duelLoser = Map.entry("Test1", 500);

                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getKey().toString().equals(p1.toString())){
                        duelWinner = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                    }
                    if(players.get(i).getKey().toString().equals(p2.toString())){
                        duelLoser = Map.entry(players.get(i).getKey(), players.get(i).getValue());
                    }
                }
                List<Integer> duelers = duelTime(duelWinner, duelLoser, tie);
                System.out.println();
                System.out.println("New Ratings:");
        

                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getKey().toString().equals(p1.toString())){
                        var ind = players.indexOf(duelWinner);
                        players.set(ind, Map.entry(p1.toString(), duelers.get(0)));
                        System.out.println("New rating for " + p1.toString() + ": " + (int)(players.get(i).getValue()));
                    }
                    if(players.get(i).getKey().toString().equals(p2.toString())){
                        var ind = players.indexOf(duelLoser);
                        players.set(ind, Map.entry(p2.toString(), duelers.get(1)));
                        System.out.println("New rating for " + p2.toString() + ": " + (int)(players.get(i).getValue()));
                    }
                }
                System.out.println();

            }
            else{
                break;
            }
        }

        //System.out.println("New ratings: " + winner.getKey() + ": " + winner.getValue());
        //System.out.println(loser1.getKey() + ": " + loser1.getValue());
        //System.out.println(loser2.getKey() + ": " + loser2.getValue());
        //System.out.println(loser3.getKey() + ": " + loser3.getValue());

        }
    }
}
