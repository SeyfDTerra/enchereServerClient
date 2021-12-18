// A Java program for a Server
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Server
{
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;

	public Server(int port)
	{ List<Enchere> encheres=new ArrayList<Enchere>();
        encheres.add(new Enchere("Pc_IBM", 7500, 17)) ; 
        encheres.add(new Enchere("Velo", 250, 16));
        encheres.add(new Enchere("Acer", 1100, 12));
		int enchereConcerne =0;
		// starts server and waits for a connection
		try
		{
            String name="";
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			// takes input from the client socket
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String line = "";
			boolean isLoggedIn=false;
			// reads message from client until Exit
			while (!line.equals("Exit"))
			{
				try
				{
					line = in.readUTF();
					//System.out.println(line);
                    
					if(line.contains("LOGIN")){
						name=line.split(" ")[1];
						isLoggedIn=true;
					}
                      if(isLoggedIn)  {
                        if(line.contains("JOIN")){
							String str[]=line.split(" ");
                            encheres.get(Integer.parseInt(str[1])-1).membres.add(new Membre(name));
							enchereConcerne=Integer.parseInt(str[1])-1;
                            
						}
						if(line.contains("ENCHERE")){
							for (Enchere enchere : encheres) {
                                System.out.println(enchere.toString());
                            }
                            
						}
						
						if(line.contains("OFFRE")){
							String str1[]=line.split(" ");
							String str2[]=str1[1].split("##");
							if(Float.parseFloat(str2[1])>encheres.get(enchereConcerne).prix){
								System.out.println("Offre Accepté ");
								encheres.get(enchereConcerne).offres.add(new Offre(name,str2[0],Float.parseFloat(str2[1])));
								encheres.get(enchereConcerne).prix=Float.parseFloat(str2[1]);
							}else{
								System.out.println("Offre rejeté");
							}
						}
						if(line.contains("LIST")){
							String str[]=line.split(" ");
							for (Offre offre : encheres.get(Integer.parseInt(str[1])).offres) {
								System.out.println(offre.membre+"#"+offre.produit+"#"+offre.prix);
							}
						}
					  }

				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
