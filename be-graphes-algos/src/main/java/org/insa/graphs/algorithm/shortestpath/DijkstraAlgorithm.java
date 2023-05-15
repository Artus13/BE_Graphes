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
        
        //Initialisation
        //attention j'initialise tous les noeuds alors qu'on en a pas necessairement besoin
        int NbNoeuds = data.getGraph().size() ;
        ArrayList <Label> listeLabels = new ArrayList <Label>(NbNoeuds) ;
        for (int i = 0; i < NbNoeuds; i++) {
            listeLabels.add(new Label(data.getGraph().getNodes().get(i), false, Double.POSITIVE_INFINITY, null, false)) ;
        }

        listeLabels.get(data.getOrigin().getId()).setCoutRealise(0.0) ;
        BinaryHeap<Label> tas = new BinaryHeap<Label>() ;
        tas.insert(listeLabels.get(data.getOrigin().getId())) ;
        listeLabels.get(data.getOrigin().getId()).setInTas(true);

        //Iterations
        //ajouter avec distance
        int idDestination = data.getDestination().getId() ;
        Label labelDestination = listeLabels.get(idDestination) ;
        Label labelCourant ;
        Node nodeSuccesseur ;
        Label labelSucceseur ;
        

        while (!tas.isEmpty() && !labelDestination.getMarque()){
            labelCourant = tas.deleteMin() ;
            listeLabels.get(labelCourant.sommetCourant.getId()).setInTas(false) ;
            notifyNodeMarked(labelCourant.sommetCourant) ;
            for (Arc y: labelCourant.sommetCourant.getSuccessors()){
                nodeSuccesseur = y.getDestination() ;
                labelSucceseur = listeLabels.get(nodeSuccesseur.getId()) ;
                if (!labelSucceseur.getMarque()){
                    if(labelSucceseur.getCoutRealise() > labelCourant.getCost() + y.getLength()){
                        labelSucceseur.setCoutRealise(labelCourant.getCost() + y.getLength());
                        labelSucceseur.setPere(y);
                        if (labelSucceseur.getInTas()){
                            tas.remove(labelSucceseur);
                            listeLabels.get(labelSucceseur.sommetCourant.getId()).setInTas(false) ;
                        }
                        tas.insert(labelSucceseur);
                        listeLabels.get(labelSucceseur.sommetCourant.getId()).setInTas(false) ;
                    }
                }
            } 
            labelCourant.setMarque(true);
        }

        
        System.out.println(listeLabels.get(data.getDestination().getId()).getPere());
        if (listeLabels.get(data.getDestination().getId()).getPere() == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

        // Create the path from the array of predecessors...
        ArrayList<Arc> arcs = new ArrayList<>();
        Arc arc = listeLabels.get(data.getDestination().getId()).getPere();
        System.out.println(arc);
        while(arc != null){
            arcs.add(arc) ;
            arc = listeLabels.get(arc.getOrigin().getId()).getPere() ;
        }
        
        Collections.reverse(arcs);
        solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));

        
    }
    return solution;
    }
}
