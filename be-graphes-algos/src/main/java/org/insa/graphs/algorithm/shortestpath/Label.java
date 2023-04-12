package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class Label implements Comparable <Label>{
   private Node sommetCourant ;
   private Boolean marque ;
   private Double coutRealise ;
   private Node pere ;

   public Node getSommetCourant () {
      return this.sommetCourant ;
   }

   public void setSommetCourant (Node sommetCourant) {
      this.sommetCourant = sommetCourant ;
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

   public Node getPere () {
      return this.pere ;
   }

   public void setPere (Node pere) {
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