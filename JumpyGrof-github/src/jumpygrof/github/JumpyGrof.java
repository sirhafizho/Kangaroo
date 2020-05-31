package jumpygrof.github;

import java.util.Scanner;

public class JumpyGrof {

    /**
     * @param args the command line arguments
     */
    static LinkedList<Kangaroo> Kangaroos = new LinkedList<Kangaroo>();// Kangaroos static so other class can use it
    static Map Hafiz = new Map();// Map is static so that other class can access it

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n; // number of points
        int a; // pointID
        int f; // number of food available at the point
        int l; // maxkang of the point
        int m; // number of route connected

        // ask the user to input the number of points
        System.out.println("enter number of points: ");
        n = s.nextInt();// sini patutnya scanner(user input)
        Hafiz = new Map(n);// set the number of points, tapi point masih belum ada
        System.out.println("enter information, kat sini kene baiki cara user input");
        System.out.println("Please enter information about points");
        for (int i = 0; i < n; i++) {
            System.out.println("Point " + ( i+1 )+ ": ");
            a = s.nextInt();
            f = s.nextInt();
            l = s.nextInt();
            m = s.nextInt();
            Hafiz.points.addNode(new Points(a, f, l, m));

        }
        System.out.println();
        for (int i = 0; i < Hafiz.numberofpoints; i++) {
            System.out.println("Point ID: " + Hafiz.points.atindex(i).getpointID());
            for (int r = 0; r < Hafiz.points.atindex(i).getnumroute(); r++) {
                int tempID;
                System.out.println("Route to Point:[pointID] ");
                tempID = s.nextInt();
                for (int t = 0; t < Hafiz.numberofpoints; t++) {
                    if (Hafiz.points.atindex(t).getpointID() == tempID) {

                        System.out.println("with height of: ");
                        int tempheight = s.nextInt();
                        Hafiz.points.atindex(i).addroute(new Route(Hafiz.points.atindex(t), tempheight));// tambah jalan

                    }

                }

            }
        }
        int tempnumkang = s.nextInt();
        System.out.println("Adding kangaroos id,gender(M/F),pouchcapacity");
        for (int i = 0; i < tempnumkang; i++) {
            System.out.println("ID: ");
            int tempID = s.nextInt();
            System.out.println("Gender: ");
            s.nextLine();
            char c = s.nextLine().charAt(0);
            Character tempgender = c;
            System.out.println("Pouch Capacity");
            int temppouch = s.nextInt();
            Kangaroos.addNode(new Kangaroo(tempID, tempgender, temppouch));

        }
        Hafiz.tick();
        for(int i = 0;i<JumpyGrof.Kangaroos.length();i++){
            int tempID = JumpyGrof.Kangaroos.atindex(i).getpointID();
            Character tempgender = JumpyGrof.Kangaroos.atindex(i).getGender();
            if(JumpyGrof.Kangaroos.atindex(i).isincolony()==false)
            System.out.println("Kangaroo "+tempID+" "+tempgender+" "+JumpyGrof.Kangaroos.atindex(i).getfood());
            
        }
        for (int i = 0;i<JumpyGrof.Hafiz.points.length();i++){
            System.out.println("Food at point "+JumpyGrof.Hafiz.points.atindex(i).getpointID()+": "+JumpyGrof.Hafiz.points.atindex(i).getfood());
            
        }
        System.out.println("Number of colony formed: "+Points.numberofcolony);
    }

}