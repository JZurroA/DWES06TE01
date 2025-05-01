<?php

/**
 * Autor: Jabier Zurro Aduriz
 * Fecha: 16/03/2025
 * Asignatura: DWES
 * UD: 5 - Migración a Laravel de un Servicio Web en PHP
 */

/**
 * Namespace: App\Models
 * Las clases dentro de este namespace representan entidades que se corresponden con las tablas de la base de datos.
 */
namespace App\Models;

// Importar las clases necesarias
use Carbon\Carbon;
use Illuminate\Database\Eloquent\Model;

// Clase que representa una reserva. Hereda de la clase Model de Eloquent.
class Reservation extends Model
{
    protected $table = 'reservations';
    
    public $timestamps = false;

    /**
     * Summary of fillable
     * @var array
     */
    protected $fillable = [
        'book_id',
        'user_id',
        'start_date',
        'end_date'
    ];

    // Validación de fecha en la capa de Entidad
    public static function boot()
    {
        parent::boot();

        static::creating(function ($reservation) {
            if (Carbon::parse($reservation->start_date)->isBefore(today())) {
                throw new \InvalidArgumentException('The requested start date cannot be earlier than the current date.');
            }

            if (Carbon::parse($reservation->end_date)->isBefore($reservation->start_date)) {
                throw new \InvalidArgumentException('The requested start date cannot be later than the current date.');
            }
        });
    }
}