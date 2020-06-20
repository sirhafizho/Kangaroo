package jumpygrof.github;

import java.util.InputMismatchException;
import java.util.Random;

/**
 *
 * @author hello
 */
public class Kangaroo implements Comparable<Kangaroo> {

    private Character gender;
    private int pouchcapacity;
    private int food = 0;//food in pouch
    private int pointID;

    public Kangaroo() {
        this.gender = null;
    }

    public Kangaroo(int pointID, Character gender, int pouchcapacity) {
        this.pointID = pointID;
        this.pouchcapacity = pouchcapacity;
        this.gender = gender;

    }

    public int getpointID() {
        return pointID;
    }

    public void setpointID(int newpointID) {
        this.pointID = newpointID;
    }

    public Points getPoint() {//return Point yang kangaroo duduk
        return JumpyGrof.Hafiz.getPoint(this.pointID);
    }

    public int getfood() {
        return food;
    }

    public void minusfood(int food) {
        this.food -= food;
    }

    public void addFood(int food) {
        if (this.food + food > pouchcapacity) {
            getPoint().addfood(food - pouchcapacity);
        } else {
            this.food += food;
        }
    }

    public Character getGender() {
        return gender;
    }

    public boolean isincolony() {//to know if getPoint() is a colony or not
        return getPoint().iscolonized();
    }

    @Override
    public String toString() {
        return "Kangaroo at point: " + pointID + " Gender: " + gender + " Food: " + food;
    }

    @Override
    public int compareTo(Kangaroo o) {
        if (this.getGender().equals(o.getGender())) {
            return 0;// true
        } else {
            return -1;// false
        }
    }

    public void collectfood() {
        try{
        
    
        if ((this.food != this.pouchcapacity) && (getPoint().getfood() != 0)) {
            int tempfood = this.pouchcapacity - this.food;// amount of food needed to fill the pouch
            if (getPoint().getfood() - tempfood < 0) {//check kalau makanan at point sikit
                this.food += getPoint().getfood();
            } else {
                getPoint().minusfood(tempfood);
                this.food += tempfood;
            }
        }
    }catch(Exception ex){
            System.out.println("Invalid");
    }
    }

    public void tick() {

        if (this.gender == 'M') {

            int tempnumroute = getPoint().getnumroute();// number of route from the point(point that current kangaroo is)
            int foodneeded = 0;
            int foodavailableforeating = 0;
            int foodatnewpoint = 0;
            LinkedList<Route> allowed = new LinkedList<Route>();
            Route choosen = null;

            for (int j = 0; j < tempnumroute; j++) {
                Route temproute = getPoint().getRoute(j);
                foodneeded = (temproute.getWeight() + this.food / 2);
                foodavailableforeating = (temproute.getLink().getfood() + this.food);
                foodatnewpoint = temproute.getLink().getfood();
                if (temproute.getLink().isMaxkang())//kalau dah penuh tempat tu takleh masuk
                {
                    continue;
                }
                if (foodavailableforeating >= foodneeded) {
                    //manage to go without considering colony
                    if (temproute.getLink().totalKangaroo() >= Points.colony) {
                        if (foodatnewpoint - foodneeded <= 0) {
                            foodavailableforeating -= foodneeded;
                            if (foodavailableforeating >= Points.colony) {
                                allowed.addNode(temproute);
                            } else {
                                continue;
                            }
                        } else {

                            if (this.food >= Points.colony) {
                                allowed.addNode(temproute);
                            } else {
                                continue;
                            }
                        }
                    } else {
                        allowed.addNode(temproute);
                    }
                } else {
                    continue;
                }

            }

            for (int i = 0; i < allowed.length(); i++) {

                if (choosen == null) {
                    choosen = allowed.atindex(i);
                } else {
                    if (choosen.getLink().getfood() < allowed.atindex(i).getLink().getfood()) {
                        choosen = allowed.atindex(i);
                    } else if (choosen.getLink().getfood() == allowed.atindex(i).getLink().getfood()) {
                        if (choosen.getLink().getfemale() < choosen.getLink().getmale()) {
                            choosen = allowed.atindex(i);
                        }
                    }
                }

            }

            this.pointID = choosen.getLink().getpointID();
            foodneeded = choosen.getWeight() + (this.food / 2); //must initialize again
            int foodeaten = 0;
            while (true) {
                if (foodeaten != foodneeded) {
                    if (choosen.getLink().getfood() != 0) {
                        choosen.getLink().minusfood(1);
                    } else {
                        this.food--;
                    }
                    foodeaten++;
                } else {
                    if (choosen.getLink().iscolonized()) {
                        this.food -= choosen.getLink().totalKangaroo();
                    }
                    break;
                }
            }

            if (choosen.getLink().totalKangaroo() >= Points.colony && choosen.getLink().iscolonized() == false) {
                choosen.getLink().setcolonized(true);
                Points.numberofcolony++;
            }

            System.out.println("choosen " + choosen.toString());//ni tak perlu pun saje je letak
        }
    }

}
