<?php

/**
 * Autor: Jabier Zurro Aduriz
 * Fecha: 16/03/2025
 * Asignatura: DWES
 * UD: 5 - Migración a Laravel de un Servicio Web en PHP
 */

/**
 * Namespace: App\Http\Controllers
 * 
 * Los controladores dentro de este namespace son responsables de manejar las solicitudes HTTP entrantes
 * y devolver las respuestas apropiadas. Cada controlador suele estar asociado con una entidad del sistema,
 * como usuarios, reservas, libros, etc.
 * 
 * Los controladores implementan la lógica de negocio y definen las acciones que se ejecutan en respuesta
 * a las solicitudes de los usuarios, ya sea a través de vistas, datos JSON o redirecciones.
 */
namespace App\Http\Controllers;

// Importar las clases necesarias
use App\Models\Book;
use Illuminate\Http\Response;

// Clase que controla las peticiones relacionadas con los libros. Hereda de la clase Controller de Laravel.
class BookController extends Controller
{
    // Listar todos los libros
    public function index()
    {  
        $books = Book::all();

        if ($books->isEmpty()) {
            return response()->json(['error' => 'No books found'], Response::HTTP_NOT_FOUND);
        }

        return response()->json($books, Response::HTTP_OK);
    }

    // Mostrar un libro específico
    public function show($id)
    {
        $book = Book::find($id);

        if (!$book) {
            return response()->json(['error' => 'Book Not Found'], Response::HTTP_NOT_FOUND);
        }

        return response()->json($book, Response::HTTP_OK);
    }
}