package br.edu.up.sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.cardapio.Cardapio;
import br.edu.up.dominio.Item;
import br.edu.up.pedido.Pedido;

public class Programa {
	public static void main(String[] args) {  
		
		int menu = 0;
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println("");
		    System.out.println("..........................");
		    System.out.println("1 - Pedido");
		    System.out.println("2 - Manutenção do Cardapio");
		    System.out.println("3 - Sair");
		    System.out.println("..........................");
		    System.out.println("");		   	    
		   
		    System.out.print("Opção: ");
		    menu = input.nextInt();
		    input.nextLine();
		    System.out.println("");	
		    
		    if ( menu == 1 ) {
				Pedido.pedido();
			} else if ( menu == 2 ) {
				Cardapio.manutecaoMenu();
			}
				
		} while ( menu != 3);
	}
}
