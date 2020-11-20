package br.edu.up.pedido;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.edu.up.cardapio.Cardapio;

public class Pedido {
	private static List<ItemPedido> listaItemsPedido;
	private static String arquivo;
	private static String numPedido;
	
	static {
		arquivo = "";
		numPedido = "";
	}
	
	public static void pedido() {	    
	    
	    Scanner input = new Scanner(System.in);
	    int opcao = 0;
	    int qtde = 0;
		String codigo = "";
		ItemPedido itemSelecionado;
		boolean bOpcao = true;
	   
	    listaItemsPedido = new ArrayList<ItemPedido>();
	    
    	System.out.println("");
    	System.out.println("1 - Novo");
	    System.out.println("2 - Alterar");
	    System.out.println("");

    	System.out.print("Opção: ");
	    opcao = input.nextInt();
	    System.out.println("");
	    
	    if ( opcao == 2 ) {
	    	boolean bContinuar = true;
		    do {
		    	System.out.print("Informe o Número do Pedido: ");
			    
			    do {
			    	numPedido = input.nextLine();	
			    } while (numPedido.equals(""));
			    
			    try {
					carregarPedido(numPedido);
					
					if ( listaItemsPedido.size() <= 0 ) {
						System.out.println("Pedido não encontrado!");
					} else {
						arquivo = numPedido;
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    bContinuar = false;
		    } while ( bContinuar );		    	
	    }
	    
	    
	    do {   	    	
		    System.out.println("");
	    	System.out.println("-------------------------------------------");
		    System.out.println("PEDIDO: " + arquivo);
		    System.out.println("-------------------------------------------");
		    
		    if ( listaItemsPedido.size() > 0 ) {
		    	for (ItemPedido itemPedido : listaItemsPedido) {
		    		System.out.println(itemPedido);
				}
		    	
		    	System.out.println("-------------------------------------------");
		    	System.out.println("Total: " + printTotal());
		    	System.out.println("-------------------------------------------");
		    }
		    
		    System.out.println("1 - Adicionar");
		    System.out.println("2 - Alterar");
		    System.out.println("3 - Remover");
		    System.out.println("4 - Salvar");
		    System.out.println("5 - Cancelar ou Sair");
		    System.out.println("-------------------------------------------");
		    System.out.println("");

	    	System.out.print("Opção: ");
		    opcao = input.nextInt();
		    System.out.println("");
		    		    		    
		    if ( opcao == 1 ) {
		    	Cardapio.pedidoMenu(listaItemsPedido);
			} else if ( opcao == 2 ) {
				if ( listaItemsPedido.size() > 0 ) {
					do {
				    	System.out.println("");
						System.out.print("Informar o código do item a ser alterado: ");
					    
				    	do {
				    		codigo = input.nextLine();	
					    } while ( codigo.equals("") );
				    	
				    	if (codigo.equals("999")) {
				    		bOpcao = false;			    		
				    	} else if ( !codigo.equals("") ) {
					    	itemSelecionado = buscarCodigo(codigo);
					    	if (itemSelecionado != null) {
						    	System.out.print("Qtde: ");
							    qtde = input.nextInt();
							    itemSelecionado.setQtde(qtde);
							    atualizar(itemSelecionado);
					    	} else {
					    		System.out.println("Item não encontrado!");
					    	}
	                        
					    	String alterarSN = "";
					    	boolean bContinuar = true;
					    	
					    	do {
							    System.out.println("");
							    System.out.print("Alterar outro item S/N: ");
								
							    do {
							    	alterarSN = input.nextLine().toUpperCase();
							    } while (alterarSN.equals(""));
							    
								
								if ( !alterarSN.equals("S") && !alterarSN.equals("N") ) {
									System.out.print("Opção inválida!");
								} else if (alterarSN.equals("S")) {
									bContinuar = false;
									bOpcao = true;
								} else {
									bContinuar = false;
									bOpcao = false;								
								}
																			   	
							} while ( bContinuar );	
					    }
					} while ( bOpcao );	
				} else {
					System.out.println("Pedido não possui itens para serem alterados!");
				}
				
				bOpcao = true;
			
			} else if ( opcao == 3 ) {
				if ( listaItemsPedido.size() > 0 ) {
					do {
				    	System.out.println("");
				    	System.out.print("Informar o código do item a ser removido: ");
					    
				    	do {
				    		codigo = input.nextLine();	
					    } while ( codigo.equals("") );
				    	
				    	if (codigo.equals("999")) {
				    		bOpcao = false;			    		
				    	} else if ( !codigo.equals("") ) {
					    	itemSelecionado = buscarCodigo(codigo);
					    	if (itemSelecionado != null) {
							    excluir(itemSelecionado);
					    	} else {
					    		System.out.println("Item não encontrado!");
					    	}
	                        
					    	String alterarSN = "";
					    	boolean bContinuar = true;
					    	
					    	do {
							    System.out.println("");
							    System.out.print("Remover outro item S/N: ");
								
							    do {
							    	alterarSN = input.nextLine().toUpperCase();
							    } while (alterarSN.equals(""));
							    
								
								if ( !alterarSN.equals("S") && !alterarSN.equals("N") ) {
									System.out.print("Opção inválida!");
								} else if (alterarSN.equals("S")) {
									bContinuar = false;
									bOpcao = true;
								} else {
									bContinuar = false;
									bOpcao = false;								
								}
																			   	
							} while ( bContinuar );	
					    }
					} while ( bOpcao );					
				}
				else {
					System.out.println("Pedido não possui itens para serem removidos!");
				}
				bOpcao = true;			
			} else if ( opcao == 4 ) {
				try {
					salvarPedido(arquivo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if ( opcao == 5 ) {
				arquivo = "";
			}
		} while ( opcao != 5 );
	    
	    input.close();
	    
	    listaItemsPedido = null;
	}
	
	public static void excluir(ItemPedido item) {
	    listaItemsPedido.remove(item);	    	
	}
	
	public static ItemPedido buscarCodigo(String query) {
		 ItemPedido itemRetornar = null;
		 for (ItemPedido item : listaItemsPedido) {
			 if (item.getCodigo().equals(query)) {
				 itemRetornar = item;
				 break;
			 }
		 }
		 
		 return itemRetornar;
	}

	
	public static String printTotal() {
		double total = 0;
		for (ItemPedido itemPedido : listaItemsPedido) {
			total = total + itemPedido.getTotal(); 
		}
		
		return new DecimalFormat("#0.00").format( total );
	}
	
	public static void atualizar(ItemPedido item) {
		for (ItemPedido itemAtualizar : listaItemsPedido) {
			if (itemAtualizar.getCodigo().equals(item.getCodigo())) {
				itemAtualizar.setQtde(item.getQtde());
				
				break;
			}
		}	
	}
	
	public static void salvarPedido(String pedido) throws IOException {	
		if ( listaItemsPedido.size() > 0 ) {
			SimpleDateFormat f = new SimpleDateFormat("ddMMyyyyHHmmss");
			Date data = new Date();
			
			if ( pedido.equals("")) {
				arquivo = "P" + f.format(data) + ".csv";
			} else {
			    arquivo = pedido;	
			}
			
			FileWriter salvarPedido = new FileWriter("C:\\Users\\Fernando\\eclipse-workspace\\aulas\\java\\ProgramaCardapioObj\\arquivos\\Pedido\\"+arquivo);
			PrintWriter gravador = new PrintWriter(salvarPedido);
			gravador.println("Código;Descrição;Quantidade;Preço");		
			for (ItemPedido itemPedido : listaItemsPedido) {
				gravador.println(itemPedido.toCSV());				
			}
		
			gravador.close();
			salvarPedido.close();
			System.out.println("Pedido Salvo!");						
		} else {
			System.out.println("Pedido não possui itens para ser salvo!");			
		}
	}
	
	public static void carregarPedido(String numPedido) throws FileNotFoundException{
		//ENTRADA
		File arquivo;
		
		if ( new File("C:\\Users\\Fernando\\eclipse-workspace\\aulas\\java\\ProgramaCardapioObj\\arquivos\\Pedido\\" + numPedido).exists() ) {
			arquivo = new File("C:\\Users\\Fernando\\eclipse-workspace\\aulas\\java\\ProgramaCardapioObj\\arquivos\\Pedido\\" + numPedido);
			
			Scanner leitor = new Scanner(new FileInputStream(arquivo));
			leitor.nextLine();
					
			while (leitor.hasNext()) {
				String linha = leitor.nextLine();
				String[] partes = linha.split(";");

				ItemPedido item = new ItemPedido( partes[0]
						                         , partes[1]
						                         , Double.parseDouble(partes[3].replaceAll(",", "."))
						                         , Integer.parseInt(partes[2]) );
				listaItemsPedido.add(item);
			}
			leitor.close();			

		}		
	}		
}
