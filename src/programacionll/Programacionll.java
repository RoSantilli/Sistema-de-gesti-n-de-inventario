/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionll;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * Clase principal que gestiona el menú interactivo para el sistema de inventario.
 * Permite realizar operaciones como agregar, modificar, eliminar, y consultar productos.
 * También incluye funcionalidad para guardar los datos en un archivo.
 *
 * @author SFTA
 */
public class Programacionll {
    
    private static final String NOMBRE_ARCHIVO = "inventario.txt"; // Nombre del archivo donde se guardará el inventario
    
    //Método principal del menú. Controla las operaciones del sistema de inventario.
    public void Menu(){
        Inventario inventario = new Inventario(); // Inicializa el inventario
        inventario.cargarInventario(NOMBRE_ARCHIVO); // Carga datos desde el archivo
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        // Bucle principal del menú
        while (!salir) {
            System.out.println("\n");
            System.out.println("Bienvenido al menu principal");
            System.out.println("\n");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Consultar Productos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            
            try {
                if (scanner.hasNextInt()) {  // Verifica si la entrada es un número
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    switch (opcion) {
                        case 1:
                            // Lógica para agregar un producto
                            System.out.print("Ingrese nombre del producto: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Ingrese categoria del producto: ");
                            String categoria = scanner.nextLine();
                            
                            int cantidad;
                            do {
                                System.out.print("Ingrese cantidad del producto: ");
                                cantidad = scanner.nextInt();
                                if (cantidad < 0) {
                                    System.out.println("Error: La cantidad no puede ser negativa. Intente nuevamente.");
                                }
                            } while (cantidad < 0);
                            
                            double precio;
                            do {
                                System.out.print("Ingrese precio del producto: $");
                                precio = scanner.nextDouble();
                                if (precio < 0) {
                                    System.out.println("Error: El precio no puede ser negativo. Intente nuevamente.");
                                }
                            } while (precio < 0);
                            
                            inventario.agregarProducto(new Producto(nombre, categoria, cantidad, precio));
                            System.out.println("Producto agregado exitosamente.");

                            break;
                        
                        case 2:
                            // Lógica para modificar un producto existente
                            System.out.println("\n");
                            System.out.print("Ingrese el nombre del producto a modificar: ");
                            String nombreProductoModificar = scanner.nextLine();
                            Producto productoModificar = inventario.buscarProductoPorNombre(nombreProductoModificar);
                            
                            if (productoModificar != null) {
                                System.out.println("Datos actuales del producto: " + productoModificar);

                                System.out.println("Ingrese nuevos detalles del producto: ");
                                System.out.print("Nombre: ");
                                String nuevoNombre = scanner.nextLine();
                                System.out.print("Categoria: ");
                                String nuevaCategoria = scanner.nextLine();
                                
                                int nuevaCantidad;
                                do {
                                    System.out.print("Cantidad: ");
                                    nuevaCantidad = scanner.nextInt();
                                    if (nuevaCantidad < 0) {
                                        System.out.println("Error: La cantidad no puede ser negativa. Intente nuevamente.");
                                    }
                                } while (nuevaCantidad < 0);
                                
                                double nuevoPrecio;
                                do {
                                    System.out.print("Precio: $");
                                    nuevoPrecio = scanner.nextDouble();
                                    if (nuevoPrecio < 0) {
                                        System.out.println("Error: El precio no puede ser negativo. Intente nuevamente.");
                                    }
                                } while (nuevoPrecio < 0);
                                
                                scanner.nextLine();
                                
                                productoModificar.setNombre(nuevoNombre);
                                productoModificar.setCategoria(nuevaCategoria);
                                productoModificar.setCantidad(nuevaCantidad);
                                productoModificar.setPrecio(nuevoPrecio);
                                
                                System.out.println("Producto modificado exitosamente.");
                            } else {
                                System.out.println("Producto no encontrado.");
                            }
                            break;
                        
                        case 3:
                            // Lógica para eliminar un producto
                            System.out.println("\n");
                            System.out.print("Ingrese el nombre del producto a eliminar: ");
                            String nombreEliminar = scanner.nextLine();
                            Producto productoEliminar = inventario.buscarProductoPorNombre(nombreEliminar);
                            
                            if (productoEliminar != null) {
                                inventario.eliminarProducto(inventario.getProductos().indexOf(productoEliminar));
                                System.out.println("Producto eliminado exitosamente.");
                            } else {
                                System.out.println("Producto no encontrado.");
                            }
                            break;
                        
                        case 4:
                            // Lógica para consultar productos mediante diferentes criterios
                            boolean seguirBuscando = true;
                            while (seguirBuscando) {
                                System.out.println("\n");
                                System.out.println("Elija un criterio para buscar productos:");
                                System.out.println("1. Buscar por nombre");
                                System.out.println("2. Buscar por categoria");
                                System.out.println("3. Filtrar por precio minimo");
                                System.out.println("4. Filtrar por precio maximo");
                                System.out.println("5. Mostrar todos los productos");
                                System.out.println("6. Salir de la busqueda");
                                System.out.print("Seleccione una opcion: ");
                                
                                try {
                                    int opcionBusqueda = scanner.nextInt();
                                    scanner.nextLine();  
                                    System.out.println();
                                    
                                    switch (opcionBusqueda) {
                                        case 1:
                                            System.out.print("Ingrese el nombre del producto: ");
                                            String nombreBusqueda = scanner.nextLine();
                                            inventario.buscarPorNombre(nombreBusqueda);
                                            break;
                                        
                                        case 2:
                                            System.out.print("Ingrese la categoria del producto: ");
                                            String categoriaBusqueda = scanner.nextLine();
                                            inventario.buscarPorCategoria(categoriaBusqueda);
                                            break;
                                        
                                        case 3:
                                            double precioMinimo = -1;
                                            while (precioMinimo < 0) {
                                                System.out.print("Ingrese el precio minimo: $");
                                                try {
                                                    precioMinimo = scanner.nextDouble();
                                                    if (precioMinimo < 0) {
                                                        System.out.println("El precio mínimo no puede ser negativo. Intente nuevamente.");
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Entrada invalida. Por favor, ingrese un numero.");
                                                    scanner.nextLine(); 
                                                }
                                            }
                                            inventario.filtrarPorPrecioMinimo(precioMinimo);
                                            break;
                                        
                                        case 4:
                                            // Filtrar por precio máximo
                                            double precioMaximo = -1;
                                            while (precioMaximo < 0) {
                                                System.out.print("Ingrese el precio maximo: $");
                                                try {
                                                    precioMaximo = scanner.nextDouble();
                                                    if (precioMaximo < 0) {
                                                        System.out.println("El precio maximo no puede ser negativo. Intente nuevamente.");
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Entrada invalida. Por favor, ingrese un numero.");
                                                    scanner.nextLine(); 
                                                }
                                            }
                                            inventario.filtrarPorPrecioMaximo(precioMaximo);
                                            break;
                                        
                                        case 5:
                                            // Guardar inventario y salir
                                            inventario.mostrarProductos();
                                            break;
                                        
                                        case 6:
                                            seguirBuscando = false;
                                            break;
                                        
                                        default:
                                            System.out.println("Opcion no valida. Intente de nuevo.");
                                    }
                                    
                                } catch (InputMismatchException e) {
                                    System.out.println("Entrada invalida. Por favor, ingrese un numero entre 1 y 6.");
                                    scanner.nextLine();
                                }
                            }
                            break;
                            
                        case 5:
                            inventario.guardarInventario(NOMBRE_ARCHIVO);
                            salir = true;
                            break;
                        
                        default:
                            System.out.println("Opcion no valida. Intente de nuevo.");
                    }
                } else {
                    System.out.println("Entrada invalida. Por favor, ingrese un numero entre 1 y 5.");
                    scanner.next();  // Descarta la entrada no válida
                }
            
            } catch (InputMismatchException e) { 
                System.out.println("Entrada invalida. Por favor, ingrese un numero.");
                scanner.nextLine();  // Limpia el buffer
            } 
        }
    }   
}