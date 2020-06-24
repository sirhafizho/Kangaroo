package jumpygrof.github;

import java.awt.Dimension;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseEvent;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.event.MouseListener;
/**
 *
 * @author Kisyok
 */
public class GraphVisualization{
    static int edgeCount = 0;
    static jumpygrof.github.LinkedList<Kangaroo> Kangaroos = new jumpygrof.github.LinkedList<Kangaroo>();// Kangaroos static so other class can use it
    static Map Hafiz = new Map();// Map is static so that other class can access it
    
    class MyNode{
        String id;
        public MyNode(String id){
            this.id = id;
        }
        public String toString(){    
            return id;  
        } 
        public String Node_Property(){
            String node_prop = id;
            return(node_prop);
        }
    }

    class MyLink {
        int weight;
        String Label;
        int id;

         public MyLink(int weight){
            this.id = edgeCount++;
            this.weight = weight;
        } 

        public String toString(){
            return "E"+id;
        }
        public String Link_Property(){
            String Link_prop = Label;
            return(Link_prop);
        }
        public String Link_Property_wt(){
            String Link_prop_wt = ""+weight;
            return(Link_prop_wt);
        }
    }
    
//to build graph and call graph algorithm from JUNG
public void Visualize_Directed_Graph(LinkedList<String> DistinctNodes, LinkedList<String> sourceVertex, LinkedList<String> DestinationVertex, LinkedList<Integer> EdgeHeight)
{
        //to create directed graph with weight
        Graph<MyNode, MyLink> g = new DirectedSparseGraph<GraphVisualization.MyNode, GraphVisualization.MyLink>();
        
        //to create the nodes
        Hashtable<String, MyNode> GraphNodes = new Hashtable<String, GraphVisualization.MyNode>();
        LinkedList<MyNode> SourceNode = new LinkedList<GraphVisualization.MyNode>();
        LinkedList<MyNode> DestinationNode = new LinkedList<GraphVisualization.MyNode>();
        
        //to create graph nodes
        for(int i=0;i<DistinctNodes.size();i++){
            String nodeName = DistinctNodes.get(i);
            MyNode data = new MyNode(nodeName);
            GraphNodes.put(nodeName, data);
        }
        
        //to convert all source and destination nodes into objects
        for(int t=0;t<sourceVertex.size();t++){
            SourceNode.add(GraphNodes.get(sourceVertex.get(t)));
            DestinationNode.add(GraphNodes.get(DestinationVertex.get(t)));
        }
        
        //to add the nodes and edges to the graph
        for(int i=0;i<EdgeHeight.size();i++){
            g.addEdge(new MyLink(EdgeHeight.get(i)),SourceNode.get(i), DestinationNode.get(i), EdgeType.DIRECTED);
        }

       
        CircleLayout<MyNode, MyLink> layout1 = new CircleLayout<MyNode,MyLink>(g);
            layout1.setSize(new Dimension(300, 300));//to zoom
            VisualizationViewer<MyNode, MyLink> viz = new VisualizationViewer<MyNode,MyLink>(layout1);
           
        viz.setPreferredSize(new Dimension(500, 500));// to set the size of the frame
           
        viz.addGraphMouseListener(new GraphMouseListener() {
            @Override
            public void graphClicked(Object a, MouseEvent click) {
                if (click.getButton() == MouseEvent.BUTTON1 && click.getClickCount() == 1){//to allow double clicking on the vertex
                    System.out.println();
                    System.out.println();
                    int counter = 0;
                    
                    for(int i = 0; i<JumpyGrof.Hafiz.Kangaroos.length(); i++){
                        if(JumpyGrof.Hafiz.Kangaroos.atindex(i).getpointID() == Integer.parseInt(a.toString())){
                            counter++;
                        }           
                    }
                    
                    System.out.println("---------------------------------------------------------------------------------");
                    
                    System.out.println("Point: " + JumpyGrof.Hafiz.points.atindex(Integer.parseInt(a.toString())-1).getpointID());
                    System.out.println("Food: "+JumpyGrof.Hafiz.points.atindex(Integer.parseInt(a.toString())-1).getfood());
                    System.out.println("Kangaroos at this point : " + counter);
                    for(int i = 0; i<JumpyGrof.Hafiz.Kangaroos.length(); i++){
                        if(JumpyGrof.Hafiz.Kangaroos.atindex(i).getpointID() == Integer.parseInt(a.toString())){
                            System.out.println(JumpyGrof.Hafiz.Kangaroos.atindex(i).toString());
                        }           
                    }
                    System.out.println("---------------------------------------------------------------------------------");
                }
                click.consume();
            }

            @Override
            public void graphPressed(Object arg0, MouseEvent arg1) {
            }

            @Override
            public void graphReleased(Object arg0, MouseEvent arg1) {
            }
        });
        
        Transformer<MyNode, String> vertexLabel = new Transformer<MyNode, String>(){
            public String transform(MyNode vertex){
               return (String) "(" + vertex.Node_Property() + ")";
            }
        };
        
        //Sets the vertex label font
        Transformer<MyNode,Font> vertexFont = new Transformer<MyNode,Font>(){
            public Font transform(MyNode node){
                Font font = new Font("Times New Roman", Font.BOLD, 30 );
                return font;
            }
        };
     

        Transformer<MyLink, String> edgeLabel = new Transformer<MyLink, String>(){
            public String transform(MyLink edge){
               return edge.Link_Property_wt();
            }
        };

        Transformer<MyLink,Font> edgeFont = new Transformer<MyLink,Font>(){
            public Font transform(MyLink node){
                Font font = new Font("Sans Serif", Font.PLAIN, 25);
                return font;
            }
        };
           
        Transformer<MyNode,Shape> vertexSize = new Transformer<MyNode,Shape>(){
            public Shape transform(MyNode i){
                Ellipse2D circle = new Ellipse2D.Double(-15, -15, 60, 60);//to change the size of vertexes
                if(i.equals(2)) return AffineTransform.getScaleInstance(2, 2).createTransformedShape(circle);
                else return circle;
            }
        };
        
        Transformer<MyNode,Paint> vertexColour = new Transformer<MyNode,Paint>(){
            public Paint transform(MyNode i){
                for (int k = 0; k < JumpyGrof.Hafiz.points.length(); k++) {
                    if (JumpyGrof.Hafiz.points.atindex(k).iscolonized() == true) {
                        if(i.Node_Property().equals(JumpyGrof.Hafiz.points.atindex(k).getpointID() + "")) {//to set specific vertex colour (colony)
                            return Color.ORANGE;
                        } 
                    }
                }
               return Color.GREEN;
            }
        };
           
           viz.getRenderContext().setVertexFillPaintTransformer(vertexColour);
           viz.getRenderContext().setVertexShapeTransformer(vertexSize);
           viz.getRenderContext().setEdgeLabelTransformer(edgeLabel);
           viz.getRenderContext().setVertexLabelTransformer(vertexLabel);
           viz.getRenderContext().setVertexFontTransformer(vertexFont);
           viz.getRenderContext().setEdgeFontTransformer(edgeFont);

           //to show the frame
           JFrame frame = new JFrame("Graph Visualisation");
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.getContentPane().add(viz);
           frame.pack();
           frame.setVisible(true);

}
    public Object coordinate(MouseEvent e){
        return e.getSource();
            }
 
}
