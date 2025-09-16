open class Cuenta(
    protected var saldo: Float,
    protected var tasaAnual: Float
) {
    protected var numConsignaciones: Int = 0
    protected var numRetiros: Int = 0
    protected var comisionMensual: Float = 0f

    open fun consignar(cantidad: Float) {
        saldo += cantidad
        numConsignaciones++
    }

    open fun retirar(cantidad: Float) {
        if (cantidad <= saldo) {
            saldo -= cantidad
            numRetiros++
        } else {
            println("Fondos insuficientes")
        }
    }

    fun calcularInteresMensual() {
        val interesMensual = saldo * (tasaAnual / 12 / 100)
        saldo += interesMensual
    }

    open fun extractoMensual() {
        saldo -= comisionMensual
        calcularInteresMensual()
    }

    open fun imprimir() {
        println("Saldo: $saldo")
        println("Número de consignaciones: $numConsignaciones")
        println("Número de retiros: $numRetiros")
        println("Tasa anual: $tasaAnual")
        println("Comisión mensual: $comisionMensual")
    }
}

// Subclase: Cuenta de Ahorros
class CuentaAhorros(saldo: Float, tasaAnual: Float) : Cuenta(saldo, tasaAnual) {
    private var activa: Boolean = saldo >= 10000

    override fun consignar(cantidad: Float) {
        if (activa) {
            super.consignar(cantidad)
        } else {
            println("Cuenta inactiva, no se puede consignar")
        }
    }

    override fun retirar(cantidad: Float) {
        if (activa) {
            super.retirar(cantidad)
        } else {
            println("Cuenta inactiva, no se puede retirar")
        }
    }

    override fun extractoMensual() {
        if (numRetiros > 4) {
            comisionMensual += (numRetiros - 4) * 1000
        }
        super.extractoMensual()
        activa = saldo >= 10000
    }

    override fun imprimir() {
        super.imprimir()
        println("Cuenta activa: $activa")
        println("Número total de transacciones: ${numConsignaciones + numRetiros}")
    }
}

// Subclase: Cuenta Corriente
class CuentaCorriente(saldo: Float, tasaAnual: Float) : Cuenta(saldo, tasaAnual) {
    private var sobregiro: Float = 0f

    override fun retirar(cantidad: Float) {
        if (cantidad <= saldo) {
            saldo -= cantidad
        } else {
            sobregiro += (cantidad - saldo)
            saldo = 0f
        }
        numRetiros++
    }

    override fun consignar(cantidad: Float) {
        var monto = cantidad  // ✅ ahora monto es mutable

        if (sobregiro > 0) {
            if (monto >= sobregiro) {
                monto -= sobregiro
                sobregiro = 0f
                saldo += monto
            } else {
                sobregiro -= monto
            }
        } else {
            super.consignar(monto)
        }
        numConsignaciones++
    }

    override fun imprimir() {
        super.imprimir()
        println("Sobregiro: $sobregiro")
        println("Número total de transacciones: ${numConsignaciones + numRetiros}")
    }
}

// Método main
fun main() {
    val cuentaAhorros = CuentaAhorros(12000f, 12f)
    cuentaAhorros.consignar(2000f)
    cuentaAhorros.retirar(3000f)
    cuentaAhorros.extractoMensual()
    cuentaAhorros.imprimir()

    println("=================================")

    val cuentaCorriente = CuentaCorriente(5000f, 10f)
    cuentaCorriente.retirar(7000f) // genera sobregiro
    cuentaCorriente.consignar(4000f) // paga sobregiro
    cuentaCorriente.extractoMensual()
    cuentaCorriente.imprimir()
}
