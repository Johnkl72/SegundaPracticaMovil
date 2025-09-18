# Sistema de Cuentas Bancarias en Kotlin

Este proyecto contiene una implementación básica de un sistema bancario con clases que modelan cuentas de ahorro y cuentas corrientes en Kotlin, permitiendo consignaciones, retiros, cálculo de intereses mensuales y manejo de comisiones y sobregiros.

Integrantes:
John Luis Alberto Castillo Reupo - 22200117
Martín Alonso Quiñonez Ruiz - 

---

## Clases Principales

### Clase `Cuenta`

- Clase base que maneja:
  - saldo
  - tasa anual de interés
  - número de consignaciones y retiros
  - comisión mensual
- Métodos para consignar, retirar, calcular intereses y generar extractos mensuales.
- Método para imprimir el estado de la cuenta.

### Subclase `CuentaAhorros`

- Hereda de `Cuenta`.
- Añade el estado `activa`, que depende del saldo mínimo de 10,000.
- Los métodos `consignar` y `retirar` solo funcionan si la cuenta está activa.
- Comisiones por retiros adicionales después de 4 retiros mensuales.
- Actualiza la condición de cuenta activa tras el extracto mensual.
- Método personalizado para imprimir el estado incluyendo la actividad y total de transacciones.

### Subclase `CuentaCorriente`

- Hereda de `Cuenta`.
- Maneja sobregiro permitiendo retirar más del saldo disponible.
- La consignación primero cubre el sobregiro antes de incrementar el saldo.
- Método para imprimir estado con información del sobregiro y total de transacciones.

---

## Uso

En el método `main` se muestran ejemplos de uso:

- Creación de una cuenta de ahorros, consignaciones y retiros, cálculo de extracto e impresión del estado.
- Creación de una cuenta corriente con sobregiro, consignación para pago de sobregiro, cálculo de extracto e impresión del estado.

---

## Requisitos

- Kotlin 1.4 o superior.
- Ambiente de ejecución compatible con Kotlin (JVM, Android, etc.).

---

## Cómo ejecutar

1. Copiar el código fuente en un archivo Kotlin (`.kt`).
2. Compilar y ejecutar con un compilador Kotlin o entorno de desarrollo compatible como IntelliJ IDEA.

---

## Ejemplo de salida

