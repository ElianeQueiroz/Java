package br.edu.up.cardapio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.dominio.Item;
import br.edu.up.pedido.ItemPedido;
import br.edu.up.pedido.Pedido;

public class Cardapio {
	
	private static String pratos = "C:\\Users\\Fernando\\eclipse-workspace\\ProgramaCardapioObj\\arquivos\\pratos.csv" ;
	private static String bebidas = "C:\\Users\\Fernando\\eclipse-workspace\\ProgramaCardapioObj\\arquivos\\bebidas-tabuladas.txt";
	private static String vinhos = "C:\\Users\\Fernando\\eclipse-workspace\\ProgramaCardapioObj\\arquivos\\vinhos-tabulados.txt";
	
	private static List<Item> listaDePratos;
	private static List<Item> listaDeBebidas;
	private static List<Item> listaDeVinhos;
	
	static {		
		listaDePratos = new ArrayList<Item>();
		listaDePratos = carregarLista(1, pratos); 
		
		listaDeBebidas = new ArrayList<Item>();
		listaDeBebidas = carregarLista(2, bebidas); 
		
		listaDeVinhos = new ArrayList<Item>();
		listaDeVinhos = carregarLista(3, vinhos); 
	}

	private static List<Item> carregarLista(int categoria, String parametro) {
		List<Item> listaRetorno = new ArrayList<>();
		
		try {
			File arquivo = new File (parametro);
		
			Scanner leitor = new Scanner(new FileInputStream(arquivo), "UTF-8");
			leitor.nextLine();
			
			String codigo = "";
			int sequencia = 0;
			
			while(leitor.hasNext()) {
				String linha = leitor.nextLine();
				sequencia = sequencia + 1;
				
				codigo = Integer.toString(categoria) + "." + Integer.toString(sequencia);
				
				if ( categoria == 1 ) {
					String[] partes = linha.split(";");				
					Item item = new Item(codigo, partes[0], partes[1].replaceAll(",", "."));
					listaRetorno.add(item);
				} else {
					String[] partes = linha.split("	");				
					Item item = new Item(codigo, partes[1], partes[0].replaceAll(",", "."));
					listaRetorno.add(item);
				}				
			}
			leitor.close();	
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
		return listaRetorno;
	}
	
		
	public static List<Item> listar(int categoria) {
		if (categoria == 1)
			return listaDePratos;
		else if (categoria == 2)
			return listaDeBebidas;
		else if (categoria == 3)
			return listaDeVinhos;
		else
			return null;
	}

	public static void incluir(int categoria, Item item) {
		if ( categoria == 1 ) {
			listaDePratos.add(item);
		} else if ( categoria == 2 ) {
			listaDeBebidas.add(item);
		} else if ( categoria == 3 ) {
			listaDeVinhos.add(item);
		}
	}
		
	public static void excluir(int categoria, Item item) {
		if ( categoria == 1 ) {
			listaDePratos.remove(item);
		} else if ( categoria == 2 ) {
			listaDeBebidas.remove(item);
		} else if ( categoria == 3 ) {
			listaDeVinhos.remove(item);
		}
	}

	private static void gravarListaAtualizada(int categoria) {
		
		try {
			String path = "";
			List<Item> lista = null;
			String linha1 = "";
			
			if ( categoria == 1 ) {
				path = pratos;
				lista = listaDePratos;
				linha1 = "PRATOS" + ";" + "PRECO";
			} else if ( categoria == 2 ) {
				linha1 = "PRECO" + "	" +  "BEBIDAS";
				path = bebidas;
				lista = listaDeBebidas;
			} else if ( categoria == 3 ) {
				linha1 = "PRECO" + "	" +  "VINHOS";
				path = vinhos;
				lista = listaDeVinhos;
			} 
			
			FileWriter arquivo = new FileWriter(path);
			PrintWriter gravador = new PrintWriter(arquivo);
			
			gravador.println(linha1);
			
			for (Item item : lista) {
				gravador.println(item.toCSV(categoria));					
			}
			
			gravador.close();
			arquivo.close();
			
		} catch (IOException e) {
			System.out.println("erro na gravacao do arquivo!");
		}
		
	}

	public static void atualizar(Item item) {
		for (Item itemAtualizar : listaDePratos) {
			if (itemAtualizar.getNome().equals(item.getNome())) {
				itemAtualizar.setNome(item.getNome());
				itemAtualizar.setPreco(item.getPreco());
				
				break;
			}
		}	
	}

	public static Item buscarPornome(String query) {
		 Item itemRetornar = null;
		 for (Item item : listaDePratos) {
			 if (item.getNome().contains(query)) {
				 itemRetornar = item;
				 break;
			 }
		 }
		
		return itemRetornar;
	}
	
	public static Item buscarCodigo(String query) {
		 Item itemRetornar = null;
		 for (Item item : listaDePratos) {
			 if (item.getCodigo().equals(query)) {
				 itemRetornar = item;
				 break;
			 }
		 }
		 
		 for (Item item : listaDeBebidas) {
			 if (item.getCodigo().equals(query)) {
				 itemRetornar = item;
				 break;
			 }
		 }
		 
		 for (Item item : listaDeVinhos) {
			 if (item.getCodigo().equals(query)) {
				 itemRetornar = item;
				 break;
			 }
		 }
		
		return itemRetornar;
	}

	public static void manutecaoMenu() {
		int opcao = 0;
		
		Scanner input = new Scanner(System.in);
		                
    	do {    		
    		recarregarListas();
    		
    		System.out.println("");
        	System.out.println("1 - Pratos");
    	    System.out.println("2 - Bebidas");
    	    System.out.println("3 - Vinhos");
    	    System.out.println("4 - Voltar");

    		System.out.println("");
    		System.out.print("Opção: ");
        	opcao = input.nextInt();
        	System.out.println("");
    	    
    		switch (opcao) {
			case 1: {			    
			    opcaoManutencao(opcao);
			    break;
			}
			case 2: {
				opcaoManutencao(opcao);
			    break;
			}
			case 3: {
				opcaoManutencao(opcao);
			    break;
			}
			case 4: {
			    break;
			}
			default:
				break;
			}
	    } while ( opcao != 4 );
	}
	
	public static void pedidoMenu(List<ItemPedido> lista) {
		recarregarListas();
		
		List<Item> listaPratos = Cardapio.listar(1);
		System.out.println("-------------------------------------------");
	    System.out.println("1 - Pratos");
	    System.out.println("-------------------------------------------");
	    
	    for (Item item : listaPratos) {
	    	System.out.println(item);	
		}

	    List<Item> listaBebidas = Cardapio.listar(2);
		System.out.println("-------------------------------------------");
	    System.out.println("2 - Bebidas");
	    System.out.println("-------------------------------------------");
	    
	    for (Item item : listaBebidas) {
	    	System.out.println(item);	
		}
	    
	    List<Item> listaVinhos = Cardapio.listar(3);
		System.out.println("-------------------------------------------");
	    System.out.println("3 - Vinhos");
	    System.out.println("-------------------------------------------");
	    
	    for (Item item : listaVinhos) {
	    	System.out.println(item);	
		}
	    
	    System.out.println("-------------------------------------------");
	    System.out.println("");
	    
	    Scanner input = new Scanner(System.in);
	    String opcao = "0";
	    String opcaoSN = "";
	    boolean bOpcao = false;
	    
	    System.out.println("Adicionar item no pedido.");

	    do {   
	    	System.out.println("");
	    	System.out.print("Código: ");
		    opcao = input.nextLine();
		    
		    if ( !opcao.equals("999") ) {
		    	Item itemRetornado = Cardapio.buscarCodigo(opcao);
		    	if ( itemRetornado != null  ) {
		    		int qtde = 0;
		    		System.out.println(itemRetornado);
		    		System.out.println("Qtde: ");
				    qtde = input.nextInt();
				    input.nextLine();
				    ItemPedido itemSelecionado = new ItemPedido( itemRetornado.getCodigo()
				    		                                   , itemRetornado.getNome()
				    		                                   , itemRetornado.getPreco()
				    		                                   , qtde);
				    lista.add(itemSelecionado);
		    		itemRetornado = null;
		    	} else {
		    		System.out.println("Código Inválido.");
		    	}
				
				do {
				    System.out.println("");
				    System.out.print("Inserir novo item S/N: ");
					opcaoSN = input.nextLine().toUpperCase();
					
					bOpcao = false;
					if ( opcaoSN.equals("S") || opcaoSN.equals("N") ) {
						bOpcao = true;
					} else {
						System.out.println("Opção inválida!");
					}					   	
				} while ( bOpcao != true );
		    }
		    	
		} while ( opcaoSN.equals("S") );
	}
	
	public static void opcaoManutencao(int categoria) {
		Scanner input = new Scanner(System.in);
	    int opcao = 0;
	    String codigo = "";
	    String nome = "";
	    String preco = "0";
	    	    
	    do {	    	
			List<Item> lista = Cardapio.listar(categoria);
			System.out.println("-------------------------------------------");
		    if ( categoria == 1 )
		    	System.out.println("1 - Pratos");
		    else if ( categoria == 2 )
		    	System.out.println("2 - Bebidas");
		    else if ( categoria == 3 )
		    	System.out.println("3 - Vinhos");
		    System.out.println("-------------------------------------------");
		    
		    for (Item item : lista) {
		    	System.out.println(item);	
			}

		    System.out.println("-------------------------------------------");	    
		    System.out.println("1 - Adicionar");
		    System.out.println("2 - Alterar");
		    System.out.println("3 - Remover");
		    System.out.println("4 - Salvar");
		    System.out.println("5 - Voltar");
		    System.out.println("-------------------------------------------");
		    System.out.println("");

	    	System.out.print("Opção: ");
		    opcao = input.nextInt();
		    System.out.println("");
		    
		    if ( opcao != 5 ) {
		    	switch (opcao) {
				case 1: {
					System.out.print("Código: ");
				    do {
				    	codigo = input.nextLine();
				    } while (codigo.equals(""));
					System.out.print("Nome: ");
					do {
				    	nome = input.nextLine();
				    } while (nome.equals(""));
					
					System.out.print("Preço: ");
					preco = input.nextLine();
					
					Item itemNovo = new Item(codigo, nome, preco);
					
					incluir(categoria, itemNovo);
					break;
				}
				case 2: {
					System.out.print("Código: ");
				    do {
				    	codigo = input.nextLine();
				    } while (codigo.equals(""));
					
					Item itemSelecionando = buscarCodigo(codigo);
	                
					System.out.println(itemSelecionando);
					
					System.out.print("Atualizar Preço para: "); 
					preco = input.nextLine();
				    itemSelecionando.setPreco(Double.parseDouble(preco.replace(',', '.')));
					
					atualizar(itemSelecionando);
					break;
				}
	            case 3: {
					
	            	System.out.print("Código: ");
				    do {
				    	codigo = input.nextLine();
				    } while (codigo.equals(""));
				    
					Item itemSelecionando = buscarCodigo(codigo);
	                
					excluir(categoria, itemSelecionando);
					break;
				}
	            case 4: {
	            	gravarListaAtualizada(categoria);            	
	        	    recarregarListas();
	            	break;
				}
				default:
					break;
				}	
		    }	    	
	    } while ( opcao != 5 );
	}
	
	public static void recarregarListas() {
		listaDePratos = carregarLista(1, pratos); 
		listaDeBebidas = carregarLista(2, bebidas); 
		listaDeVinhos = carregarLista(3, vinhos); 
	}
}
