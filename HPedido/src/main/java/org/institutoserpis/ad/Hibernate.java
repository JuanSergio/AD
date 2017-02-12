package org.institutoserpis.ad;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.*;

public class Hibernate {
	
	private static Scanner tcl;
	private static EntityManager entityManager; 
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args){
		
		entityManagerFactory = Persistence.createEntityManagerFactory("org.institutoserpis.ad.hpedido");
	
		tcl = new Scanner(System.in);
		int opc = 0;
		
		System.out.println("MENÚ");
		System.out.println("----------------------------");
		System.out.println("1. Insertar");
		System.out.println("2. Modificar");
		System.out.println("3. Eliminar");
		System.out.println("4. Consultar");
		System.out.println("5. Lista");
		System.out.println("0. Salir");
		
		System.out.println("-----------------------------");
		
		do{
			System.out.println("Escoge una opción: ");
			opc = tcl.nextInt();
		
			switch (opc) {
			case 1:
				int opci=0;
				System.out.println("INSERTAR");
				System.out.println();
				System.out.println("7. Cliente");
				System.out.println("8. Pedido");
				System.out.println("9. Salir");
				opci=tcl.nextInt();
				
					if(opci==7) {
						System.out.println("Vas a insertar un cliente");
						System.out.println("Indica el nombre");
						String nombre=tcl.next();
						nuevocliente(nombre);
					}
						
						
					if(opci==8){
						System.out.println("Vas a insertar un pedido");
						System.out.println("Introduce el nombre del cliente");
						String nombre=tcl.next();
						System.out.println("Introduce el importe");
						BigDecimal total=tcl.nextBigDecimal();
						nuevopedido(nombre,total);
					}		
					if(opci==9){
						System.out.println("Has salido de la opción insertar");
						break;
					}
					
					
		
			case 2:
				System.out.println("Vas a modificar un articulo");
				System.out.println();
				//listar();
				//modificar();
				break;
			
			case 3:
				System.out.println("Vas a eliminar un artículo");
				System.out.println();
				//eliminar();
				break;
			
			case 4:
				System.out.println("Vas a consultar un artículo de tu base de datos");
				System.out.println();
				//consultar();
				break;
			
			case 5:
				System.out.println("Vas a listar los artículos de tu base de datos");
				System.out.println();
				//listar();
				break;
			
			case 0:
				System.out.println("Has salido del programa");
				break;

			default:
				System.out.println("Opción erronea");
				break;
			}
		}while(opc!=0);
		
}
	
	public static void nuevocliente(String nombre){
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Cliente cliente=new Cliente();
		cliente.setNombre(nombre);
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
public static void nuevopedido(String idcliente,BigDecimal importe){
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Cliente cliente2=entityManager.getReference(Cliente.class, Long.parseLong(idcliente));
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente2);
		
		java.util.Date fec= Calendar.getInstance().getTime();
		
		Date date= new Date(fec.getDate());
		pedido.setDate(date);
		pedido.setImporte(importe);
		
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
}


