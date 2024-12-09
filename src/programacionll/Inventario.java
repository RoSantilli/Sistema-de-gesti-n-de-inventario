/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionll;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SFTA
 */
public class Inventario {
    private List<Producto> productos; // Lista que almacena todos los productos en el inventario.
    
    // Constructor: inicializa la lista de productos.
    public Inventario() {
        this.productos = new ArrayList<>();
    }
    
    // Método para agregar un nuevo producto al inventario.
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    // Método para modificar un producto existente.
    public void modificarProducto(int index, Producto nuevoProducto) {
        if (index >= 0 && index < productos.size()) { // Verifica que el índice sea válido.
            productos.set(index, nuevoProducto);
        } else {
            System.out.println("Producto no encontrado."); // Manejo de error si el índice no es válido.
        }
    }

    // Método para eliminar un producto del inventario.
    public void eliminarProducto(int index) {
        if (index >= 0 && index < productos.size()) { // Verifica si el índice existe en la lista.
            productos.remove(index);
        } else {
            System.out.println("Producto no encontrado."); // Error si no hay producto en ese índice.
        }
    }

    // Método para mostrar todos los productos del inventario.
    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            for (int i = 0; i < productos.size(); i++) { // Itera y muestra cada producto junto con su índice.
                System.out.println((i + 1) + ". " + productos.get(i));
            }
        }
    }
    
    // Método para buscar un producto por su nombre.
    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto; // Retorna el producto encontrado.
            }
        }
        return null; 
    }
    
    // Método para obtener la lista de productos (getter).
    public List<Producto> getProductos() {  
        return productos;
    }

    // Métodos para búsquedas y filtros (por nombre, categoría, precio, etc.)
    public void buscarPorNombre(String nombre) {
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos con ese nombre.");
        }
    }

    public void buscarPorCategoria(String categoria) {
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos en esa categoria.");
        }
    }
    
    public void filtrarPorPrecioMinimo(double precioMinimo) { 
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getPrecio() >= precioMinimo) { 
                System.out.println(producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos con precio mayor o igual a $" + precioMinimo);
        }
    }
    
    public void filtrarPorPrecioMaximo(double precioMaximo) {
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getPrecio() <= precioMaximo) {
                System.out.println(producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos con precio menor o igual a $" + precioMaximo);
        }
    }
    
    // Métodos para guardar y cargar inventarios desde/para archivos de texto.
    public void guardarInventario(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Producto producto : productos) {
                // Escribe los atributos de cada producto en el archivo, separados por comas.
                writer.write(producto.getNombre() + "," + producto.getCategoria() + "," + producto.getCantidad() + "," + producto.getPrecio());
                writer.newLine();
            }
            System.out.println("Inventario guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }
    
    public void cargarInventario(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String categoria = datos[1];
                int cantidad = Integer.parseInt(datos[2]);
                double precio = Double.parseDouble(datos[3]);
                agregarProducto(new Producto(nombre, categoria, cantidad, precio)); // Crea un nuevo producto y lo agrega.
            }
            System.out.println("Inventario cargado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir datos numericos: " + e.getMessage());
        }
    }
} 