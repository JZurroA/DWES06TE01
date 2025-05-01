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

// Importar la clase Model de Eloquent
use Illuminate\Database\Eloquent\Model;

// Clase que representa un usuario. Hereda de la clase Model de Eloquent.
class User extends Model
{
    public $timestamps = false;

    protected $table = 'users';

    /**
     * Summary of fillable
     *
     * @var array
     */
    protected $fillable = [
        'name',
        'surname',
        'email'
    ];    

    public function reservations()
    {
        return $this->hasMany(Reservation::class, 'user_id');
    }
}