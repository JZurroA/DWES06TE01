<?php

/**
 * Autor: Jabier Zurro Aduriz
 * Fecha: 16/03/2025
 * Asignatura: DWES
 * UD: 5 - Migración a Laravel de un Servicio Web en PHP
 */


// Clase que define las rutas de la API y hace de FrontController

// Importar las clases necesarias
use App\Http\Controllers\BookController;
use App\Http\Controllers\ReservationController;
use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;


// Rutas de la API
Route::get('/books', [BookController::class, 'index']);
Route::get('/books/{id}', [BookController::class, 'show'])->where('id', '[0-9]+');
Route::get('/users', [UserController::class, 'index']);
Route::get('/users/{id}', [UserController::class, 'show'])->where('id', '[0-9]+');
Route::post('/reservations', [ReservationController::class, 'store']);
Route::put('/reservations/{id}', [ReservationController::class, 'update'])->where('id', '[0-9]+');
Route::delete('/reservations/{id}', [ReservationController::class, 'destroy'])->where('id', '[0-9]+');