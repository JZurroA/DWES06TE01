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

use Illuminate\Database\Eloquent\Model;

// Clase que representa un libro. Hereda de la clase Model de Eloquent.
class Book extends Model
{
    public $timestamps = false;
    protected $table = 'books';
    /**
     * Summary of fillable
     * @var array
     */
    protected $fillable = [
        'title',
        'author',
        'genre'
    ];
}
