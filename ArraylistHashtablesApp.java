import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ArraylistHashtablesApp {

	public static void main(String[] args) {
//		metodoTarea1();
//		metodoTarea2();
//		metodoTarea3();
//		metodoTarea4();

	}
	
//	**** TAREA 1 ****
//	Método principal
	public static void metodoTarea1() {
//		Creamos un hashtable para guardar las medias de los alumnos y un arraylist para las notas
		Hashtable<Integer, Double> mediasAlumnos = new Hashtable<Integer, Double>();
		ArrayList<Integer> notasAlumnos = new ArrayList<>();
		generarNotas(notasAlumnos, mediasAlumnos);
		
	}
	
	public static void generarNotas(ArrayList<Integer> notasAlumnos, Hashtable<Integer, Double> mediasAlumnos) {
		DecimalFormat df = new DecimalFormat("#.00");
		Random rndm = new Random();
		int nota;
		double media = 0;
		
//		Bucle principal donde generamos las notas y las medias de los 25 alumnos de uno en uno
		for (int i = 0; i < 25; i++) {
			System.out.println("\nAlumno "+(i+1));
			if(!notasAlumnos.isEmpty()) {
				notasAlumnos.clear();
				media = 0;
			}
			
//			Bucle para generar las notas de C1 a C6 de un alumno y sacarlas por consola
			for (int j = 0; j < 6; j++) {
				nota = rndm.nextInt(11);
				media += nota;
				notasAlumnos.add(nota);
				System.out.println("C"+(j+1)+": "+notasAlumnos.get(j));
			}
			
//			Calculamos la nota media del último alumno del que hemos generado las notas y la añadimos al hashtable
			mediasAlumnos.put(i, (media/6));
//			Enseñamos por consola la media del alumno
			System.out.println("Media: "+df.format(mediasAlumnos.get(i)));
		}
	}
	
//	**** TAREA 2 ****
//	Método principal
	public static void metodoTarea2() {
//		Creamos dos hashtables para guardar el precio y el iva aplicable de los productos
		Hashtable<String, Double> precios = new Hashtable<String, Double>();
		Hashtable<String, Double> iva = new Hashtable<String, Double>();
		precios.put("pan", 1.0);
		precios.put("zumo", 1.5);
		precios.put("ketchup", 2.0);
		precios.put("cocacola", 0.33);
		precios.put("spaghetti", 1.20);
		precios.put("pollo", 3.0);
		precios.put("cebolla", 2.5);
		precios.put("manzana", 1.8);
		precios.put("leche", 0.9);
		precios.put("vino", 10.0);
		precios.put("embutido", 2.8);
		
		iva.put("pan", 0.04);
		iva.put("zumo", 0.21);
		iva.put("ketchup", 0.21);
		iva.put("cocacola", 0.21);
		iva.put("spaghetti", 0.21);
		iva.put("pollo", 0.21);
		iva.put("cebolla", 0.04);
		iva.put("manzana", 0.04);
		iva.put("leche", 0.04);
		iva.put("vino", 0.21);
		iva.put("embutido", 0.21);
		
		cajaSuper(precios, iva);
	}
	
	public static void cajaSuper(Hashtable<String, Double> precios, Hashtable<String, Double> iva) {	
		DecimalFormat df = new DecimalFormat("#.00");
//		Variables para controlar si hay más productos a introducir, el total a cobrar, el precio del producto con iva aplicado y la cantidad total de productos
		boolean otroProd = true;
		double dineroCobrar = 0;
		double precioIva = 0;
		int totalProductos = 0;
//		String que contendrá todo el output del método
		String output = "Lista de productos:\n Cantidad\tProducto\tPrecio bruto\tPrecio total (IVA incl.)\n";
		
//		Bucle para cobrar los productos y que nos irá generando el output del método
		while(otroProd) {
//			Pedimos input del producto a cobrar y la cantidad, lo añadimos al hashtable del carrito
			String producto = JOptionPane.showInputDialog("Indica el producto a cobrar");
			int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Qué cantidad?"));
//			Calculamos el precio del producto con el iva correspondiente aplicado
			precioIva = ((precios.get(producto)+(precios.get(producto)*iva.get(producto)))*cantidad);
//			Sumamos el precio del producto con iva incluido al precio total
			dineroCobrar += precioIva;
//			Sumamos la cantidad del producto a la cantidad total de productos comprado
			totalProductos += cantidad;
//			Añadimos todos los datos de la compra del artículo al string de output bien formatados
			output += cantidad+"\t"+producto+"\t"+(cantidad*precios.get(producto))+"\t"+df.format(precioIva)+" ("+iva.get(producto)+"% IVA)\n";
			
//			Bucle para controlar si hay que cobrar más productos, en caso de que no la variable otroProd pasaría a false y saldriamos del bucle while principal
			String[] botones = {"Si", "No"};
			int seleccion = JOptionPane.showOptionDialog(null, "Cobrar otro producto?", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			if(seleccion==1) {
				otroProd = false;
			}
		}		
		
//		Ventana que nos muestra el precio a pagar y pide input de con cuanto pagaría el cliente
		double dineroPagar = Double.parseDouble(JOptionPane.showInputDialog("El precio de la compra asciende a "+dineroCobrar+" €. Con cuanto va a pagar el cliente?"));
//		Cambio a devolver
		double cambio = dineroPagar - dineroCobrar;
//		Añadimos al string de output el total de productos, total a cobrar, efectivo y cambio
		output += "-------------------------------\nArtículos\tTOTAL\tEFECTIVO\tCAMBIO\n";
		output += totalProductos+"\t"+dineroCobrar+"\t"+dineroPagar+"\t"+df.format(cambio);
		
//		Ventana que nos muestra el string de output final
		JOptionPane.showMessageDialog(null, new JTextArea(output));
	}
	
//	**** TAREA 3 ****
//	Método principal
	public static void metodoTarea3() {
		Hashtable<String, Integer> productos = new Hashtable<String, Integer>();
		Hashtable<String, Double> precios = new Hashtable<String, Double>();
		productos.put("pan", 500);
		productos.put("zumo", 419);
		productos.put("ketchup", 99);
		productos.put("cocacola", 258);
		productos.put("spaghetti", 50);
		productos.put("pollo", 332);
		productos.put("cebolla", 140);
		productos.put("manzana", 157);
		productos.put("leche", 452);
		productos.put("vino", 214);
		productos.put("embutido", 92);
		
		precios.put("pan", 1.0);
		precios.put("zumo", 1.5);
		precios.put("ketchup", 2.0);
		precios.put("cocacola", 0.33);
		precios.put("spaghetti", 1.20);
		precios.put("pollo", 3.0);
		precios.put("cebolla", 2.5);
		precios.put("manzana", 1.8);
		precios.put("leche", 0.9);
		precios.put("vino", 10.0);
		precios.put("embutido", 2.8);
		
		seleccion(productos, precios, false);
	}
	
//	Método para generar las ventanas de selección
	public static void seleccion(Hashtable<String, Integer> productos, Hashtable<String, Double> precios, boolean cont) {
		if(cont) {
			String[] botones = {"Añadir un producto", "Visualizar productos", "Cerrar"};
			int seleccion = JOptionPane.showOptionDialog(null, "Qué quieres hacer ahora?", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			
			switch (seleccion) {
			case 0:
				generarProducto(productos, precios);
				break;
			case 1:
				consultarProductos(productos, precios);
				break;
			case 2:
				break;
			}
		} else {
			String[] botones = {"Añadir un producto", "Visualizar productos", "Cerrar"};
			int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			
			switch (seleccion) {
			case 0:
				generarProducto(productos, precios);
				break;
			case 1:
				consultarProductos(productos, precios);
				break;
			case 2:
				break;
			}
		}
	}
	
//	Método que nos añade un producto, el precio y la cantidad a sus respectibas hashtables
	public static void generarProducto(Hashtable<String, Integer> productos, Hashtable<String, Double> precios) {
		String producto = JOptionPane.showInputDialog("Qué artículo quieres añadir?");
		int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Qué cantidad?"));
		double precio = Double.parseDouble(JOptionPane.showInputDialog("A que precio?"));
		productos.put(producto, cantidad);
		precios.put(producto, precio);
		seleccion(productos, precios, true);
	}
	
//	Método para consultar un producto individual o listar todo los productos disponibles
	public static void consultarProductos(Hashtable<String, Integer> productos, Hashtable<String, Double> precios) {
		String[] botones = {"Buscar un producto", "Visualizar todos los productos", "Cerrar"};
		int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
		Enumeration<Integer> cantidad = productos.elements();
		Enumeration<Double> precio = precios.elements();
		Enumeration<String> llaves = productos.keys();
		String output = "";
		
		switch (seleccion) {
		case 0:
			String inputProd = JOptionPane.showInputDialog("Qué producto quieres buscar?");
			JOptionPane.showMessageDialog(null, "Hay "+productos.get(inputProd)+" "+inputProd+" con un precio individual de "+precios.get(inputProd)+" €");
			seleccion(productos, precios, true);
			break;
			
		case 1:
			while(cantidad.hasMoreElements()) {
				output += "\nHay "+cantidad.nextElement()+" "+llaves.nextElement()+" a "+precio.nextElement()+" €";
			}
			JOptionPane.showMessageDialog(null, output);
			seleccion(productos, precios, true);
			break;
			
		case 2:		
			break;
		}

	}

//	**** TAREA 4 ****
//	Método principal
	public static void metodoTarea4() {
		Hashtable<String, Integer> productos = new Hashtable<String, Integer>();
		Hashtable<String, Double> precios = new Hashtable<String, Double>();
		Hashtable<String, Double> iva = new Hashtable<String, Double>();
		
		productos.put("pan", 500);
		productos.put("zumo", 419);
		productos.put("ketchup", 99);
		productos.put("cocacola", 258);
		productos.put("spaghetti", 50);
		productos.put("pollo", 332);
		productos.put("cebolla", 140);
		productos.put("manzana", 157);
		productos.put("leche", 452);
		productos.put("vino", 214);
		productos.put("embutido", 92);
		
		precios.put("pan", 1.0);
		precios.put("zumo", 1.5);
		precios.put("ketchup", 2.0);
		precios.put("cocacola", 0.33);
		precios.put("spaghetti", 1.20);
		precios.put("pollo", 3.0);
		precios.put("cebolla", 2.5);
		precios.put("manzana", 1.8);
		precios.put("leche", 0.9);
		precios.put("vino", 10.0);
		precios.put("embutido", 2.8);
		
		iva.put("pan", 0.04);
		iva.put("zumo", 0.21);
		iva.put("ketchup", 0.21);
		iva.put("cocacola", 0.21);
		iva.put("spaghetti", 0.21);
		iva.put("pollo", 0.21);
		iva.put("cebolla", 0.04);
		iva.put("manzana", 0.04);
		iva.put("leche", 0.04);
		iva.put("vino", 0.21);
		iva.put("embutido", 0.21);
		
		seleccion2(productos, precios, iva, false);
	}
	
	public static void listaCompra(Hashtable<String, Integer> productos, Hashtable<String, Double> precios, Hashtable<String, Double> iva) {	
		DecimalFormat df = new DecimalFormat("#.00");
//		Variables para controlar si hay más productos a introducir, el total a cobrar, el precio del producto con iva aplicado y la cantidad total de productos
		boolean otroProd = true;
		double dineroCobrar = 0;
		double precioIva = 0;
		int totalProductos = 0;
//		Variable que contendra todo el output del método
		String output = "Lista de productos:\n Cantidad\tProducto\tPrecio bruto\tPrecio total (IVA incl.)\n";
		
		while(otroProd) {
//			Pedimos input del producto a cobrar y la cantidad, lo añadimos al hashtable del carrito
			String producto = JOptionPane.showInputDialog("Indica el producto a cobrar");
			int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Qué cantidad?"));
//			Restamos la cantidad cantidad comprada del producto al total del stock
			productos.put(producto, productos.get(producto)-cantidad);
//			Calculamos el precio del producto con el iva correspondiente aplicado
			precioIva = ((precios.get(producto)+(precios.get(producto)*iva.get(producto)))*cantidad);
//			Sumamos el precio del producto con iva incluido al precio total
			dineroCobrar += precioIva;
//			Sumamos la cantidad del producto a la cantidad total de productos comprado
			totalProductos += cantidad;
//			Añadimos todos los datos de la compra del artículo al string de output bien formatados
			output += cantidad+"\t"+producto+"\t"+(cantidad*precios.get(producto))+"\t"+df.format(precioIva)+" ("+iva.get(producto)+"% IVA)\n";
			
//			Bucle para controlar si hay que cobrar más productos, en caso de que no la variable otroProd pasaría a false y saldriamos del bucle while principal
			String[] botones = {"Si", "No"};
			int seleccion = JOptionPane.showOptionDialog(null, "Cobrar otro producto?", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			if(seleccion==1) {
				otroProd = false;
			}
		}		
		
//		Ventana que nos muestra el precio a pagar y pide input de con cuanto pagaría el cliente
		double dineroPagar = Double.parseDouble(JOptionPane.showInputDialog("El precio de la compra asciende a "+dineroCobrar+" €. Con cuanto va a pagar el cliente?"));
//		Cambio a devolver
		double cambio = dineroPagar - dineroCobrar;
//		Añadimos al string de output el total de productos, total a cobrar, efectivo y cambio
		output += "-------------------------------\nArtículos\tTOTAL\tEFECTIVO\tCAMBIO\n";
		output += totalProductos+"\t"+dineroCobrar+"\t"+dineroPagar+"\t"+df.format(cambio);
		
//		Ventana que nos muestra el string de output final
		JOptionPane.showMessageDialog(null, new JTextArea(output));
		seleccion2(productos, precios, iva, true);
		
	}
	
//	Método para generar las ventanas de selección
	public static void seleccion2(Hashtable<String, Integer> productos, Hashtable<String, Double> precios, Hashtable<String, Double> iva, boolean cont) {
//		Este condicional controla el mensaje que aparecerá en la ventana pero tanto el if como el else llevan a los mismos métodos pasando los mismos atributos
		if(cont) {
			String[] botones = {"Cobrar", "Añadir un producto", "Visualizar productos", "Cerrar"};
			int seleccion = JOptionPane.showOptionDialog(null, "Qué quieres hacer ahora?", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			
			switch (seleccion) {
			case 0:
				listaCompra(productos, precios, iva);
				break;
			case 1:
				generarProducto2(productos, precios, iva);
				break;
			case 2:
				consultarProductos2(productos, precios, iva);
				break;
			case 3:
				break;
			}
		} else {
			String[] botones = {"Cobrar", "Añadir un producto", "Visualizar productos", "Cerrar"};
			int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			
			switch (seleccion) {
			case 0:
				listaCompra(productos, precios, iva);
				break;
			case 1:
				generarProducto2(productos, precios, iva);
				break;
			case 2:
				consultarProductos2(productos, precios, iva);
				break;
			case 3:
				break;
			}
		}
		
	}
	
//	Método que nos añade un producto, el precio y la cantidad a sus respectibas hashtables
	public static void generarProducto2(Hashtable<String, Integer> productos, Hashtable<String, Double> precios, Hashtable<String, Double> iva) {
//		Pedimos por pantalla el nombre del artículo, cuanto stock habrá y el precio unitario
		String producto = JOptionPane.showInputDialog("Qué artículo quieres añadir?");
		int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Qué cantidad?"));
		double precio = Double.parseDouble(JOptionPane.showInputDialog("A que precio?"));
//		Lo añadimos a los hashtables correspondientes y volvemos a la ventana de selección
		productos.put(producto, cantidad);
		precios.put(producto, precio);
		seleccion2(productos, precios, iva, true);
	}
	
//	Método para consultar un producto individual o listar todo los productos disponibles
	public static void consultarProductos2(Hashtable<String, Integer> productos, Hashtable<String, Double> precios, Hashtable<String, Double> iva) {
		String[] botones = {"Buscar un producto", "Visualizar todos los productos", "Cerrar"};
		int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Base de datos supermercado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
		Enumeration<Integer> cantidad = productos.elements();
		Enumeration<Double> precio = precios.elements();
		Enumeration<String> llaves = productos.keys();
		String output = "";
		
		switch (seleccion) {
		case 0:
			String inputProd = JOptionPane.showInputDialog("Qué producto quieres buscar?");
			JOptionPane.showMessageDialog(null, "Hay "+productos.get(inputProd)+" "+inputProd+" con un precio individual de "+precios.get(inputProd)+" € con un IVA del "+iva.get(inputProd)*100+" %");
			seleccion2(productos, precios, iva, true);
			break;
			
		case 1:
			while(cantidad.hasMoreElements()) {
				output += "\nHay "+cantidad.nextElement()+" "+llaves.nextElement()+" a "+precio.nextElement()+" €";
			}
			JOptionPane.showMessageDialog(null, output);
			seleccion2(productos, precios, iva, true);
			break;
			
		case 2:		
			break;
		}
	}
}
