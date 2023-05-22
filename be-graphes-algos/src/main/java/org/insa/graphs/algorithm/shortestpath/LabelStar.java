package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;
import org.insa.graphs.model.Arc;
import org.insa.graphs.algorithm.AbstractInputData ;

public class LabelStar extends Label{

   private Double coutVolOiseau ;

   public LabelStar (Node sommetCourant, Boolean marque, Double coutRealise, Arc pere, Boolean inTas, Node destination, ShortestPathData data){
      super(sommetCourant, marque, coutRealise, pere, inTas);

      if (data.getMode() == AbstractInputData.Mode.LENGTH){
         this.coutVolOiseau = Point.distance(sommetCourant.getPoint(), destination.getPoint()) ;
      }
      else if (data.getMode() == AbstractInputData.Mode.TIME){
         int maximumSpeed = data.getGraph().getGraphInformation().getMaximumSpeed() ;
         this.coutVolOiseau = Point.distance(sommetCourant.getPoint(), destination.getPoint())/(maximumSpeed*1000/3600) ;
      }
   }

   @Override
   public Double getTotalCost (){
      return this.getCoutRealise() + this.coutVolOiseau ;
   }

   public Double getCoutVolOiseau () {
      return this.coutVolOiseau ;
   }
}
