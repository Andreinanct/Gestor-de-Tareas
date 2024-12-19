/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ventanas.logicaVentanas;

/**
 *
 * @author Jhoel Rivera
 */
public class Nodo<J>{
      Nodo ant;
      Nodo sig;
      J valor;
      public Nodo(J valor){
                this.valor=valor;
      }
      public Nodo getant(){
         return ant;
      }
      public Nodo getsig(){
        return sig;
      }
      public J getValor(){
          return valor;
      }
      public void setValor(J nuevo){
          valor = nuevo;
      }
      public void setsig(Nodo nuevo){
        sig = nuevo;
      }
      
}
