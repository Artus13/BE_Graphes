package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;
import org.insa.graphs.model.Arc;

public class LabelStar extends Label{

   private Double coutVolOiseau ;

   public LabelStar (Node sommetCourant, Boolean marque, Double coutRealise, Arc pere, Boolean inTas, Node destination){
      super(sommetCourant, marque, coutRealise, pere, inTas);
      this.coutVolOiseau = Point.distance(sommetCourant.getPoint(), destination.getPoint()) ;
   }

   public Double getTotalCost (){
      return this.getCoutRealise() + this.coutVolOiseau ;
   }
}
