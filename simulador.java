import java.util.Scanner;

public class simulador {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String id_Proceso, nombre;
        int numProcesos, tamano, tEjecucion, tLlegada, memoriaTotal, quantum;
        int memoriaDisponible;

        ListaDinamica colaListos = new ListaDinamica();  // Cola de procesos ingresados (espera)
        ListaDinamica colaEjecucion = new ListaDinamica();  // Cola de procesos cargados a memoria (FIFO ejecución)

        System.out.println("SIMULADOR PLANIFICADOR DE PROCESOS\n");
        System.out.println("****************************************************\n");
        System.out.println("Se simulará la política de planificación de Round Robin\n");

        System.out.print("Ingrese el tamaño de la memoria: ");
        memoriaTotal = sc.nextInt();
        memoriaDisponible = memoriaTotal;

        System.out.print("Ingrese el valor del quantum: ");
        quantum = sc.nextInt();

        System.out.print("Ingresa el número de procesos a simular: ");
        numProcesos = sc.nextInt();

        System.out.println("\nIngresa los datos de los procesos:");
        for (int i=1; i<=numProcesos; i++){
            System.out.println("\nDatos del proceso " + i);
            System.out.print("\tId (numérico o alfabético): ");
            sc.nextLine();
            id_Proceso = sc.nextLine();
            while (colaListos.verificarId(id_Proceso)==1){
                System.out.print("Ya existe un proceso con ese identificador, ingrese un nuevo id: ");
                id_Proceso = sc.nextLine();
            }
            System.out.print("\tNombre: ");
            nombre = sc.nextLine();
            System.out.print("\tTamano: ");
            tamano = sc.nextInt();
            System.out.print("\tTiempo de llegada: ");
            tLlegada = sc.nextInt();
            System.out.print("\tTiempo que requiere para su ejecución: ");
            tEjecucion = sc.nextInt();

            Proceso proceso = new Proceso(id_Proceso, nombre, tamano, tEjecucion, tLlegada);
            colaListos.agregar(proceso);

            System.out.println("\nSe creó el proceso " + nombre);
            System.out.println("Cola de procesos listos (esperando cargar a memoria):");
            colaListos.imprimir();
        }

        System.out.println("\n\n**** CARGA DE PROCESOS A MEMORIA ****");
        Nodo temp = colaListos.head;
        while (temp != null){
            Proceso p = temp.proceso;
            if (p.tamano <= memoriaDisponible){
                colaEjecucion.agregar(p);
                memoriaDisponible -= p.tamano;
                System.out.println("Subió el proceso " + p.nombre + " y restan " + memoriaDisponible + " unidades de memoria");
            } else {
                System.out.println("No hay suficiente memoria para cargar el proceso " + p.nombre + " (tamaño: " + p.tamano + ")");
                break; 
            }
            temp = temp.nodoSiguiente;
        }

        System.out.println("\n\nCola de procesos listos (en espera):");
        while (temp != null) {
            Proceso p = temp.proceso;
            System.out.println("\tId: " + p.id_Proceso + ", nombre: " + p.nombre +
                            ", tamaño: " + p.tamano +
                            ", tiempo ejecución: " + p.tEjecucion +
                            ", tiempo llegada: " + p.tLlegada +
                            ", tiempo restante: " + p.tiempoRestante);
            temp = temp.nodoSiguiente;
        }

        System.out.println("\nCola de procesos listos para ejecución (en memoria):");
        colaEjecucion.imprimir();
    }
}
