package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;

import org.insa.graphs.algorithm.utils.BinaryHeap;

import org.insa.graphs.model.Node ;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    private ArrayList <Label> listeLabels = new ArrayList <Label>();

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:

        for (int i = 0; i < data.getGraph().getNodes().size(); i++) {
            listeLabels.add(null);
        }
        
        //Initialisation
        for (int i = 0; i < data.getGraph().getNodes().size(); i++) {
            listeLabels.get(i).setSommetCourant(data.getGraph().getNodes().get(i)) ;
            listeLabels.get(i).setMarque(false) ;
            listeLabels.get(i).setCoutRealise(Double.POSITIVE_INFINITY) ;
            listeLabels.get(i).setPere(null);
        }

        listeLabels.get(0).setCoutRealise(0.0) ;
        BinaryHeap<Label> tas = new BinaryHeap<Label>() ;
        

        return solution;
    }

}
