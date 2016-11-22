using System;
using System.Data;
using MySql.Data.MySqlClient;
using MySql.Data;



namespace PDbPrueba
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			Console.WriteLine ("probando acceso a dbprueba");

			//CONEXION
			IDbConnection dbconnection = new MySqlConnection(
				"Database=dbprueba;User Id=root;Password=sistemas");

			//PERMITE AÑADIR,ELIMINAR,ETC...
			IDbCommand dbcomand = dbconnection.CreateCommand();

			//SIRVE PARA AÑADIR
			IDbDataParameter dbdataparameter = dbcomand.CreateParameter ();
			IDbDataParameter dbdataparameter2 = dbcomand.CreateParameter ();

			//PERMITE LEER LAS CONSULTA EN BD
			IDataReader query;


			dbconnection.Open ();


			//operaciones

			Console.Write ("0.SALIR" + "\n" + "1.NUEVO" + "\n" + "2.EDITAR" + "\n" + "3.ELIMINAR" + "\n" + "4.LISTAR TODOS"+"\n");


			Boolean flag=true;
			do {
				Console.Write ("selecciona una opcion");

				switch (Console.Read ()) {
					case '0':

					Console.Write ("SALIR");
					Environment.Exit (0);
					break;

					case '1':

					Console.Write ("NUEVO");
					Console.WriteLine ("Introduce el nombre");
					String nombreteclado = Console.ReadLine ();
					dbcomand.CommandText = "insert into categoria (nombre) values(@nombre)";
					dbdataparameter.ParameterName = "nombre";
					dbdataparameter.Value = nombreteclado;
					dbcomand.Parameters.Add (dbdataparameter);
					dbcomand.ExecuteNonQuery ();

					//EXCEPCIONES

					//private const int ERP_DUP_ENTRY =1062;
					//private static string getUserMessage(MysqlException ex){
					//switch (ex.Number){
					//case ERP_DUP_ENTRY:
					//	retrun "Dato duplicado.Ese dato ya existe";
					// }
					// return ex.Message;
					//}

					break;

					case '2':
					Console.WriteLine ("");
					Console.Write ("EDITAR");
					Console.WriteLine ("");
					dbcomand.CommandText = "select id,nombre from categoria"; 
					query = dbcomand.ExecuteReader ();

					while (query.Read()) {

						int id = query.GetInt32 (0);
						String nom = query.GetString (1);
						Console.WriteLine ("El id es " + id + "y es de " + nom);

					}
					query.Close ();


					Console.Write ("Introduce el nombre nuevo");
					String nombrenuevo = Console.ReadLine ();
					Console.Write ("dime el id");
					String sinid = Console.ReadLine ();
					int conid = int.Parse (sinid);
					dbcomand.CommandText = "update categoria set nombre=@nombrenuevo where id=@id";
					dbdataparameter.ParameterName = "nombrenuevo";
					dbdataparameter.Value = nombrenuevo;
					dbdataparameter2.ParameterName = "id";
					dbdataparameter2.Value = conid;
					dbcomand.Parameters.Add (dbdataparameter);
					dbcomand.Parameters.Add (dbdataparameter2);

					dbcomand.ExecuteNonQuery ();
					break;

					case '3':
					Console.WriteLine ("");
					Console.WriteLine ("ELIMINAR");
					Console.WriteLine ("");
					Console.WriteLine ("Dime el nombre a borrar");
					String elimina = Console.ReadLine ();
					dbcomand.CommandText = "delete from categoria where nombre=@elimina";
					dbdataparameter.ParameterName = "elimina";
					dbdataparameter.Value = elimina;
					dbcomand.Parameters.Add (dbdataparameter);

					dbcomand.ExecuteNonQuery ();
					break;


					case '4':
					Console.WriteLine ("");
					Console.Write ("LISTAR TODOS");
					Console.WriteLine ("");
					dbcomand.CommandText = "select id,nombre from categoria"; 
					query = dbcomand.ExecuteReader ();

					while (query.Read()) {

						int id = query.GetInt32 (0);
						String nom = query.GetString (1);
						Console.WriteLine ("El id es " + id + " y es de " + nom);

					}
					query.Close ();
					break;
				}



			} while(flag=true);
			dbconnection.Close ();
		}
	}
}
