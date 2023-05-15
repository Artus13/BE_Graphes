package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable <Label>{
   protected final Node sommetCourant ;
   private Boolean marque ;
   private Double coutRealise ;
   private Arc pere ;
   private Boolean inTas ;

   public Label (Node sommetCourant, Boolean marque, Double coutRealise, Arc pere, Boolean inTas){
      this.sommetCourant = sommetCourant ;
      this.marque = marque ;
      this.coutRealise = coutRealise ;
      this.pere = pere ;
      this.inTas = inTas ;
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

   public Boolean getInTas () {
      return this.inTas ;
   }

   public void setInTas (Boolean inTas) {
      this.inTas = inTas ;
   }

   public Double getTotalCost (){
      return this.getCoutRealise() ;
   }

   public int compareTo (Label l) {
      if (this.getTotalCost()-l.getTotalCost() > 0) {
         return 1 ;
      }
      else if (this.getTotalCost()-l.getTotalCost() < 0) {
         return -1 ;
      }
      else {
         if (this.getTotalCost() - this.getCoutRealise() - (this.getTotalCost() - this.getCoutRealise()) > 0){
            return 1 ;
         }
         else if (this.getTotalCost() - this.getCoutRealise() - (this.getTotalCost() - this.getCoutRealise()) < 0){
            return -1 ;
         }
         else {
            return 0 ;
         }
      }
   }
}