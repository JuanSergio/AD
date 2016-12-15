package org.institutoserpis.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GArticulo {
	private static Connection connection;
	private static Scanner tlc = new Scanner (System.in);


	public static void main(String[] args) throws SQLException{

		connection=DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba", 
				"root", "sistemas");

		int opcion = 0;

		do{
			System.out.println("========BASE DE DATOS=======");     
			System.out.println("1.Nuevo");
			System.out.println("2.Modificar");
			System.out.println("3.Eliminar");
			System.out.println("4.Consultar");
			System.out.println("5.Listar");
			System.out.println("0.Salir");
			System.out.println("============================");
			System.out.println("Selecciona una opcion...");

			opcion = tlc.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Nuevo...");
				nuevo();
				break;
			case 2:
				System.out.println("Modificar...");
				modificar();
				break;
			case 3:
				System.out.println("Eliminar...");
				eliminar();
				break;
			case 4:
				System.out.println("Consultar...");
				consultar();
				break;
			case 5:
				System.out.println("Listar...");
				listar();
				break;
			case 0:
				System.out.println("Gracias por usar el servicio...");
			}
		}while (opcion!=0);



	}
	public static void nuevo() throws SQLException{
		System.out.printf("Introduce un nombre: ");
		String nombre = tlc.next();

		System.out.printf("Introduce un precio: ");
		Double precio = tlc.nextDouble();

		System.out.printf("Introduce una categoría: ");
		int categoria = tlc.nextInt();

		String sql="INSERT INTO articulo (id, nombre, precio, categoria)"
				+ "VALUES (id, ?, ?, ?) ";

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, nombre);
		ps.setDouble(2, precio);
		ps.setInt(3, categoria);

		ps.executeUpdate();
		ps.close();
	}

	public static void modificar() throws SQLException{
		System.out.println("Introduce la id a buscar: ");
		int id = tlc.nextInt();

		System.out.println("Introduce el nuevo nombre: ");
		String nuevoNombre = tlc.next();

		System.out.println("Introduce el nuevo precio: ");
		Double nuevoPrecio = tlc.nextDouble();

		System.out.println("Introduce la nueva categoria: ");
		int nuevaCategoria = tlc.nextInt();

		String sql="UPDATE articulo SET nombre=?, precio=?, categoria=? "
				+ "WHERE id='"+id+"'";

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, nuevoNombre);
		ps.setDouble(2, nuevoPrecio);
		ps.setInt(3, nuevaCategoria);

		ps.executeUpdate();
		ps.close();

	}
	public static void eliminar() throws SQLException{
		System.out.println("Introduce la id del elemento a borrar: ");
		int id = tlc.nextInt();

		String sql="DELETE FROM articulo WHERE id='"+id+"'";

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();

	}
	public static void consultar() throws SQLException{
		System.out.println("Introduce la id de la tabla a consultar: ");
		int id = tlc.nextInt();


		String sql="SELECT * FROM articulo WHERE id='"+id+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		System.out.printf("%5s %30s %10s %9s\n", "id", "nombre", "precio", "categoria");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.printf("%5s %30s %10s %5s\n",rs.getInt("id"),rs.getString("nombre"),rs.getDouble("precio"),rs.getInt("categoria"));
			System.out.println();

		}
		rs.close();
		ps.close();
	}
	public static void listar() throws SQLException{
		String sql="SELECT * FROM articulo";

		PreparedStatement ps= connection.prepareStatement(sql);
		System.out.printf("%5s %30s %10s %5s\n", "id", "nombre", "precio","categoria");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.printf("%5s %30s %10s %5s\n",rs.getInt("id"),rs.getString("nombre"),rs.getDouble("precio"),rs.getInt("categoria"));
			System.out.println();
		}
		rs.close();
		ps.close();
	}


}
	


