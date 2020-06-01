package jumpygrof.github;

public class Route implements Comparable<Route>{
    private int weight;//obstacles level
    private Points link;
    
    public Route(){
        this.weight = 0;
        this.link = null;
    }
    public Route(Points link, int weight){
        this.weight = weight;
        this.link = link;
    }
    
    public void setLink(Points link){
        this.link = link;
    }
    
    public Points getLink(){
        return this.link;
    }
    public int getWeight(){
        return this.weight;
    }

    @Override
    public String toString() {
        return "Route " + weight + " to point " +link.getpointID() + '}';
    }

    @Override
    public int compareTo(Route o) {
        if(this.weight == o.getWeight())
            return 0;
        else if(this.weight > o.getWeight())
            return 1;
        else
            return -1;
    }
   
}
