package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.utils.BinaryHeap;

import org.insa.graphs.model.Node ;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.AbstractSolution.Status;

import org.insa.graphs.model.Arc;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:

        ArrayList <Label> listeLabels = new ArrayList <Label>(data.getGraph().size());
        
        //Initialisation
        //attention j'initialise tous les noeuds alors qu'on en a pas necessairement besoin
        int NbNoeuds = data.getGraph().getNodes().size() ;
        System.out.println(data.getOrigin().getId());
        for (int i = 0; i < NbNoeuds; i++) {
            listeLabels.add(new Label(data.getGraph().getNodes().get(i), false, Double.POSITIVE_INFINITY, null)) ;
        }

        listeLabels.get(data.getOrigin().getId()).setCoutRealise(0.0) ;
        BinaryHeap<Label> tas = new BinaryHeap<Label>() ;
        tas.insert(listeLabels.get(data.getOrigin().getId())) ;

        //Iterations
        int idDestination = data.getDestination().getId() ;
        Label labelDestination = listeLabels.get(idDestination) ;
        Label labelCourant ;
        Node nodeSuccesseur ;
        Label labelSucceseur ;
        Arc arcCourant ;
        Double oldCost ;
        Double newCost ;

        while (!labelDestination.getMarque() && !tas.isEmpty()){
            labelCourant = tas.findMin() ;
            labelCourant.setMarque(true) ;
            for (int y = 0 ; y < labelCourant.getSommetCourant().getNumberOfSuccessors() ; y ++){
                nodeSuccesseur = labelCourant.getSommetCourant().getSuccessors().get(y).getDestination() ;
                labelSucceseur = listeLabels.get(nodeSuccesseur.getId()) ;
                arcCourant = labelCourant.getSommetCourant().getSuccessors().get(y) ;
                if (!labelSucceseur.getMarque()){
                   // oldCost = labelSucceseur.getCoutRealise() ;
                    //newCost = oldCost, labelCourant.getCost() + labelCourant.getSommetCourant().getSuccessors().get(y).getLength();
                    if(labelSucceseur.getCoutRealise() > labelCourant.getCost() + labelCourant.getSommetCourant().getSuccessors().get(y).getLength()){
                        labelSucceseur.setCoutRealise(labelCourant.getCost() + labelCourant.getSommetCourant().getSuccessors().get(y).getLength());
                        if(labelSucceseur.getCoutRealise()!=Double.POSITIVE_INFINITY){
                            tas.remove(labelSucceseur);
                            tas.insert(labelSucceseur);
                            labelSucceseur.setPere(arcCourant);
                        }
                        else{
                            tas.insert(labelSucceseur);
                            labelSucceseur.setPere(arcCourant);
                        }
                    }
                }
            } 
        }

        ArrayList<Arc> arcs = new ArrayList<>();
        Arc arc = listeLabels.get(data.getDestination().getId()).getPere();
        System.out.println(arc);
        while(arc != null){
            arcs.add(arc) ;
            arc = listeLabels.get(arc.getOrigin().getId()).getPere() ;
        }
        
        Collections.reverse(arcs);
        solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));

        return solution;
    }

}
