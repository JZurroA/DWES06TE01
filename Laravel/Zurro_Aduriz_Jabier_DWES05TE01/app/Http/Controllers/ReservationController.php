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
use App\Models\Reservation;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use InvalidArgumentException;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Log;

// Clase que controla las peticiones relacionadas con las reservas. Hereda de la clase Controller de Laravel.
class ReservationController extends Controller
{
    public function store(Request $request) {
        $validatedData = $request->validate([
            'book_id' => 'required|integer|exists:books,id',
            'user_id' => 'required|integer|exists:users,id',
            'start_date' => 'required|date',
            'end_date' => 'required|date',
        ]);
    
        try {
            // Verificación de conflictos de fechas en la BD (DAO)
            $conflict = Reservation::where('book_id', $request->book_id)
                ->where(function ($query) use ($request) {
                    $query->whereBetween('start_date', [$request->start_date, $request->end_date])
                          ->orWhereBetween('end_date', [$request->start_date, $request->end_date])
                          ->orWhere(function ($query) use ($request) {
                              $query->where('start_date', '<=', $request->start_date)
                                    ->where('end_date', '>=', $request->end_date);
                          })
                          ->orWhere(function ($query) use ($request) {
                              $query->where('start_date', '<=', $request->start_date)
                                    ->where('end_date', '>=', $request->start_date);
                          });
                })
                ->exists();
    
            if ($conflict) {
                return response()->json([
                    'error' => 'The selected dates conflict with an existing reservation.'
                ], Response::HTTP_CONFLICT);
            }
    
            // Crear la reserva si no hay conflictos
            $reservation = Reservation::create($validatedData);

            /* Enviar notificación a través de un servicio externo
             * Se realiza una petición POST al microservicio desarrollado con Spring Boot (localhost:8080),
             * encargado de registrar la notificación en su propia base de datos y simular el envío
             * de un correo electrónico al usuario con los detalles de la reserva realizada.
             */
            try {
                Http::post('http://localhost:8080/api/notificaciones', $reservation->toArray());
            } catch (\Exception $e) {
                Log::error('Error al enviar la notificación al microservicio: ' . $e->getMessage());
            }            
            
            return response()->json($reservation, Response::HTTP_CREATED);                    
    
        } catch (InvalidArgumentException $e) {
            // Captura el error lanzado por el modelo
            return response()->json([
                'error' => $e->getMessage()
            ], Response::HTTP_BAD_REQUEST);
        }
    }   

    public function update(Request $request, $id) {
        try {
            // Buscar la reserva con el ID proporcionado
            $reservation = Reservation::findOrFail($id);
    
            // Validación de los datos recibidos
            $validatedData = $request->validate([
                'book_id' => 'required|integer|exists:books,id',
                'user_id' => 'required|integer|exists:users,id',
                'start_date' => 'required|date',
                'end_date' => 'required|date',
            ]);
    
            // Verificar si hay conflicto de fechas con otras reservas
            $conflict = Reservation::where('book_id', $request->book_id)
                ->where(function ($query) use ($request) {
                    $query->whereBetween('start_date', [$request->start_date, $request->end_date])
                        ->orWhereBetween('end_date', [$request->start_date, $request->end_date])
                        ->orWhere(function ($query) use ($request) {
                            $query->where('start_date', '<=', $request->start_date)
                                ->where('end_date', '>=', $request->end_date);
                        })
                        ->orWhere(function ($query) use ($request) {
                            $query->where('start_date', '<=', $request->start_date)
                            ->where('end_date', '>=', $request->start_date);
                    });
                })
                // Excluir la reserva que estamos actualizando
                ->where('id', '!=', $id)
                ->exists();

    
            // Si hay conflicto de fechas, retornar un error
            if ($conflict) {
                return response()->json([
                    'error' => 'The selected dates conflict with an existing reservation.'
                ], Response::HTTP_CONFLICT); // Código 409 para conflicto de recursos
            }
    
            // Actualizar la reserva con los nuevos datos
            $reservation->update($validatedData);
    
            // Respuesta exitosa con el objeto actualizado
            return response()->json([
                'message' => 'Reservation updated successfully.',
                'reservation' => $reservation
            ], Response::HTTP_OK);
    
        } catch (ModelNotFoundException $e) {
            // Si la reserva no se encuentra, retornar 404
            return response()->json([
                'error' => 'The reservation with the provided ID (' . $id . ') does not exist.'
            ], Response::HTTP_NOT_FOUND);
    
        } catch (\Exception $e) {
            // Manejo de errores generales
            return response()->json([
                'error' => 'An unexpected error occurred while updating the reservation.'
            ], Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }
    

    public function destroy($id)
    {
        try {
            $reservation = Reservation::findOrFail($id);
            $reservation->delete();

            return response()->json(
                ['Reservation deleted successfully.'],
                Response::HTTP_OK
            );
            
        } catch (ModelNotFoundException $e) {
            return response()->json([
                'error' => 'The reservation with the provided ID (' . $id . ') does not exist.'
            ], Response::HTTP_NOT_FOUND); // Código 404 es más apropiado para "no encontrado"

        } catch (\Exception $e) {
            return response()->json([
                'error' => 'An unexpected error occurred while deleting the reservation.'
            ], Response::HTTP_INTERNAL_SERVER_ERROR); // Código 500 para fallos internos
        }
    }
}
