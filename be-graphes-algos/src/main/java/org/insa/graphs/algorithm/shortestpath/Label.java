package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable <Label>{
   protected final Node sommetCourant ;
   private Boolean marque ;
   private Double coutRealise ;
   private Arc pere ;

   public Label (Node sommetCourant, Boolean marque, Double coutRealise, Arc pere){
      this.sommetCourant = sommetCourant ;
      this.marque = marque ;
      this.coutRealise = coutRealise ;
      this.pere = pere ;
   }

   public Node getSommetCourant () {
      return this.sommetCourant ;
   }

   public void setCoutRealise (Double coutRealise) {
      this.coutRealise = coutRealise ;
   }

   public Boolean getMarque () {
      return this.marque ;
   }

   public void setMarque (Boolean marque) {
      this.marque = marque ;
   }

   public Double getCoutRealise () {
      return this.coutRealise ;
   }

   public Arc getPere () {
      return this.pere ;
   }

   public void setPere (Arc pere) {
      this.pere = pere ;
   }

   public Double getCost () {
      return this.coutRealise ;
   }

   public int compareTo (Label l) {
      if (this.coutRealise-l.coutRealise < 0) {
         return -1 ;
      }
      else if (this.coutRealise-l.coutRealise < 0) {
         return 1 ;
      }
      else {
         return 0 ;
      }
   }
}