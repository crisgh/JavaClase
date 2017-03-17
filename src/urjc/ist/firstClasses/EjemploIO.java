package urjc.ist.firstClasses;
import java.io.*;

public class EjemploIO {

	public static void main(String[] args) {
				

		// ********************** IO **************************** //
		
		//		"__Forma 1__"			//
		
		DataOutputStream odata = null;
		try {
			FileOutputStream sumidero = new FileOutputStream("/tmp/a");
			BufferedOutputStream bio = new BufferedOutputStream(sumidero);
			odata = new DataOutputStream(bio);
			odata.writeBoolean(true);
			odata.writeLong(1234L);
			odata.writeDouble(3.1416);

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally{
			try{
				if(odata != null){
					odata.close();
				}
					
			}catch(IOException eclose){
				eclose.printStackTrace();
			}
		}			
	
		//		"__Forma 2__"			//
	
		// ****** Try - catch with resources ******* //
	
		try (DataOutputStream odata2 =
				new DataOutputStream(
						new BufferedOutputStream(
								new FileOutputStream("/tmp/a2")))
			){
				odata2.writeBoolean(true);
				odata2.writeLong(1234L);
				odata2.writeDouble(3.1416);
			}catch(IOException ex){
				ex.printStackTrace();
			}
		
			 				// ******* Decorador Para la salida ******** // 
			
		//	Tenemos que leer y escribir los datos en las mismas posiciones 
		
		try{
			FileInputStream fuente = new FileInputStream("/tmp/a");
			BufferedInputStream ibs = new BufferedInputStream(fuente);
			DataInputStream idata = new DataInputStream(ibs);
			boolean b = idata.readBoolean();
			long l = idata.readLong();
			double d = idata.readDouble();
			idata.close();
			System.out.println(b + " " + l + " " + d);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		try{
			FileInputStream fuente = new FileInputStream("/tmp/a2");
			BufferedInputStream ibs = new BufferedInputStream(fuente);
			DataInputStream idata = new DataInputStream(ibs);
			boolean b = idata.readBoolean();
			long l = idata.readLong();
			double d = idata.readDouble();
			idata.close();
			System.out.println(b + " " + l + " " + d);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
}
