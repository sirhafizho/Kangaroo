package jumpygrof.github;

import java.util.Scanner;

public class JumpyGrof {

    
    static LinkedList<Kangaroo> Kangaroos = new LinkedList<Kangaroo>();// Kangaroos static so other class can use it
    static Map Hafiz = new Map();// Map is static so that other class can access it

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n; // number of points
        int a; // pointID
        int f; // number of food available at the point
        int l; // maxkang of the point
        int m; // number of route connected
        int threshold ;//size of colony
        
        //MENU INPUT POINT
        System.out.println("(THE MAP/POINTS INPUT MENU)");
        // ask the user to input the number of points
        System.out.print("Enter the number of points: ");
        n = s.nextInt();
        Hafiz = new Map(n);// set the number of points, tapi point masih belum ada
//        System.out.println("enter information, kat sini kene baiki cara user input");
        System.out.println("Please enter information about points");
        for (int i = 0; i < n; i++) {
            System.out.println("(Point " + ( i+1 ) + ")");
            a = i+1;
            System.out.print("Enter the number of food available at the point : ");
            f = s.nextInt(); 
            System.out.print("Enter the maximum amount of kangaroo that could fit in point " + (i+1) + ": ");
            l = s.nextInt();
            System.out.print("Enter the number of route that is connected to " + (i+1) + ": " );
            m = s.nextInt();
            Hafiz.points.addNode(new Points(a, f, l, m));
            System.out.println();
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < Hafiz.numberofpoints; i++) {
            if(!(Hafiz.points.atindex(i).getnumroute() == 0)) {     
            System.out.println("Point ID: " + Hafiz.points.atindex(i).getpointID());
            for (int r = 0; r < Hafiz.points.atindex(i).getnumroute(); r++) {
                int tempID;
                System.out.print("Route from "+ Hafiz.points.atindex(i).getpointID() +" to Point: ");
                tempID = s.nextInt();
                for (int t = 0; t < Hafiz.numberofpoints; t++) {
                    if (Hafiz.points.atindex(t).getpointID() == tempID) {

                        System.out.print("with height of: ");
                        int tempheight = s.nextInt();
                        Hafiz.points.atindex(i).addroute(new Route(Hafiz.points.atindex(t), tempheight));// tambah jalan

                    }

                }
            }
            System.out.println();
            }else {
                System.out.println("Point ID: " + Hafiz.points.atindex(i).getpointID() + " has no outward route");
            }
            System.out.println();
        }
        //END MENU INPUT POINT
        //test
        
        //KANGAROO INPUT POINT
        System.out.println("(THE KANGAROO INPUT MENU)");
        System.out.print("Enter the amount of Kangaroo(s) for the whole map : ");
        int tempnumkang = s.nextInt();  
        //i , s and p KANGAROOS INFORMATION
        System.out.println("Adding kangaroos id,gender(M/F),pouchcapacity");
        for (int i = 0; i < tempnumkang; i++) {
            System.out.print("ID: ");
            int tempID = s.nextInt();
            System.out.print("Gender: ");
            s.nextLine();
            char c = s.nextLine().charAt(0);
            char c1 = Character.toUpperCase(c);
            Character tempgender = c1;
            System.out.print("Pouch Capacity: ");
            int temppouch = s.nextInt();
            Kangaroos.addNode(new Kangaroo(tempID, tempgender, temppouch));
            System.out.println();
        }
        System.out.println("Size of a colony(threshold): ");
        threshold = s.nextInt();
        Points.colony = threshold;
        System.out.println();
        System.out.println();
        //
        //END KANGAROO INPUT POINT
        
        
        System.out.println("_____________________________________________________________");
        System.out.println("List of route taken by the Kangaroo(s)");
        Hafiz.tick();
        
        
        //
        int counter = 0;
        for (int i = 0; i < JumpyGrof.Kangaroos.length(); i++) { //kira bilangan kangaroo yang ada dalam colony
            if (JumpyGrof.Kangaroos.atindex(i).isincolony() == false) {
                System.out.println(JumpyGrof.Kangaroos.atindex(i).toString());
                counter++;
            } 
        }
        for (int i = 0; i < JumpyGrof.Hafiz.points.length(); i++) {
            System.out.println("Food at point " + JumpyGrof.Hafiz.points.atindex(i).getpointID() + ": " + JumpyGrof.Hafiz.points.atindex(i).getfood());
        }

        System.out.println("Number of colony formed: " + Points.numberofcolony);
        System.out.println();
        System.out.println();
        //List of Kangaroos that was not able to colonized
        System.out.println("List of Kangaroos that was not able to colonized");
        System.out.println("Number of Remaining Kangaroos: " + counter);
        for(int i = 0;i<JumpyGrof.Kangaroos.length();i++){
            if(JumpyGrof.Kangaroos.atindex(i).isincolony()==false)
            System.out.println(JumpyGrof.Kangaroos.atindex(i).toString());
        }
        System.out.println();
        System.out.println();
        counter = 0;
        //
        
        //
        System.out.println("LIST OF FOOD LEFT (DEBUGGING)");
        for (int i = 0;i<JumpyGrof.Hafiz.points.length();i++){
            System.out.println("Food at point "+JumpyGrof.Hafiz.points.atindex(i).getpointID()+": "+JumpyGrof.Hafiz.points.atindex(i).getfood());
        }
        System.out.println();
        //
        
        //List of Kangaroos that are able to colonized
        System.out.println("List of Kangaroos that are able to colonized");
        for(int i = 0;i<JumpyGrof.Kangaroos.length();i++){
            if(JumpyGrof.Kangaroos.atindex(i).isincolony()==true)
            counter++;   
        }     
        System.out.println("Number of colony formed: "+Points.numberofcolony);
        System.out.println("Number of Kangaroos that are colonized: " + counter);
        for (int i = 0; i < JumpyGrof.Hafiz.points.length(); i++) {
            if (JumpyGrof.Hafiz.points.atindex(i).iscolonized() == true) {
                System.out.println("Colony at point " + JumpyGrof.Hafiz.points.atindex(i).getpointID());
                for (int j = 0; j < JumpyGrof.Kangaroos.length(); j++) {
                    if (JumpyGrof.Kangaroos.atindex(j).getpointID() == JumpyGrof.Hafiz.points.atindex(i).getpointID()) {
                        System.out.println(JumpyGrof.Kangaroos.atindex(j).toString());
                    }
                }
            }
        }
        //
        
        
    }

}
